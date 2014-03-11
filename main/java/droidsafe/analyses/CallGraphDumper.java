package droidsafe.analyses;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import soot.MethodOrMethodContext;
import soot.SootMethod;
import soot.jimple.toolkits.callgraph.Edge;
import droidsafe.analyses.pta.PTABridge;
import droidsafe.android.system.API;

public class CallGraphDumper {

    public CallGraphDumper() {
        // TODO Auto-generated constructor stub
    }

    public static void run(String fileStr) {
        System.out.println("Dumping call graph.");
        FileWriter fw;
        try {
            fw = new FileWriter(fileStr);

            fw.write("digraph CallGraph {\n");

            //get the harness main and all the edges in the call graph
            //from it, and for each edge to an entry point, create and populate
            //the rCFG node
            Set<Edge> visitedEdges = 
                    new HashSet<Edge>();
            Map<String, Set<MethodOrMethodContext>> classSubgraphNodes = 
                    new HashMap<String, Set<MethodOrMethodContext>>();
            Map<String, Set<Edge>> classSubgraphEdges = 
                    new HashMap<String, Set<Edge>>();

            int uniqueID = 0;

            for (MethodOrMethodContext src : PTABridge.v().getReachableMethodContexts()) {
                if (API.v().isSystemMethod(src.method()))
                    continue;

                String className = src.method().getDeclaringClass().getName();

                if (!classSubgraphNodes.containsKey(className)) {
                    classSubgraphNodes.put(className, new HashSet<MethodOrMethodContext>());
                }

                classSubgraphNodes.get(className).add(src);

                Iterator<Edge> edges = PTABridge.v().getCallGraph().edgesOutOf(src);
                while (edges.hasNext()) {
                    Edge edge = edges.next();

                    if (!API.v().reportInSpec(edge.tgt()) && API.v().isSystemMethod(edge.tgt()))
                        continue;

                    boolean isAPI = API.v().reportInSpec(edge.tgt());

                    if (!visitedEdges.contains(edge)) {
                        String tgtID = Integer.toString(edge.getTgt().hashCode());

                        if (isAPI) {
                            //create unique id for api calls so they show up multiple times
                            tgtID += uniqueID++;
                            fw.write(getMethodDotLabel(edge.getTgt(), true, tgtID));
                        }

                        if (className.equals(edge.tgt().method().getDeclaringClass().getName())) {
                            if (!classSubgraphEdges.containsKey(className)) {
                                classSubgraphEdges.put(className, new HashSet<Edge>());
                            }

                            classSubgraphEdges.get(className).add(edge);
                        } else {
                            fw.write(edge.getSrc().hashCode() + "->" + tgtID + ";\n");
                        }

                        visitedEdges.add(edge);

                    }

                }
            }

            for (String pack : classSubgraphNodes.keySet()) {
                String label = pack.replace('.', '_').replace('$', '_');
                fw.write("subgraph " + label + "{\n");
                fw.write("label = \"" + pack + "\";\n");
                fw.write("color=blue;\n");
                for (MethodOrMethodContext m : classSubgraphNodes.get(pack)) {
                    fw.write(getMethodDotLabel(m, false, Integer.toString(m.hashCode())));
                }
                if (classSubgraphEdges.containsKey(pack)) {
                    for (Edge e : classSubgraphEdges.get(pack)) {
                        fw.write(e.getSrc().hashCode() + "->" + e.getTgt().hashCode() + ";\n");
                    }
                }

                fw.write("}\n");
            }

            fw.write("}");
            fw.close();
        } catch (IOException e) {
            System.err.println("Error writing call graph dot file");
            droidsafe.main.Main.exit(1);
        }
    }

    /**
     * Return dot label for methods for dot call graph generation.
     */
    private static String getMethodDotLabel(MethodOrMethodContext m, boolean isAPI, String uniqueID) {
        StringBuffer buf = new StringBuffer();
        if (isAPI)
            buf.append(uniqueID + " [label=\"");
        else 
            buf.append(uniqueID + " [label=\"");
        buf.append(m.method().getDeclaringClass() + "\\n");
        buf.append(m.method().getSubSignature() + "\"");
        if (isAPI)
            buf.append(",color=red,style=filled");
        buf.append("];\n");
        return buf.toString();
    }

}

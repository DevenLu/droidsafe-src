package droidsafe.eclipse.plugin.core.view.callhierarchy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.Signature;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import droidsafe.eclipse.plugin.core.util.DroidsafePluginUtilities;
import droidsafe.reports.SourceCallTree;

/**
 * The base class for CalleeHierarchy and CallerHierachy.
 * 
 * @author gilham
 *
 */
public abstract class CallHierarchy {

	/** 
	 * A map from projects to the Json objects representing the source call trees
	 * from the entry points for the corresponding projects.
	 */
    public static Map<IProject, JsonObject> projectCalleeHierarchyMap = new HashMap<IProject, JsonObject>();

	/** Description of this call hierarchy. */
	protected String fDesription;
	
    /** The underlying project for this call hierarchy. */
	protected IProject fProject;

	/**
	 * Returns the description string of this call hierarchy.
	 */
	public String getDescription() {
		return fDesription;
	}

    /**
     * Computes and returns a description string for this call hierarchy.
     * 
     * @param method - the root method
     * @return the description string
     */
    abstract String computeDescription(IMethod method);
    
    /**
     * Returns the underlying project for this call hierarchy. */
	public IProject getProject() {
		return fProject;
	}

    /**
     * Returns the top-level Json object parsed from the Json file source_call_graph.txt 
	 * in the droidsafe output directory for the given projects. This Json object 
	 * represents the source call graph from the project's entry points.
     */
    public static JsonElement getProjectCalleeHierarchy(IProject project) {
    	if (project != null) {
    		JsonObject callHierarchy = projectCalleeHierarchyMap.get(project);
    		if (callHierarchy == null) {
    			String fileName = DroidsafePluginUtilities.droidsafeOutputFile(project, SourceCallTree.FILE_NAME);
    			File file = new File(fileName);
    			if (file.exists()) {
    				callHierarchy = DroidsafePluginUtilities.parseIndicatorFile(file);
    				projectCalleeHierarchyMap.put(project, callHierarchy);
    			}
    		}
    		return callHierarchy;
    	}
    	return null;
    }

	/**
	 * Returns true if the given soot type strings and the given jdt type strings match.
	 */
	static boolean typesMatch(String[] sootTypeStrings, String[] jdtTypeStrings) {
		if (sootTypeStrings.length != jdtTypeStrings.length) {
			return false;
		}
		for (int i = 0; i < sootTypeStrings.length; i++) {
			if (!typeMatch(sootTypeStrings[i], jdtTypeStrings[i]))
				return false;
		}
		return true;
	}

	/**
	 * Returns true if the given soot type string and the given jdt type string match.
	 */
	static boolean typeMatch(String sootTypeString, String jdtTypeString) {
		String type = Signature.toString(jdtTypeString);
		if (sootTypeString.equals(type))
			return true;
		if (jdtTypeString.startsWith("Q") && droidsafe.utils.Utils.extractClassname(sootTypeString).equals(type))
			return true;
		return false;
	}

}

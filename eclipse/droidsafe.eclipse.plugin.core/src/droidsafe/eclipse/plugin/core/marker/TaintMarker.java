package droidsafe.eclipse.plugin.core.marker;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.Position;

import droidsafe.speclang.model.CallLocationModel;

/**
 * A text marker that indicates tainted data in a Java source file.
 * 
 * @author gilham
 *
 */
public class TaintMarker {

    /**
     * The type ID of TaintMarker.
     */
    public static final String TYPE = "droidsafe.taint.marker";
    
    /**
     * The annotation ID of TaintMarker.
     */
    public static final String ANNOTATION = "droidsafe.taint";
    
    /**
     * The name of the attribute whose value is a set of taint source units
     */
    public static final String SOURCES = "sources";
    
    /**
     * The name of the attribute whose value is a set of filtered taint source units
     * 
     */
    public static final String FILTERED_SOURCES = "filteredSources";
    
    /**
     * Creates a Taint Marker for the given file at the given position with the given sources and filtered
     * sources attribute values.
     * 
     * @param file - the java source file in which the tainted data resides
     * @param taintPosition - the position where the tainted data is located
     * @param sources - taint source units for the taint marker
     * @param filteredSources - filtered taint source units for the taint marker
     * @return the taint marker created
     * @throws CoreException
     */
    public static IMarker createMarker(IFile file, Position taintPosition, Set<CallLocationModel> sources, 
    		Map<String, Set<CallLocationModel>> filteredSources)
    				throws CoreException {
        IMarker marker = file.createMarker(TYPE);
        int offset = taintPosition.getOffset();
        int length = taintPosition.getLength();
        marker.setAttributes(
            new String[] {IMarker.CHAR_START, IMarker.CHAR_END, IMarker.MESSAGE, SOURCES, FILTERED_SOURCES},
            new Object[] {new Integer(offset), new Integer(offset + length), "Taint", sources, filteredSources});
        return marker;
    }

    /**
     * Returns the taint source units for the given taint marker.
     * 
     * @param marker - a taint marker
     * @return the taint source units
     */
    public static Set<CallLocationModel> getSources(IMarker marker) {
        try {
            Set<CallLocationModel> sources = (Set<CallLocationModel>) marker.getAttribute(SOURCES);
            return sources;
        } catch (CoreException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_SET;
    }
    
    /**
     * Returns the filtered taint source units for the given taint marker.
     * 
     * @param marker - a taint marker
     * @return the filtered taint source units
     */
    public static Map<String, Set<CallLocationModel>> getFilteredTaintSourcesMap(IMarker marker) {
        try {
        	Map<String, Set<CallLocationModel>> sources = (Map<String, Set<CallLocationModel>>) marker.getAttribute(FILTERED_SOURCES);
            return sources;
        } catch (CoreException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_MAP;
    }
    
}

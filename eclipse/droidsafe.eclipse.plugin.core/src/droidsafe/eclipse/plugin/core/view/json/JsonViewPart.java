package droidsafe.eclipse.plugin.core.view.json;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import droidsafe.eclipse.plugin.core.Activator;
import droidsafe.eclipse.plugin.core.specmodel.TreeElement;
import droidsafe.eclipse.plugin.core.util.DroidsafePluginUtilities;
import droidsafe.eclipse.plugin.core.view.DroidsafeInfoOutlineViewPart;
import droidsafe.eclipse.plugin.core.view.infoflow.MethodWithSourceSinkFilter;
import droidsafe.eclipse.plugin.core.view.infoflow.SourceSinkPair;
import droidsafe.eclipse.plugin.core.view.pointsto.PointsToViewPart;
import droidsafe.eclipse.plugin.core.view.value.ValueViewPart;
import droidsafe.speclang.model.MethodModel;

/**
 * View for displaying the points-to info on the receiver/arguments of a given method. 
 * 
 * @author Limei Gilham (gilham@kestrel.edu)
 * 
 */
abstract public class JsonViewPart extends DroidsafeInfoOutlineViewPart {

    protected String fInputElement;
    
    /** The text displayed on the empty page. */
    protected static String EMPTY_PAGE_LABEL = "No Android Project selected. "
          + "\nSelect an Android project in the Project Explorer."
          + "\nYou may also need to run the Droidsafe spec generation "
          + "command from the project context menu.";
    
    public Map<String, Boolean> getVisibilityMap() {
        return ((JsonTreeElementContentProvider)fContentProvider).getVisibilityMap();
    }

    public boolean getVisibility(String type) {
        Map<String, Boolean> visibilityMap = getVisibilityMap();
        Boolean visibility = visibilityMap.get(type);
        if (visibility != null)
            return visibility.booleanValue();
        return true;
    }

    public void toggleVisibility(String type) {
        Map<String, Boolean> visibilityMap = getVisibilityMap();
        Boolean visibility = visibilityMap.get(type);
        if (visibility != null)
            visibilityMap.put(type, !visibility.booleanValue());
    }

    /**
     * Set the input element for the viewer and update the contents of the view.
     */
    protected void setInputElement(String inputElement) {
        IProject project = getProject();
        if (project != null) {
            inputElement = DroidsafePluginUtilities.droidsafeOutputFile(project, inputElement);
            if (inputElement != fInputElement || fTreeViewer.getInput() == null) {
                fInputElement = inputElement;
                updateView();
            }
        }
    }
    
    protected static String getViewId() {
        return null;
    }

    /**
     * Return the input element for this outline.
     */
    public String getInputElement() {
        return fInputElement;
    }

    /**
     * Update the content of the outline view.
     */
    public void updateView() {
        if (fInputElement != null && fParentComposite != null) {
            JsonObject jsonObj = DroidsafePluginUtilities.parseIndicatorDisplay(fInputElement);
            showPage(PAGE_VIEWER);
            fTreeViewer.setInput(jsonObj);
        }        
    }

    @Override
    protected ITreeContentProvider makeContentProvider() {
        return new JsonTreeElementContentProvider();
    }

    @Override
    protected IBaseLabelProvider makeLabelProvider() {
        return new JsonTreeElementLabelProvider();
    }

    @Override
    protected String emptyPageText() {
        return EMPTY_PAGE_LABEL;
    }
    
    @Override
    protected int autoExpandLevel() {
      return 3;
    }

    /**
     * Open the outline view for the given input element.
     */
    public static void openView(String jsonFileName, String viewId) {
        IWorkbenchPage activePage = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
        
        JsonViewPart view = (JsonViewPart) activePage.findView(viewId);
        if (view == null) {
            // open the view
            try {
                view = (JsonViewPart) activePage.showView(viewId);
            } catch (PartInitException e) {
                e.printStackTrace();
            }
        }
        view.setInputElement(jsonFileName);
//        activePage.activate(view);
    }

    @Override
    public void selectionChanged(SelectionChangedEvent e) {
        if (e.getSelectionProvider() == fTreeViewer) {
          ISelection selection = e.getSelection();
            revealSelectionInEditor(selection, false);
        }
    }

    public void addFilter(Filter filter) {
        ((JsonTreeElementContentProvider)fContentProvider).addFilter(filter);
        refresh();
    }

    public List<Filter> getFilters() {
        return ((JsonTreeElementContentProvider)fContentProvider).getFilters();
    }

    public void setFilters(List<Filter> filters) {
        ((JsonTreeElementContentProvider)fContentProvider).setFilters(filters);
        refresh();
    }


}

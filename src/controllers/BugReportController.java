package controllers;

import java.util.List;

import controllers.exceptions.UnauthorizedAccessException;
import model.BugTrap;
import model.bugreports.IBugReport;
import model.bugreports.commands.CreateBugReportCommand;
import model.bugreports.filters.FilterType;
import model.bugreports.forms.BugReportAssignForm;
import model.bugreports.forms.BugReportCreationForm;
import model.bugreports.forms.BugReportUpdateForm;
import model.bugreports.forms.CommentCreationForm;
import model.projects.IProject;

/**
 * Controller for all BugReport related things.
 * Controllers are the interface that is available to developers
 * creating e.g. a BugTrap UI.
 */
public class BugReportController extends Controller {

	public BugReportController(BugTrap bugTrap) {
		super(bugTrap);
	}
	
	public BugReportCreationForm getBugReportCreationForm() throws UnauthorizedAccessException{
		return getBugTrap().getFormFactory().makeBugReportCreationForm();
	}

	public CommentCreationForm getCommentCreationForm() throws UnauthorizedAccessException{
		return getBugTrap().getFormFactory().makeCommentCreationForm();
	}

	public BugReportAssignForm getBugReportAssignForm() throws UnauthorizedAccessException{
		return getBugTrap().getFormFactory().makeBugReportAssignForm();
	}
	
	public BugReportUpdateForm getBugReportUpdateForm() throws UnauthorizedAccessException{
		return getBugTrap().getFormFactory().makeBugReportUpdateForm();
	}

	public List<IBugReport> getBugReportList() throws UnauthorizedAccessException{
		return getBugTrap().getBugReportManager().getBugReportList();
	}

	public List<IBugReport> getBugReportsForProject(IProject project) throws UnauthorizedAccessException {
		return getBugTrap().getBugReportManager().getBugReportsForProject(project);
	}
	
	public FilterType[] getFilterTypes() throws UnauthorizedAccessException{
		return getBugTrap().getBugReportManager().getFilterTypes();
	}

	public List<IBugReport> getOrderedList(FilterType[] types, String[] arguments) throws UnauthorizedAccessException {
		return getBugTrap().getBugReportManager().getOrderedList(types, arguments);
	}

	public void createBugReport(BugReportCreationForm form) throws UnauthorizedAccessException {
		new CreateBugReportCommand(getBugTrap(), form).execute();
	}

	public void createComment(CommentCreationForm form) throws UnauthorizedAccessException {
		form.allVarsFilledIn();

		getBugTrap().getBugReportManager().addComment(form.getCommentable(), form.getText());
	}

	public void updateBugReport(BugReportUpdateForm form) throws UnauthorizedAccessException {
		form.allVarsFilledIn();
		
		getBugTrap().getBugReportManager().updateBugReport(form.getBugReport(), form.getBugTag());
	}

	public void assignToBugReport(BugReportAssignForm form) throws UnauthorizedAccessException {
		form.allVarsFilledIn();
		
		getBugTrap().getBugReportManager().assignToBugReport(form.getBugReport(), form.getDeveloper());
	}
}
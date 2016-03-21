package controllers;

import java.util.List;

import controllers.exceptions.UnauthorizedAccessException;
import model.BugTrap;
import model.projects.IProject;
import model.projects.Project;
import model.projects.forms.ProjectAssignForm;
import model.projects.forms.ProjectCreationForm;
import model.projects.forms.ProjectDeleteForm;
import model.projects.forms.ProjectForkForm;
import model.projects.forms.ProjectUpdateForm;
import model.projects.forms.SubsystemCreationForm;
import model.users.Developer;
import model.users.IUser;

/**
 * Controller for all Project related things.
 * Controllers are the interface that is available to developers
 * creating e.g. a BugTrap UI.
 */
public class ProjectController extends Controller {

	public ProjectController(BugTrap bugTrap) {
		super(bugTrap);
	}

	public List<IProject> getProjectList() throws UnauthorizedAccessException{
		if (!getBugTrap().isLoggedIn())
			throw new UnauthorizedAccessException("You need to be logged in to perform this action.");
		
		return getBugTrap().getProjectManager().getProjects();
	}
	
	public List<IProject> getProjectsForLeadDeveloper(IUser dev) {
		if (!dev.isDeveloper())
			throw new IllegalArgumentException("Given user should be a developer!");

		return getBugTrap().getProjectManager().getProjectsForLeadDeveloper(dev);
	}

	public ProjectCreationForm getProjectCreationForm() throws UnauthorizedAccessException {
		return getBugTrap().getFormFactory().makeProjectCreationForm();
	}

	public ProjectForkForm getProjectForkForm() throws UnauthorizedAccessException {
		return getBugTrap().getFormFactory().makeProjectForkForm();
	}

	public ProjectUpdateForm getProjectUpdateForm() throws UnauthorizedAccessException {
		return getBugTrap().getFormFactory().makeProjectUpdateForm();
	}
	
	public ProjectDeleteForm getProjectDeleteForm() throws UnauthorizedAccessException {
		return getBugTrap().getFormFactory().makeProjectDeleteForm();
	}

	public ProjectAssignForm getProjectAssignForm() throws UnauthorizedAccessException {
		return getBugTrap().getFormFactory().makeProjectAssignForm();
	}

	public SubsystemCreationForm getSubsystemCreationForm() throws UnauthorizedAccessException{
		return getBugTrap().getFormFactory().makeSubsystemCreationForm();
	}

	/**
	 * Create a project with the information provided in the form.
	 * @param form ProjectCreationForm containing all the details about the project to be created.
	 * @return The Project that was created.
	 */
	public IProject createProject(ProjectCreationForm form) {
		form.allVarsFilledIn();
		return getBugTrap().getProjectManager().createProject(form);
	}

	/**
	 * Fork a project with the information provided in the form.
	 * @param form ProjectForkForm containing all the details about the fork.
	 * @return The Project that was forked.
     */
	public IProject forkProject(ProjectForkForm form) {
		form.allVarsFilledIn();
		return getBugTrap().getProjectManager().createFork(form);
	}

	/**
	 * Update a project with the information provided in the form.
	 * @param form ProjectUpdateForm containing all the details about the update.
	 */
	public void updateProject(ProjectUpdateForm form) {
		form.allVarsFilledIn();
		getBugTrap().getProjectManager().updateProject(form);
	}

	/**
	 * Assign a developer to the project with the information provided in the form.
	 * @param form ProjectAssignForm containing the details about the assignment.
	 */
	public void assignToProject(ProjectAssignForm form) {
		form.allVarsFilledIn();
		getBugTrap().getProjectManager().assignToProject(form);
	}

	/**
	 * Delete a project with the information provided in the form.
	 * @param form ProjectDeleteForm containing the details about the project to be deleted.
	 */
	public void deleteProject(ProjectDeleteForm form) {
		getBugTrap().getProjectManager().deleteProject(form);
	}
	
	/**
	 * Create a subsystem with the information provided in the form.
	 * @param form SubsystemCreationForm containing all the details about the subsystem to be created.
	 */
	public void createSubsystem(SubsystemCreationForm form) {
		form.allVarsFilledIn();
		getBugTrap().getProjectManager().createSubsystem(form);
	}
}
package model;

import model.bugreports.BugReportManager;
import model.notifications.NotificationManager;
import model.projects.ProjectManager;
import model.users.UserManager;

/**
 * This class represents the complete BugTrap system.
 * It contains several managers and a form factory.
 */
public class BugTrap {

	private final UserManager userManager;
	private final ProjectManager projectManager;
	private final BugReportManager bugReportManager;
	private final NotificationManager notificationManager;
	private final FormFactory formFactory;
	
	
	public BugTrap() {
		this.userManager = new UserManager();
		this.projectManager = new ProjectManager(this);
		this.bugReportManager = new BugReportManager(this);
		this.notificationManager = new NotificationManager(this);
		this.formFactory = new FormFactory(this);
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public ProjectManager getProjectManager() {
		return projectManager;
	}

	public BugReportManager getBugReportManager() {
		return bugReportManager;
	}

	public NotificationManager getNotificationManager() {
		return notificationManager;
	}
	
	public FormFactory getFormFactory() {
		return formFactory;
	}
	
	public boolean isLoggedIn(){
		if(getUserManager().getLoggedInUser() == null)
			return false;
		else
			return true;
	}
	
	public boolean isAdminLoggedIn(){
		if(!isLoggedIn())
			return false;
		return getUserManager().getLoggedInUser().isAdmin();
	}
	
	public boolean isIssuerLoggedIn(){
		if(!isLoggedIn())
			return false;
		return getUserManager().getLoggedInUser().isIssuer();
	}
	
	public boolean isDeveloperLoggedIn(){
		if(!isLoggedIn())
			return false;
		return getUserManager().getLoggedInUser().isDeveloper();
	}
	
	public void initialize() {
		BugTrapInitializer initializer = new BugTrapInitializer(this);
		initializer.init();
	}
}
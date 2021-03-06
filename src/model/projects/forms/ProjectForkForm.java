package model.projects.forms;

import java.util.Date;

import model.Form;
import model.projects.IProject;
import model.projects.Version;
import model.users.IUser;

/**
 * Form used to store temporary data to fork project.
 */
public class ProjectForkForm implements Form {
    private IProject project;

    private double budgetEstimate;
    private Date startDate;
    private Version version;
	private IUser leadDeveloper;

    public ProjectForkForm() {
		project 		= null;
		startDate 		= null;
		leadDeveloper 	= null;
		version 		= null;
		budgetEstimate 	= 0;
    }

    public double getBudgetEstimate() {
        return budgetEstimate;
    }

    public void setBudgetEstimate(double budgetEstimate) {
        if (budgetEstimate <= 0) throw new IllegalArgumentException("The budget estimate should be strictly positive.");

        this.budgetEstimate = budgetEstimate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        if (startDate == null) throw new NullPointerException("Given starting date is null.");

        this.startDate = startDate;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        if (version == null) throw new NullPointerException("Given version is null.");
        if (project == null) throw new NullPointerException("Project should be set first and not be null.");
        if (project.getVersion().compareTo(version) >= 0) throw new IllegalArgumentException("The version specified should be larger than the project version.");
        
        this.version = version;
    }

    public IProject getProject() {
        return project;
    }

    public void setProject(IProject project) {
        if (project == null) throw new NullPointerException("Given project is null.");

        this.project = project;
    }
    
    public IUser getLeadDeveloper() {
		return leadDeveloper;
	}

	public void setLeadDeveloper(IUser leadDeveloper) {
		if (leadDeveloper == null) throw new NullPointerException("Given lead developer is null.");
		if (!leadDeveloper.isDeveloper()) throw new IllegalArgumentException("Lead developer should be a developer.");

		this.leadDeveloper = leadDeveloper;
	}

    @Override
    public void allVarsFilledIn() {
        if (getBudgetEstimate() <= 0) throw new IllegalArgumentException("Budget estimate is null");
        if (getStartDate() == null) throw new NullPointerException("Start Date is null");
        if (getProject() == null) throw new NullPointerException("Project is null");
        if (getVersion() == null) throw new NullPointerException("Version is null");
        if (getLeadDeveloper() == null) throw new NullPointerException("Lead is null");
    }
}

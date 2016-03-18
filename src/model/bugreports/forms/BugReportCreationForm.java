package model.bugreports.forms;

import model.Form;
import model.bugreports.BugReport;
import model.projects.Subsystem;
import model.users.Issuer;

import java.util.ArrayList;

public class BugReportCreationForm implements Form {

	//Information needed to create a BugReport.
	private Issuer issuer;	//Issuer who issues a BugReport.
	private String title;	//A Title for the BugReport.
	private String description;	//A description for the BugReport.
	private Subsystem subsystem;	//The Subsystem the BugReport is about.
	private ArrayList<BugReport> dependsOn;	//List of BugReports the BugReport depends on.
	
	public BugReportCreationForm() {
		//Explicitly settings this to null.
		this.issuer			= null;
		this.title			= null;
		this.description 	= null;
		this.subsystem		= null;
		this.dependsOn		= null;
	}

	@Override
	public void allVarsFilledIn() {
		if (getIssuer() == null) throw new NullPointerException("Issuer is null");
		if (getTitle() == null) throw new NullPointerException("Title is null");
		if (getDescription() == null) throw new NullPointerException("Description is null");
		if (getSubsystem() == null) throw new NullPointerException("Subsystem is null");
		if (getDependsOn() == null) throw new NullPointerException("DependsOn is null");
	}

	//Getters and Setters

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title == null) throw new NullPointerException("Title is null");
		
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description == null) throw new NullPointerException("Description is null");
		
		this.description = description;
	}

	public Subsystem getSubsystem() {
		return subsystem;
	}

	public void setSubsystem(Subsystem subsystem) {
		if (subsystem == null) throw new NullPointerException("Subsystem is null");
		
		this.subsystem = subsystem;
	}

	public ArrayList<BugReport> getDependsOn() {
		return dependsOn;
	}

	public void setDependsOn(ArrayList<BugReport> dependsOn) {
		if (dependsOn == null) throw new NullPointerException("DependsOn is null");
		
		this.dependsOn = dependsOn;
	}

	public Issuer getIssuer() {
		return issuer;
	}

	public void setIssuer(Issuer issuer) {
		if (issuer == null) throw new NullPointerException("Issuer is null");
		
		this.issuer = issuer;
	}
}
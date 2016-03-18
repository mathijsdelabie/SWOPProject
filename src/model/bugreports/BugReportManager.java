package model.bugreports;

import model.bugreports.bugtag.BugTag;
import model.bugreports.bugtag.New;
import model.bugreports.builders.BugReportBuilder;
import model.bugreports.filters.BugReportFilter;
import model.bugreports.filters.FilterType;
import model.bugreports.forms.BugReportCreationForm;
import model.projects.Subsystem;
import model.users.Issuer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * 
 * Class that stores and manages BugReports.
 *
 */
public class BugReportManager{

	private final ArrayList<BugReport> bugReportList; //List that keeps BugReports.

	/**
	 * Constructor.
	 */
	public BugReportManager() {
		this.bugReportList = new ArrayList<BugReport>();
	}

	public ArrayList<BugReport> getOrderedList(FilterType[] types, String[] arguments) {
		ArrayList<BugReport> filteredList = cloneList();
		BugReportFilter filter = new BugReportFilter(filteredList);
		
		for (int index = 0; index < types.length; index++)
			filter.filter(types[index], arguments[index]);
		
		Collections.sort(filteredList);
		
		return filteredList;
	}

	public ArrayList<BugReport> getBugReportList() {
		return bugReportList;
	}
	
	/**
	 * Creates and adds a new BugReport to the list.
	 * @param form The information for the new BugReport.
	 */
	public void addBugReport(BugReportCreationForm form) {
		addBugReport(form.getTitle(), form.getDescription(), new Date(), form.getSubsystem(), form.getIssuer(), form.getDependsOn(), new New());
	}
	
	public void addBugReport(String title, String description, Date creationDate, Subsystem subsystem, Issuer issuer, ArrayList<BugReport> dependencies, BugTag tag) {
		getBugReportList().add((new BugReportBuilder()).setTitle(title)
				.setDescription(description)
				.setSubsystem(subsystem)
				.setIssuer(issuer)
				.setDependsOn(dependencies)
				.setCreationDate(creationDate)
				.setBugTag(tag)
				.getBugReport());
	}

	private ArrayList<BugReport> cloneList() {
		ArrayList<BugReport> clonedList = new ArrayList<BugReport>();
		
		for (BugReport bugReport : getBugReportList()) clonedList.add(bugReport);
		
		return clonedList;
	}

}
package model.bugreports;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controllers.exceptions.UnauthorizedAccessException;
import model.BugTrap;
import model.bugreports.bugtag.BugTag;
import model.bugreports.builders.BugReportBuilder;
import model.bugreports.filters.BugReportFilter;
import model.bugreports.filters.FilterType;
import model.projects.ISubsystem;
import model.projects.ISystem;
import model.users.IUser;

/**
 * Class that stores and manages BugReports.
 */
public class BugReportManager {

	private final List<BugReport> bugReportList; //List that keeps BugReports.
	private final BugTrap bugTrap;
	/**
	 * Constructor.
	 * @param bugTrap the BugTrap System
	 */
	public BugReportManager(BugTrap bugTrap) {
		this.bugReportList = new ArrayList<BugReport>();
		this.bugTrap = bugTrap;
	}
	
	/**
	 * returns an array of all the different filter types
	 * @return an array of filter types
	 */
	public FilterType[] getFilterTypes() {
		return FilterType.values();
	}

	/**
	 * returns an ordered list of bug reports
	 * @param types Filter Types to filter by.
	 * @param arguments Filter arguments.
	 * @return an ordered list of bug reports
	 * @throws UnauthorizedAccessException 
	 */
	public List<IBugReport> getOrderedList(FilterType[] types, String[] arguments) throws UnauthorizedAccessException {
		if (bugTrap.getUserManager().getLoggedInUser() == null || 
				!bugTrap.getUserManager().getLoggedInUser().isIssuer())
			throw new UnauthorizedAccessException("Must be Issuer");
		
		List<IBugReport> filteredList = getBugReportList();
		
		BugReportFilter filter = new BugReportFilter(filteredList);

		for (int index = 0; index < types.length; index++)
			filter.filter(types[index], arguments[index]);
		
		return filter.getFilteredList();
	}

	/**
	 * returns a copy of the bug report list
	 * @return list of bug reports
	 */
	public List<IBugReport> getBugReportList(){
		ArrayList<IBugReport> clonedList = new ArrayList<IBugReport>();
		clonedList.addAll(bugReportList);
		return clonedList;
	}

	/**
	 * Delete the BugReports for given System and deletes the registrations for this bug report
	 * @param system The System for which to delete the BugReports
	 */
	public void deleteBugReportsForSystem(ISystem system) {
		for (IBugReport report : system.getAllBugReports()){
			((BugReport)report).terminate();
			bugReportList.remove(report);
		}
	}

	/**
	 * adds a bug report
	 * @param title Title of the BugReport
	 * @param description Description of the BugReport
	 * @param creationDate Creation Date of the BugReport
	 * @param subsystem Subsystem of the BugReport
	 * @param issuer Issuer of the BugReport
	 * @param dependencies Dependencies of the BugReport
	 * @param assignees Assignees of the BugReport
	 * @param tag Tag of the BugReport
	 */
	public void addBugReport(String title, String description, Date creationDate, ISubsystem subsystem, IUser issuer, List<IBugReport> dependencies, List<IUser> assignees, BugTag tag, TargetMilestone milestone, int impactFactor) {
		if (milestone != null) {
			if (milestone.compareTo(subsystem.getAchievedMilestone()) <= 0)
				throw new IllegalArgumentException("The target milestone should be strict higher than the achieved milestone of the subsystem");
		}

		BugReport report = new BugReportBuilder(bugTrap).setTitle(title)
				.setDescription(description)
				.setSubsystem(subsystem)
				.setIssuer(issuer)
				.setDependsOn(dependencies)
				.setCreationDate(creationDate)
				.setAssignees(assignees)
				.setBugTag(tag)
				.setImpactFactor(impactFactor)
				.setMilestone(milestone)
				.getBugReport();
		bugReportList.add(report);
	}
}
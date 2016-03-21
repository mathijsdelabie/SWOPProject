package model.bugreports.filters;

import java.util.ArrayList;
import java.util.Iterator;

import model.bugreports.BugReport;
import model.bugreports.IBugReport;
import model.users.Developer;
import model.users.IUser;

/**  
 * Class dedicated to filtering BugReport lists according to FilterTypes.  
 */  
public class BugReportFilter {

	private final ArrayList<IBugReport> filteredList; //List to filter.
		
	/**  
	 * Constructor.  
	 * @param bugReportList The BugReport list to filter.  
	 */  
	public BugReportFilter(ArrayList<IBugReport> bugReportList) {
		this.filteredList = bugReportList;
	}

	/**  
	 * Filter the list according to given FilterType and argument (of type String).  
	 * @param type FilterType to filter by.  
	 * @param string Argument to filter by.  
	 * @return The list with given filter applied.  
	 */  
	public ArrayList<IBugReport> filter(FilterType type, String string) {
		switch (type) {
		case CONTAINS_STRING:
			inTitleOrDesc(string);
			break;
		case FILED_BY_USER:
			issuedByUser(string);
			break;
		case ASSIGNED_TO_USER:
			assignedByUser(string);
			break;
		default: throw new IllegalArgumentException();
		}
		
		return getFilteredList();
	}
	
	private void inTitleOrDesc(String string) {
		Iterator<IBugReport> iter = getFilteredList().iterator();
		while (iter.hasNext()) {
			IBugReport bugReport = iter.next();
			if (!bugReport.getTitle().contains(string) && !bugReport.getDescription().contains(string))
				iter.remove();
		}
	}
	
	private void issuedByUser(String string) {
		Iterator<IBugReport> iter = getFilteredList().iterator();
		while (iter.hasNext()) {
			IBugReport bugReport = iter.next();
			if (!bugReport.getIssuedBy().getUserName().equals(string))
				iter.remove();
		}
	}
	
	private void assignedByUser(String string) {
		Iterator<IBugReport> iter = getFilteredList().iterator();
		while (iter.hasNext()) {
			IBugReport bugReport = iter.next();
			
			boolean remove = true;
			for (IUser assignee : bugReport.getAssignees()) {
				if (assignee.getUserName().equals(string))
					remove = false;
			}
			
			if (remove) iter.remove();
		}
	}

	private ArrayList<IBugReport> getFilteredList() {
		return filteredList;
	}
}
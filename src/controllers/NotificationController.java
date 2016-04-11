package controllers;

import controllers.exceptions.UnauthorizedAccessException;
import model.BugTrap;
import model.notifications.forms.RegisterNotificationForm;
import model.notifications.forms.UnregisterNotificationForm;

/**
 * Controller for all Notification related things.
 * Controllers are the interface that is available to developers
 * creating e.g. a BugTrap UI.
 */
public class NotificationController extends Controller{

	public NotificationController(BugTrap bugTrap) {
		super(bugTrap);
	}

	public void registerForNotification(RegisterNotificationForm form) throws UnauthorizedAccessException {
		getBugTrap().getNotificationManager().registerForNotification(form.getRegistrationType(), form.getObservable(), form.getTag());
	}

	public void unregisterForNotification(UnregisterNotificationForm form) throws UnauthorizedAccessException {
		getBugTrap().getNotificationManager().unregisterForNotification(form.getRegistration());
	}
}

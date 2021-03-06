package model.bugreports.commands;

import java.util.ArrayList;
import java.util.Date;

import controllers.exceptions.UnauthorizedAccessException;
import model.BugTrap;
import model.Command;
import model.bugreports.bugtag.BugTag;
import model.bugreports.forms.BugReportCreationForm;
import model.users.IUser;

public class CreateBugReportCommand extends Command {

    private BugReportCreationForm form;

    public CreateBugReportCommand(BugTrap bugTrap, BugReportCreationForm form) {
        super(bugTrap, form);
        
        this.form = form;
    }

    @Override
    public void execute() throws UnauthorizedAccessException {
        form.allVarsFilledIn();
        getBugTrap().getBugReportManager().addBugReport(form.getTitle(), form.getDescription(), new Date(), form.getSubsystem(), form.getIssuer(), form.getDependsOn(), new ArrayList<IUser>(), BugTag.NEW, form.getTargetMilestone(), form.getImpactFactor());
    }
}

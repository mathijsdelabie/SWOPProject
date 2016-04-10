package model.projects;

import java.util.List;

import model.notifications.Observable;

/**
 * Interface for the system objects.
 */
public interface ISystem {

    public String getName();
    public String getDescription();
    public ISystem getParent();
    public List<ISubsystem> getSubsystems();
    public List<AchievedMilestone> getAchievedMilestones();
    
    
    public List<ISubsystem> getAllDirectOrIndirectSubsystems();
}

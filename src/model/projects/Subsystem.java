package model.projects;

import java.util.ArrayList;

public class Subsystem extends System {

	private Project project;
	
	public Subsystem(String name, String description, System parent, Version version, Project project) {
		super(name, description, parent, version);
		setProject(project);
	}
	
	public Project getProject() {
		return project;
	}
	
	void setProject(Project project) {
		this.project = project;
	}
}

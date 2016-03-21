package tests;

import org.junit.Before;
import org.junit.Test;

import model.projects.Project;
import model.projects.Subsystem;
import model.projects.System;
import org.junit.Assert;
import model.projects.Version;

public class SystemTests {
    System sys;
    System subsys;
    System subsubsys;
    System subsys2;

    @Before
    public void setUp() throws Exception {
        sys = new Project("", "", Version.firstVersion(), null, null, 12345, null);
        subsys = new Subsystem("", "", sys, Version.firstVersion(), (Project)sys);
        subsubsys = new Subsystem("", "", subsys, Version.firstVersion(), (Project)sys);
        subsys2 = new Subsystem("", "", sys, Version.firstVersion(), (Project)sys);
    }

    @Test
    public void testSetUp(){
        Assert.assertEquals(subsys.getParent(), sys);
        Assert.assertEquals(subsys2.getParent(), sys);
        Assert.assertEquals(subsys.getSubsystems().size(), 0);
        Assert.assertEquals(sys.getSubsystems().size(), 2);
        Assert.assertEquals(sys.getVersion(), Version.firstVersion());
    }

    @Test
    public void testAddSubsystem() {
        Subsystem s = new Subsystem("sub", "descr", null, Version.firstVersion(), ((Subsystem)subsubsys).getProject());
        subsubsys.addSubsystem(s);

        Assert.assertEquals(s.getParent(), subsubsys);
        Assert.assertEquals(s.getSubsystems().size(), 1);
        Assert.assertFalse(subsys.getSubsystems().contains(s));
        Assert.assertTrue(subsys.getAllDirectOrIndirectSubsystems().contains(s));
        Assert.assertTrue(subsubsys.getSubsystems().contains(s));
    }
}

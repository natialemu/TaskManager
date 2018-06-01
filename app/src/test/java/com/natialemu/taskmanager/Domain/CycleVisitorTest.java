package com.natialemu.taskmanager.Domain;

import org.junit.Before;
import org.junit.Test;

public class CycleVisitorTest {

    private Graph graph;
    private  CycleVisitor cycleVisitor;
    @Before
    public void setUp(){
        graph = new DAG();
        cycleVisitor = new CycleVisitor();
    }

    @Test
    public void testCycleDetector(){
        //TODO
    }
}

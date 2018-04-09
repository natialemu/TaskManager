package com.natialemu.taskmanager.Domain;

/**
 * Created by Nathnael on 4/8/2018.
 * Desc: A visitor class that detects cycles in graphs
 */

public class CycleVisitor implements GraphVisitor {
    @Override
    public boolean visit(DAG directedGraph) {
        return false;//checks for cycle for the directed graph
    }
}

package com.natialemu.taskmanager.Domain;

/**
 * Created by Nathnael on 4/8/2018.
 */

public interface GraphVisitor {

    boolean visit(DAG directedGraph);
}

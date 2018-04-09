package com.natialemu.taskmanager.Domain;

import java.util.List;

/**
 * Created by Nathnael on 4/8/2018.
 */

public interface Graph {

    boolean removeItem(Item item);
    boolean removeEdge(Item source, Item target);
    boolean addItem(Item item);
    boolean addEdge(Item source, Item target);
    public boolean cycleExists(GraphVisitor visitor);
}

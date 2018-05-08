package com.natialemu.taskmanager.Domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathnael on 4/8/2018.
 *
 * Manage how when you have forrests, they're completely unrelated things so they can be done concurrently
 */

public class DAG implements Graph {
    List<GraphNode> sources;

    public DAG(){
        sources = new ArrayList<>();
    }

    @Override
    public boolean removeItem(Item item) {
        /**
         * if item is a source:
         *   remove it form sources list
         *  otherwise:
         *     check neighbor of every node for item and if found:
         *           add an edge from parent of item to all its children, provided that they dont form cycles
         *
         *
         */
        return false;
    }

    @Override
    public boolean removeEdge(Item source, Item target) {
        /**
         * for each source in sources:
         *      if source item exists:
         *          check its neighbor for target and remove it if it exists
         *          if it doesnt exist return false
         *
         */
        return false;
    }

    @Override
    public boolean addItem(Item item) {
        /**
         * add it as a source
         */
        GraphNode newItem = new GraphNode();
        newItem.setItem(item);
        sources.add(newItem);
        return true;
    }

    @Override
    public boolean addEdge(Item source, Item target) {
        /**
         * for each source:
         *     find the node with the source
         *       add the edge, provided no cycles! return false if cycles result
         * for each source:
         *     if you find the target:
         *           keep track of the corresponding source
         *           remove the source from the sources list
         *
         */
        return false;
    }

    @Override
    public boolean cycleExists(GraphVisitor visitor) {

        return visitor.visit(this);
    }


}

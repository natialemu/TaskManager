package com.natialemu.taskmanager.Domain;

import java.util.List;

/**
 * Created by Nathnael on 4/9/2018.
 */

public class ConnectivityImpl implements Connectivity {
    private Graph graph;

    public ConnectivityImpl(Graph graph){
        this.graph = graph;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    @Override
    public List<GraphNode> getSortedItems() {
        return null;
    }

    @Override
    public List<List<GraphNode>> getAllCombinationsOfSortedItems() {
        return null;
    }
}

package com.natialemu.taskmanager.Domain;

import com.natialemu.taskmanager.ForestObserver;

import java.util.List;
import java.util.Set;

/**
 * Created by Nathnael on 4/8/2018.
 */

public interface Graph {

    boolean removeItem(Item item);

    boolean addItem(Item item);

    boolean cycleExists(GraphVisitor visitor);

    Set<GraphNode> adj(GraphNode source);

    List<GraphNode> getSources();

    Set<GraphNode> getForestSources(ForestObserver targetObserver);

    void removeSources(ForestObserver targetObserver);

    void addNewObserver(ForestObserver newObserver, Set<GraphNode> s);

    boolean containsNode(GraphNode sourceNode);

    void addEdge(GraphNode sourceNode, Set<GraphNode> neighbors);

    void removeEdge(GraphNode source, GraphNode destination, ForestObserver currentObserver);

    Set<GraphNode> getParents(GraphNode sourceNode);

    int getNumberOfForests();

    Set<GraphNode> vertices();

    void removeSourceFromObserver(ForestObserver currentObserver, GraphNode targetNode);

    void addSourceToObserver(ForestObserver newObserver, GraphNode source);
}

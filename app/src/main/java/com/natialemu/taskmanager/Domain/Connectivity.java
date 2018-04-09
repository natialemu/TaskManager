package com.natialemu.taskmanager.Domain;

import java.util.List;

/**
 * Created by Nathnael on 4/9/2018.
 */

public interface Connectivity {

    List<GraphNode> getSortedItems();//returns a topological sort for DAGs, or shortest path for undirected Graphs
    List<List<GraphNode>> getAllCombinationsOfSortedItems();//returns all possible topologically sorted paths or all paths for undirected Graphs


}

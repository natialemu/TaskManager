package com.natialemu.taskmanager.Domain;

import java.util.List;
import java.util.Map;

/**
 * Created by Nathnael on 4/9/2018.
 */

public interface Connectivity {

    List<GraphNode> getSortedItems();//returns a topological sort for DAGs, or shortest path for undirected Graphs
    List<List<GraphNode>> getAllCombinationsOfSortedItems();//returns all possible topologically sorted paths or all paths for undirected Graphs

    Map<GraphNode, List<GraphNode>> getSortedItemsPerForest();
    /*
    1. A Method for returned topological sort of multiple forests
    2. A method for combining the topological sort of multiple forests in a reasonable way
        - Question: How should it be combined?
        -     1. Set up a rule for combination:
                       A. if unrelated, priority takes over
                       B.
     */

}

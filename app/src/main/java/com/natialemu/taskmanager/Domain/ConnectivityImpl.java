package com.natialemu.taskmanager.Domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Nathnael on 4/9/2018.
 */

public class ConnectivityImpl implements Connectivity {
    private Graph graph;
    private Set<GraphNode> visited;

    private Set<GraphNode> forrestRoots;

    public ConnectivityImpl(Graph graph){
        this.graph = graph;
        visited = new HashSet<>();
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    @Override
    public List<GraphNode> getSortedItems() {

        List<GraphNode> sortedElements = new Stack<>();

        for(GraphNode source: graph.getSources()){
            if(!visited.contains(source)) {
                forrestRoots.add(source);
                postOrder(sortedElements, source);
            }


        }
        /*

        goal is to perform reverse post order traversal
         */

        return sortedElements;
    }

    private void postOrder(List<GraphNode> sortedElements, GraphNode source) {


        for(GraphNode adjNode: graph.adj(source)){
            postOrder(sortedElements,adjNode);
        }
        sortedElements.add(source);
    }


    @Override
    public List<List<GraphNode>> getAllCombinationsOfSortedItems() {
        return null;
    }

    @Override
    public Map<GraphNode, List<GraphNode>> getSortedItemsPerForest() {


        Map<GraphNode,List<GraphNode>> itemsPerForest = new HashMap<>();
        for(GraphNode source: graph.getSources()){
            if(!visited.contains(source)) {
                forrestRoots.add(source);
                List<GraphNode> sortedElements = new Stack<>();
                postOrder(sortedElements, source);
                itemsPerForest.put(source,sortedElements);
            }


        }

        return itemsPerForest;


    }
}

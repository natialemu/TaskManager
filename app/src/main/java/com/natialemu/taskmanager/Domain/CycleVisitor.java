package com.natialemu.taskmanager.Domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Nathnael on 4/8/2018.
 * Desc: A visitor class that detects cycles in graphs
 */

public class CycleVisitor implements GraphVisitor {
    private Set<GraphNode> visitedNodes;
    private Map<GraphNode,Boolean> pushedToStack;
    private Map<GraphNode, Boolean> poppedFromStack;


    public CycleVisitor(){
        visitedNodes = new HashSet<>();
        pushedToStack = new HashMap<>();
        poppedFromStack = new HashMap<>();
    }


    @Override
    public boolean visit(DAG directedGraph) {

        for(GraphNode gn:directedGraph.getSources()){
            if(!visitedNodes.contains(gn)){
                boolean cyclefound = cycleExists(gn, directedGraph);
                if(!cyclefound)
                    return cyclefound;
            }
        }
        return true;
        /*
        for every source within the graph:
             deligate to a private method to check
         */
    }

    private boolean cycleExists(GraphNode source, DAG directedGraph){

        visitedNodes.add(source);
        pushedToStack.put(source,true);

        for(GraphNode adjNode: directedGraph.adj(source) ){
            if(visitedNodes.contains(adjNode) && verifcyCycle(source,adjNode)){
                return true;
            }
            cycleExists(adjNode,directedGraph);
        }


        poppedFromStack.put(source,true);
        return false;

    }

    private boolean verifcyCycle(GraphNode source, GraphNode adjNode) {

        /**
         * cyle exists if there is an edge to a prior pushed node but not popped node:
         *
         * so adjNode(B) must be already pushed BUT not popped!
         * source must also be pushed AND not popped
         *
         * { [ } ]
         */
        return (!poppedFromStack.get(adjNode) && !poppedFromStack.get(source) && pushedToStack.get(source) && pushedToStack.get(adjNode);


    }
}

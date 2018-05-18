package com.natialemu.taskmanager.Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nathnael on 4/8/2018.
 *
 * Manage how when you have forrests, they're completely unrelated things so they can be done concurrently
 */

public class DAG implements Graph {
    Map<GraphNode, List<GraphNode>> adjMatrix;

    public DAG(){
        adjMatrix = new HashMap<>();
    }

    @Override
    public boolean removeItem(Item item) {

        GraphNode itemNode = buildGraphNode(item);

        if(adjMatrix.containsKey(itemNode)){
            adjMatrix.remove(itemNode);
            for(GraphNode key: adjMatrix.keySet()){
                if(adjMatrix.get(key).contains(itemNode)){
                    adjMatrix.get(key).remove(itemNode);
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public boolean removeEdge(Item source, Item target) {

        GraphNode sourceNode = buildGraphNode(source);
        GraphNode targetNode = buildGraphNode(source);


        if(adjMatrix.containsKey(sourceNode) && adjMatrix.get(sourceNode).contains(targetNode)){

            adjMatrix.get(sourceNode).remove(targetNode);
            return true;

        }

        return false;
    }

    @Override
    public boolean addItem(Item item) {
        /**
         * add it as a source
         */
        GraphNode newItem = buildGraphNode(item);
        assert (!adjMatrix.containsKey(newItem));
        adjMatrix.put(newItem,new ArrayList<GraphNode>());
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
        GraphNode sourceNode = buildGraphNode(source);
        GraphNode targetNode = buildGraphNode(target);
        if(adjMatrix.containsKey(sourceNode)){

            List<GraphNode> neighbors = adjMatrix.get(sourceNode);
            neighbors.add(targetNode);
            adjMatrix.put(sourceNode,neighbors);

        }else{
            List<GraphNode> neighbors = new ArrayList<>();
            neighbors.add(targetNode);
            adjMatrix.put(sourceNode,neighbors);

        }
        return true;
    }

    private GraphNode buildGraphNode(Item item){
        GraphNode newNode = new GraphNode();
        newNode.setItem(item);
        return newNode;
    }

    @Override
    public boolean cycleExists(GraphVisitor visitor) {

        return visitor.visit(this);
    }

    @Override
    public List<GraphNode> adj(GraphNode source) {
        return adjMatrix.get(source);
    }

    @Override
    public List<GraphNode> getSources() {
        List<GraphNode> sources = new ArrayList<>();
        for(GraphNode node: adjMatrix.keySet()){
            if(node.isParentTag()){

                sources.add(node);

            }
        }

        return sources;
    }


}

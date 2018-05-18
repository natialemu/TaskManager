package com.natialemu.taskmanager.Domain;

import com.natialemu.taskmanager.ForestObserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Nathnael on 4/8/2018.
 *
 * Manage how when you have forrests, they're completely unrelated things so they can be done concurrently
 */

public class DAG implements Graph {
    private Map<GraphNode, Set<GraphNode>> adjMatrix;

    private Map<ForestObserver,Set<GraphNode>> forests; //map forest observer nodes to entry points to the forest



    public DAG(){
        adjMatrix = new HashMap<>();
        forests = new HashMap<>();
    }

    /*
    Returns the forest observer of this node
     */
    public ForestObserver find(GraphNode node){//path compression
        //TODO: to be implemented

    }

    public boolean union(GraphNode sourceNode, GraphNode targetNode){

        ForestObserver sourceObserver = find(sourceNode);
        ForestObserver targetObserver = find(targetNode);

        if(!targetNode.isParentTag()){//no parent
            assert (forests.get(targetObserver).contains(targetNode));
            forests.get(targetObserver).remove(targetNode);
            return merge(sourceObserver,targetObserver);

        }

        forests.get(targetObserver).remove(targetNode);
        return merge(sourceObserver,targetObserver);

        /*
        Options:

        1. both are not sources || only sourceNode is a source:
           -->add all sources of smaller forest observer to that of the bigger one
           --> remove small forest observer from map
        2. targetNode is a source:

           remove targetNode from targetObserver first
           perform step 1



         */

    }

    public boolean splitGraph(){
        //TODO: to be implemented

    }

    private boolean merge(ForestObserver sourceObserver, ForestObserver targetObserver) {
        if(forests.get(sourceObserver).size() < forests.get(targetObserver).size()){
            moveSources(sourceObserver,targetObserver);
            forests.remove(sourceObserver);

        }else{
            moveSources(targetObserver,sourceObserver);
            forests.remove(targetObserver);

        }
        return true;
    }

    private void moveSources(ForestObserver from, ForestObserver to) {
        for(GraphNode source : forests.get(from)){
            forests.get(to).add(source);
        }

    }

    @Override
    public boolean removeItem(Item item) {

        GraphNode itemNode = buildGraphNode(item);
        Set<GraphNode> itemChildren = adjMatrix.get(itemNode);

        if(adjMatrix.containsKey(itemNode)){


            adjMatrix.remove(itemNode);

            List<GraphNode> itemParents = new ArrayList<>();

            for(GraphNode key: adjMatrix.keySet()){

                if(adjMatrix.get(key).contains(itemNode)){

                    itemParents.add(key);

                    adjMatrix.get(key).remove(itemNode);

                    return true;

                }

            }

            for(GraphNode parent : itemParents){

                adjMatrix.get(parent).addAll(itemChildren);

            }

        }

        if(!itemNode.isParentTag()){//is a source

            //sef parent flag
            setNodesAsSources(itemChildren);

            for(GraphNode key: adjMatrix.keySet()){

                for(GraphNode child: itemChildren){
                    if(adjMatrix.get(key).contains(child)){
                        child.setParentTag(true);
                    }
                }

            }


            ForestObserver fo =find(itemNode);
            Set<GraphNode> neighbors = adjMatrix.get(itemNode);
            forests.get(fo).remove(itemNode);


            for(GraphNode nd : neighbors){
                if(!nd.isParentTag()){
                    forests.get(fo).add(nd);//new sources
                }

            }

            if(neighbors.size() == 0){
                forests.remove(fo);
            }
        }
        return false;
    }

    private void setNodesAsSources(Set<GraphNode> itemChildren) {
        Iterator<GraphNode> nodeIterator = itemChildren.iterator();

        while (nodeIterator.hasNext()){
            GraphNode nd = nodeIterator.next();
            nd.setParentTag(false);
        }
    }

    @Override
    public boolean removeEdge(Item source, Item target) {

        GraphNode sourceNode = buildGraphNode(source);
        GraphNode targetNode = buildGraphNode(source);


        if(adjMatrix.containsKey(sourceNode) && adjMatrix.get(sourceNode).contains(targetNode)){

            adjMatrix.get(sourceNode).remove(targetNode);
            boolean newForest = verifyPotentialSource(adjMatrix.get(sourceNode), targetNode);//through DFS
            if(newForest){
                //Call the split graph method
                ForestObserver currentObserver = find(sourceNode);
                ForestObserver newObserver = new ForestObserver();
                Set<GraphNode> s = new HashSet<>();
                s.add(targetNode);

                //some sources from current observer might transfer over to new observer
                removeSourcesFromCurrentObserver(currentObserver,targetNode)//Through DFS
                forests.put(newObserver,s);
            }
            return true;

        }


        /**
         * 1. Target could be a source
         * 2. How to efficienty tell if a graph rooted at target is a forest?
         *     potential new sources are all the children of source so
         *       1. perform dfs from target marking everything
         *       2. then perform dfs from the remaining potential sources,
         *           - if the process does not visit an already explored node, then we have a new forest
         *
         *
         */

        return false;
    }

    @Override
    public boolean addItem(Item item) {
        /**
         * add it as a source
         */
        GraphNode newItem = buildGraphNode(item);
        assert (!adjMatrix.containsKey(newItem));
        adjMatrix.put(newItem,new HashSet<GraphNode>());
        Set<GraphNode> newSource = new HashSet<>();
        newSource.add(newItem);
        forests.put(new ForestObserver(),newSource);
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

            Set<GraphNode> neighbors = adjMatrix.get(sourceNode);
            neighbors.add(targetNode);
            adjMatrix.put(sourceNode,neighbors);

        }else{
            Set<GraphNode> neighbors = new HashSet<>();
            neighbors.add(targetNode);
            adjMatrix.put(sourceNode,neighbors);

        }


        union(sourceNode,targetNode);
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
        List<GraphNode> sourceNeighbors = new ArrayList<>();
        sourceNeighbors.addAll(adjMatrix.get(source));
        return sourceNeighbors;
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

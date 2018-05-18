package com.natialemu.taskmanager.Domain;

import com.natialemu.taskmanager.ForestObserver;

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

    Map<GraphNode, GraphNode> forestIdentifiers;
    /*

    After edge removal, algorithm for determining the name of the new forest:

    1. create a temporary forest observer and assign the removed node(targetNode) to that observer
    2. Conduct DFS from every source:
        current Source
        if DFS from a source finds/intersects with a node that belongs to temporary observer, modify every node when returning to belong
        to temporary observer
        otherwise:
           add that source to its own forest

     */

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

    @Override
    public boolean removeEdge(Item source, Item target) {
        GraphNode sourceNode = buildGraphNode(source);
        GraphNode targetNode = buildGraphNode(source);

        ForestObserver currentObserver = find(targetNode);
        graph.removeEdge(sourceNode,targetNode,currentObserver);

        displayForrests();



        int numOfForests = new HashSet<>(forestIdentifiers.values()).size();
        if(numOfForests - 1 == graph.getNumberOfForests()){//a new forest is formed

            splitGraph(currentObserver, sourceNode,targetNode);

        }



//        if(graph.containsNode(sourceNode) && graph.adj(sourceNode).contains(targetNode)) {
//
//            graph.adj(sourceNode).remove(targetNode);
//            boolean newForest = verifyPotentialSource(graph.adj(sourceNode), targetNode);//through DFS
//            if (newForest) {
//                //Call the split graph method
//                ForestObserver currentObserver = find(sourceNode);
//                ForestObserver newObserver = new ForestObserver();
//                Set<GraphNode> s = new HashSet<>();
//                s.add(targetNode);
//
//                //some sources from current observer might transfer over to new observer
//                removeSourcesFromCurrentObserver(currentObserver, targetNode)//Through DFS
//                graph.addNewObserver(newObserver, s);
//            }
//        }
//        return true;

        return true;

    }

    private void displayForrests() {

        for(GraphNode source : graph.vertices()){
            if(!visited.contains(source))
                modifiedDFS(source,source);
        }
    }

    private void modifiedDFS(GraphNode current, GraphNode source) {
        forestIdentifiers.put(current,source);
        visited.add(current);

        for(GraphNode adjNode: graph.adj(current)){
            if(visited.contains(adjNode)){
                source = forestIdentifiers.get(adjNode);
                forestIdentifiers.put(current,source);
            }
            modifiedDFS(adjNode,source);
        }
        forestIdentifiers.put(current,source);
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
        if(graph.containsNode(sourceNode)){

            Set<GraphNode> neighbors = graph.adj(sourceNode);
            neighbors.add(targetNode);
            graph.addEdge(sourceNode,neighbors);

        }else{
            Set<GraphNode> neighbors = new HashSet<>();
            neighbors.add(targetNode);
            graph.addEdge(sourceNode,neighbors);

        }


        union(sourceNode,targetNode);
        return true;
    }

    @Override
    public boolean union(GraphNode sourceNode, GraphNode targetNode) {
        ForestObserver sourceObserver = find(sourceNode);
        ForestObserver targetObserver = find(targetNode);

        if(!targetNode.isParentTag()){//no parent
            assert (graph.getForestSources(targetObserver).contains(targetNode));
            graph.getForestSources(targetObserver).remove(targetNode);
            return merge(sourceObserver,targetObserver);

        }

        graph.getForestSources(targetObserver).remove(targetNode);
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

    private boolean merge(ForestObserver sourceObserver, ForestObserver targetObserver) {
        if(graph.getForestSources(sourceObserver).size() < graph.getForestSources(targetObserver).size()){
            moveSources(sourceObserver,targetObserver);
            graph.removeSources(targetObserver);

        }else{
            moveSources(targetObserver,sourceObserver);
            graph.removeSources(targetObserver);

        }
        return true;
    }

    private void moveSources(ForestObserver from, ForestObserver to) {
        for(GraphNode source : graph.getForestSources(from)){
            graph.getForestSources(to).add(source);
        }

    }

    @Override
    public ForestObserver find(GraphNode node) {
        return null;
    }

    private boolean splitGraph(ForestObserver currentObserver, GraphNode sourceNode, GraphNode targetNode){
        ForestObserver newObserver = new ForestObserver();
        Set<GraphNode> newSources = new HashSet<>();
        newSources.add(targetNode);

        graph.addNewObserver(newObserver,newSources);
        graph.removeSourceFromObserver(currentObserver,targetNode);

        Set<GraphNode> potentialSources = graph.getForestSources(currentObserver);
        init(visited);

        Set<GraphNode> newVisited = new HashSet<>();

        Set<GraphNode> oldVisisted = new HashSet<>();

        modifiedDFS(targetNode,targetNode);
        newVisited.addAll(visited);

        modifiedDFS(sourceNode,sourceNode);
        oldVisisted.addAll(visited);

        init(visited);
        List<GraphNode> sources = graph.getSources();
        for(GraphNode s: sources) {
            if(!visited.contains(s))
                modifiedDFS2(newObserver,currentObserver, s,s,sources,oldVisisted,newVisited);
            init(visited);


        }

        return true;

    }

    private void modifiedDFS2(ForestObserver newObserver, ForestObserver currentObserver, GraphNode s, GraphNode source, List<GraphNode> sources, Set<GraphNode> oldVisisted, Set<GraphNode> newVisited) {

        visited.add(s);

        for(GraphNode adjNode: graph.adj(s)){
            if(visited.contains(adjNode)){

                if(newVisited.contains(adjNode)){

                    graph.removeSourceFromObserver(currentObserver,source);
                    graph.addSourceToObserver(newObserver,source);
                    newVisited.addAll(visited);

                }else{
                    oldVisisted.addAll(visited);
                }
                if(sources.contains(source))
                    sources.remove(source);
                continue;
            }
            modifiedDFS(adjNode,source);
        }



    }

    private void init(Set<GraphNode> visited) {
        visited = new HashSet<>();
    }

    private GraphNode buildGraphNode(Item item){
        GraphNode newNode = new GraphNode();
        newNode.setItem(item);
        return newNode;
    }

}

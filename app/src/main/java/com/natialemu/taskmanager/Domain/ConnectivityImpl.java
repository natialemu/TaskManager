package com.natialemu.taskmanager.Domain;

import com.natialemu.taskmanager.ForestObserver;

import java.util.ArrayList;
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

    private Map<GraphNode, GraphNode> forestIdentifiers;



    public ConnectivityImpl(Graph graph){
        this.graph = graph;
        visited = new HashSet<>();
        forestIdentifiers = new HashMap<>();
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    /*
    A random post order traversal --> topological sort. However, for multiple forests, each topological sort comes
    one after the other
     */
    @Override
    public List<GraphNode> getSortedItems() {



       return getSortedItemPersource(new HashSet<GraphNode>(graph.getSources()));


    }

    private List<GraphNode> getSortedItemPersource(Set<GraphNode> sources){

        List<GraphNode> sortedElements = new Stack<>();
        for(GraphNode source: sources){
            if(!visited.contains(source)) {


                postOrder(sortedElements, source);

            }


        }
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
        //TODO: to be implemented
        return null;
    }

    /*
    Gets topological sorts of all forests in the graph
     */
    @Override
    public List<List<GraphNode>> getSortedItemsPerForest() {





        List<List<GraphNode>>  topologicalSorts = new ArrayList<>();
        for(ForestObserver fo : graph.getObservers()){

            List<GraphNode> topologicalSortPerForest = getSortedItemPersource(graph.getForestSources(fo));
            topologicalSorts.add(topologicalSortPerForest);


        }

        return topologicalSorts;


    }

    @Override
    public boolean removeEdge(Item source, Item target) {

        GraphNode sourceNode = buildGraphNode(source);
        GraphNode targetNode = buildGraphNode(source);

        ForestObserver currentObserver = find(targetNode);

        graph.removeEdge(sourceNode,targetNode,currentObserver);

        displayForrests();// this will reveal any new forests that result from the edge removal



        int numOfForests = new HashSet<>(forestIdentifiers.values()).size();
        if(numOfForests - 1 == graph.getNumberOfForests()){//a new forest is formed

            splitGraph(currentObserver, sourceNode,targetNode);

        }

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

        Set<GraphNode> potentialNewSources = graph.getForestSources(currentObserver);
        init(visited);

        Set<GraphNode> newVisited = new HashSet<>(); // visited in the new cluster that broke off

        Set<GraphNode> oldVisisted = new HashSet<>(); // visited nodes in the old forest

        modifiedDFS(targetNode,targetNode); // marks all nodes reachable from target node
        newVisited.addAll(visited); // add all reachable nodes from target node - aka in new forest


        modifiedDFS(sourceNode,sourceNode);// mark source node which is in the old cluster
        oldVisisted.add(sourceNode);

        init(visited);
        for(GraphNode s: potentialNewSources) {
            if(!visited.contains(s))
                modifiedDFS2(newObserver,currentObserver, s,s,new ArrayList<GraphNode>(potentialNewSources),oldVisisted,newVisited);
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

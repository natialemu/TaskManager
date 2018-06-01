package com.natialemu.taskmanager.Domain;

import java.util.ArrayList;
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

    @Override
    public boolean removeItem(Item item) {



        GraphNode itemNode = buildGraphNode(item);
        Set<GraphNode> itemChildren = adjMatrix.get(itemNode);
        removeParentFromChildren(itemNode);

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

        if(!itemNode.hasParent()){//is a source

            //sef parent flag
            setNodesAsSources(itemChildren);

            for(GraphNode key: adjMatrix.keySet()){

                for(GraphNode child: itemChildren){
                    if(adjMatrix.get(key).contains(child)){
                        child.addParent(key);
                    }
                }

            }


            ForestObserver fo = getSourceObserver(itemNode);

            assert (fo != null);

            Set<GraphNode> neighbors = adjMatrix.get(itemNode);
            forests.get(fo).remove(itemNode);


            for(GraphNode nd : neighbors){
                if(!nd.hasParent()){
                    forests.get(fo).add(nd);//new sources
                }

            }

            if(neighbors.size() == 0){
                forests.remove(fo);
            }
        }
        return false;
    }

    private void removeParentFromChildren(GraphNode itemNode) {
        for(GraphNode gn: adjMatrix.get(itemNode)){
            assert (gn.isMyParent(itemNode));
            gn.removeParent(itemNode);
        }
    }

    private ForestObserver getSourceObserver(GraphNode itemNode) {
        for(ForestObserver forestObserver: forests.keySet()){
            if(forests.get(forestObserver).contains(itemNode)){
                return forestObserver;
            }
        }

        return null;
    }

    private void setNodesAsSources(Set<GraphNode> itemChildren) {
        Iterator<GraphNode> nodeIterator = itemChildren.iterator();

        while (nodeIterator.hasNext()){
            GraphNode nd = nodeIterator.next();
            nd.removeAllParents();
        }
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
    public Set<GraphNode> adj(GraphNode source) {

        return adjMatrix.get(source);
    }

    @Override
    public List<GraphNode> getSources() {
        List<GraphNode> sources = new ArrayList<>();
        for(GraphNode node: adjMatrix.keySet()){
            if(node.hasParent()){

                sources.add(node);

            }
        }

        return sources;
    }

    @Override
    public Set<GraphNode> getForestSources(ForestObserver targetObserver) {
        return forests.get(targetObserver);
    }

    @Override
    public void removeSources(ForestObserver targetObserver) {
        forests.remove(targetObserver);
    }

    @Override
    public void addNewObserver(ForestObserver newObserver, Set<GraphNode> s) {
        forests.put(newObserver,s);
    }

    @Override
    public boolean containsNode(GraphNode sourceNode) {
        return adjMatrix.containsKey(sourceNode);
    }

    @Override
    public void addEdge(GraphNode sourceNode, Set<GraphNode> neighbors) {
        adjMatrix.put(sourceNode,neighbors);
    }

    @Override
    public void removeEdge(GraphNode source, GraphNode destination, ForestObserver currentObserver) {
        assert adjMatrix.containsKey(source);
        adjMatrix.get(source).remove(destination);

        destination.removeAllParents();//assume target node is a source
        for(GraphNode key: adjMatrix.keySet()){
            if(adjMatrix.get(key).contains(destination)){
                destination.addParent(key);
            }
        }

        if(!destination.hasParent()){//if target node is still a parent

            forests.get(currentObserver).add(destination);

        }

    }

    @Override
    public Set<GraphNode> getParents(GraphNode sourceNode) {


        return new HashSet<>(sourceNode.getParents());
    }

    @Override
    public int getNumberOfForests() {
        return forests.size();
    }

    @Override
    public Set<GraphNode> vertices() {
        return adjMatrix.keySet();
    }

    @Override
    public void removeSourceFromObserver(ForestObserver currentObserver, GraphNode targetNode) {
        forests.get(currentObserver).remove(targetNode);
    }

    @Override
    public void addSourceToObserver(ForestObserver newObserver, GraphNode source) {
        forests.get(newObserver).add(source);
    }

    @Override
    public Set<ForestObserver> getObservers() {
        return forests.keySet();
    }

    @Override
    public ForestObserver getObserverForSource(GraphNode source) {
        assert (!source.hasParent());
        for(ForestObserver fo: forests.keySet()){
            if(forests.get(fo).contains(source)){
                return fo;
            }
        }
        return null;
    }


}

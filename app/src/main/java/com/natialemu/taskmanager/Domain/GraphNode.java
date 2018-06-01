package com.natialemu.taskmanager.Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Nathnael on 4/9/2018.
 */

public class GraphNode {

    private Item item;
    List<GraphNode> parents;

    public GraphNode(){

        item = new Item();
        parents = new ArrayList<>();
    }

    public GraphNode(Item item){
        this.item = item;
        parents = new ArrayList<>();


    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean hasParent() {
        return parents.size() != 0;
    }

    public int getPriority(){
        return item.getPriority();
    }

    public boolean isMyParent(GraphNode graphNode){return parents.contains(graphNode);}
    public List<GraphNode> getParents(){return parents; }
    public void addParent(GraphNode graphNode){
        parents.add(graphNode);
    }

    public  void addParents(Collection<GraphNode> parents){
        parents.addAll(parents);
    }

    public void removeParent(GraphNode graphNode){
        parents.remove(graphNode);
    }
    public void removeAllParents(){
        parents = new ArrayList<>();
    }

    public GraphNode getOneParent() {
        return parents.get(0);
    }
}

package com.natialemu.taskmanager.Domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathnael on 4/9/2018.
 */

public class GraphNode {
    List<GraphNode> neighbors;
    Item item;
    boolean parentTag;//indicates it has a parent

    public GraphNode(){
        neighbors = new ArrayList<>();
        item = new Item();
    }

    public List<GraphNode> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<GraphNode> neighbors) {
        this.neighbors = neighbors;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean isParentTag() {
        return parentTag;
    }

    public void setParentTag(boolean parentTag) {
        this.parentTag = parentTag;
    }

    public int getPriority(){
        return item.getPriority();
    }
}

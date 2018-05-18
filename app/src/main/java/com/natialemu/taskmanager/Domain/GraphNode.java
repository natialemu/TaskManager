package com.natialemu.taskmanager.Domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathnael on 4/9/2018.
 */

public class GraphNode {

    private Item item;
    private boolean parentTag;//indicates it has a parent

    public GraphNode(){

        item = new Item();
        parentTag = false;
    }

    public GraphNode(Item item){
        this.item = item;
        parentTag = false;

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

package com.natialemu.taskmanager;

import com.natialemu.taskmanager.Domain.GraphNode;
import com.natialemu.taskmanager.Domain.Tools.IDGenerator;

import java.util.Objects;

public final class ForestObserver extends GraphNode {
    private int observerID;
    public ForestObserver(){
        super(null);
        observerID = IDGenerator.getNewID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForestObserver that = (ForestObserver) o;
        return observerID == that.observerID;
    }

    @Override
    public int hashCode() {

        return Objects.hash(observerID);
    }
}

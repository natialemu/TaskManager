package com.natialemu.taskmanager.Domain;

import com.natialemu.taskmanager.Domain.Tools.IDGenerator;

import java.util.Objects;

public final class ForestObserver extends GraphNode {
    private int observerID;
    private boolean observingReccuringTasks;

    public int getObserverID() {
        return observerID;
    }

    public void setObserverID(int observerID) {
        this.observerID = observerID;
    }

    public boolean isObservingReccuringTasks() {
        return observingReccuringTasks;
    }

    public void setObservingReccuringTasks(boolean observingReccuringTasks) {
        this.observingReccuringTasks = observingReccuringTasks;
    }

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

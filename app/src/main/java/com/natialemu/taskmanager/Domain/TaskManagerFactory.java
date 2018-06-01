package com.natialemu.taskmanager.Domain;

import java.util.List;

/**
 * Created by Nathnael on 4/8/2018.
 *
 * Facade for the Domain Layer
 */

public class TaskManagerFactory {
    private Graph taskGraph;
    private Connectivity connectivity;

    public TaskManagerFactory(){
        taskGraph = new DAG();
        connectivity = new ConnectivityImpl(taskGraph);
    }
    /**
     *
     * Should have the ff functionalities:
     *
     * 1. create dependency b/n two tasks
     * 2. add a task to your list
     * 3. remove a task --> readjust dependency
     * 4. get preffered order of tasks
     * 5. get a collection of task orders
     * 6. get concurrent collection of tasks to complete
     * 7. get individual concurrent executable tasks
     * 8. get all active tasks
     *
     *
     */

    public boolean createDependency(Item parentItem, Item childItem){
        return connectivity.addEdge(parentItem,childItem);
    }

    public boolean addTask(Item item){
        return taskGraph.addItem(item);
    }

    public boolean removeTask(Item item){
        return taskGraph.removeItem(item);
    }

    public boolean removeDependency(Item parentItem, Item childItem){
        return connectivity.removeEdge(parentItem,childItem);
    }

    public List<Item> getOptimalOrderOfTasks(){
        return null;

    }

    public List<Item> getIndependentSetOfTasks(){
        return null;
    }

    public List<Item> getFirstPriorityTasks(){
        return null;
    }

    public List<Item> getAllTasks(){
        return null;
    }

    public List<Item> getAllReccuringTasks(){return null;}
    public List<Item> getTopNReccuringTasks(int n){return null;}
}

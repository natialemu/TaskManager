package com.natialemu.taskmanager.DataAccess;

import com.natialemu.taskmanager.Domain.ForestObserver;
import com.natialemu.taskmanager.Domain.GraphNode;

import java.sql.Connection;
import java.util.List;

public class ObserverDAO {
    /**
     * Will contain information about observers:
     *   1. Their sources
     *   2. Thier id
     *   3. weather they observe recurring tasks or not
     */

    public List<ForestObserver> getAllObservers(){
        Connection connection = DBConnect.getDatabaseConnection();
        return null;



    }

    public List<GraphNode> geSourcesObservedBy(int observer_id){
        Connection connection = DBConnect.getDatabaseConnection();

        return null;

    }
}

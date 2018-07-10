package com.natialemu.taskmanager.DataAccess;

import com.natialemu.taskmanager.Domain.Item;

import java.sql.Connection;
import java.util.List;

public class ItemRelationShipDAO {

    public List<Item> getChildren(int item_id){
        Connection connection = DBConnect.getDatabaseConnection();

        return null;

    }

    public List<Item> getParents(int item_id){
        Connection connection = DBConnect.getDatabaseConnection();

        return null;
    }

    public void addEdge(int source_item_id, int destination_item_id){
        Connection connection = DBConnect.getDatabaseConnection();


    }

    public void deleteEdge(int source_ite_id, int destination_item_id){
        Connection connection = DBConnect.getDatabaseConnection();


    }
}

/**
 *
 * This contains information about an items relation with respect to other items such as:
 *    1. An item's neighbors
 *    2. An item's parents
 *    3.
 *
 */

package com.natialemu.taskmanager.Domain;

import org.junit.Before;
import org.junit.Test;

public class ConnectivityTest {
    private DAG graph;
    private  ConnectivityImpl connectivityManager;

    @Before
    public void setUp(){
        graph = new DAG();
        connectivityManager = new ConnectivityImpl(graph);
    }


    @Test
    public void testGetSortedItemPersource(){
        //TODO
    }

    @Test
    public void testPostOrder(){
        //TODO
    }



    @Test
    public void testGetAllCombinationsOfSortedItems(){
        //TODO
    }

    @Test
    public void testGetSortedItemsPerForest(){
        //TODO
    }

    @Test
    public void testRemoveEdge(){
        //TODO
    }

    @Test
    public void testModifiedDFS(){
        //TODO
    }

    @Test
    public void testUnion(){
        //TODO
    }

    @Test
    public void testMerge(){
        //TODO
    }

    @Test
    public void testFind(){
        //TODO
    }

    @Test
    public void testSplitGraph(){
        //TODO
    }

    @Test
    public void testModifedDFS2(){
        //TODO
    }
}

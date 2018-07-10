package com.natialemu.taskmanager.DataAccess;

import com.natialemu.taskmanager.Domain.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class ItemDAO {

    public boolean saveItem(Item item){
        boolean inserted = false;
        Connection connection = DBConnect.getDatabaseConnection();
        try{
            Statement insertSatement = connection.createStatement();

            String insertQuery = "INSERT INTO item_desc (item_id,item_category,priority,title," +
                    "tag,is_recurring_task,anticipated_completion_date,item_creation_date,note)" +
                    " VALUES("+item.getItemID()+", '" +
                    ""+item.getCategory()+"', "+item.getPriority()+", '" +
                    item.getTitle()+"', '" +
                    item.getTag()+"', " + item.isReccuringTask()+ ", " +
                    item.getAnticipatedCompletionDate() + ", " + item.getCreationDate() + ", '" +
                    item.getNotes() + "')";
            insertSatement.executeUpdate(insertQuery);

            inserted = true;
        }catch (SQLException se){
            se.printStackTrace();

        }finally {
            if(connection != null){
                try {
                    connection.close();

                }catch (Exception e){}

            }
        }

        return inserted;

    }

    public boolean updateItem(Item item, int item_id){
        Connection connection = DBConnect.getDatabaseConnection();
        boolean updated = false;

        try{
            Statement insertSatement = connection.createStatement();

            String updateQuery = "UPDATE item_desc SET priority=" + item.getPriority() + ", " +
                    "title='"+item.getTitle() + "', item_category='"+item.getCategory() + "', " +
                    "tag='"+item.getTag() +"', is_recurring_task=" + item.isReccuringTask() + ", " +
                    "anticipated_completion_date=" + item.getAnticipatedCompletionDate() + ", "+
                    "item_creation_date=" + item.getCreationDate() + ", note='"+item.getNotes()+"'," +
                    " WHERE item_id=" + item_id;
            insertSatement.executeUpdate(updateQuery);

            updated = true;
        }catch (SQLException se){
            se.printStackTrace();

        }finally {
            if(connection != null){
                try {
                    connection.close();

                }catch (Exception e){}

            }
        }

        return updated;


    }

    public Item getItem(int item_id){
        int priority = 0;
        String title = "";
        String category = "";
        String tag = "";
        boolean isReccuringTask = false;
        Date anticipatedCreationDate = null;
        Date creationDate = null;
        String notes = null;




        Connection connection = DBConnect.getDatabaseConnection();
        try {
            Statement selectStatement = connection.createStatement();

            String selectQuery = "SELECT * from item_desc where item_id=" + item_id;
            ResultSet resultSet = selectStatement.executeQuery(selectQuery);
            resultSet.next();

//            body = resultSet.getString("body");
//            header = resultSet.getString("header");
            priority = resultSet.getInt("priority");
            title = resultSet.getString("title");
            tag = resultSet.getString("tag");
            category = resultSet.getString("item_category");
            isReccuringTask = resultSet.getBoolean("is_reccuring_task");
            anticipatedCreationDate = resultSet.getDate("anticipated_completion_date");
            creationDate = resultSet.getDate("item_creation_date");
            notes = resultSet.getString("note");


        } catch (SQLException se) {
            se.printStackTrace();
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
        Item item = new Item();
        item.setPriority(priority);
        item.setItemID(Integer.toString(item_id));
        item.setAnticipatedCompletionDate(anticipatedCreationDate);
        item.setCreationDate(creationDate);
        item.setCategory(category);
        item.setReccuringTask(isReccuringTask);
        item.setTag(tag);
        item.setTitle(title);
        item.setNotes(notes);
        //notes not created


        return item;

    }

    public boolean deleteItem(Item item){
        //TODO
        return false;

    }
}

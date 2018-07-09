package com.natialemu.taskmanager.DataAccess;

import com.natialemu.taskmanager.Domain.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemDAO {

    public boolean saveItem(Item item){
        boolean inserted = false;
        Connection connection = DBConnect.getDatabaseConnection();
        try{
            Statement insertSatement = connection.createStatement();

            String insertQuery = "INSERT INTO order_confirmation (confirmation_id,header,body) VALUES("+orderConfirmation.getConfirmationID()+", '"+orderConfirmation.getMessageHeader()+"', '"+orderConfirmation.getMessageBody()+"')";
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

    public boolean updateItem(Item item){
        Connection connection = DBConnect.getDatabaseConnection();
        boolean updated = false;

        try{
            Statement insertSatement = connection.createStatement();

            String updateQuery = "UPDATE placed_order SET order_status='" + retrievedOrder.getStringOrderState() + "" +
                    "' WHERE order_confirmation_id=" + confirmation_id;
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

    public Item getItem(Item item){

        Connection connection = DBConnect.getDatabaseConnection();
        try {
            Statement selectStatement = connection.createStatement();

            String selectQuery = "SELECT * from order_confirmation where confirmation_id=" + confirmationID;
            ResultSet resultSet = selectStatement.executeQuery(selectQuery);
            resultSet.next();

//            body = resultSet.getString("body");
//            header = resultSet.getString("header");

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* ignored */}
            }
        }

        return null;

    }

    public boolean deleteItem(Item item){
        return false;

    }
}

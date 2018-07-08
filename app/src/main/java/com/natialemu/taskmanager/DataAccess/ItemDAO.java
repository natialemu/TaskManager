package com.natialemu.taskmanager.DataAccess;

import com.natialemu.taskmanager.Domain.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class ItemDAO {

    /**
     * This contains information about individual items only
     * Filename: item
     *
     * behaviors:
     * 1. open file
     * 2. save to file
     * 3. add to file
     * 4. delete entry from file
     * 5. update entry from file
     * 6. get entry from file
     */

    public boolean saveItem(Item item){



        boolean inserted = false;
        try {
            Statement selectStatement = connection.createStatement();

            String insertQuery = "INSERT INTO delivery (delivery_id,delivery_conf_number,order_id,delivery_date) VALUES" +
                    "("+delivery.getDeliveryID()+", "+delivery.getDeliveryConfirmation().getDeliveryID()+", " +
                    ""+delivery.getOrder().getOrderID()+","+getDateSring(delivery.getDeliveryCreationDate())+")";

            insertSatement.executeUpdate(insertQuery);

            inserted = true;

//            tracking_number = resultSet.getInt("delivery_tracking_no");
//            order_id = resultSet.getInt("order_id");
//            confirmation_id = resultSet.getInt("delivery_conf_number");
//            deliveryCreationDate = resultSet.getDate("delivery_date");

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
        return inserted;


    }

    public void updateItem(Item item){
        Connection connection = DBConnect.getDatabaseConnection();


        try {
            Statement updateSqlStatement = connection.createStatement();
            String updateQuery = "UPDATE placed_order SET order_status='" + retrievedOrder.getStringOrderState() + "' WHERE order_confirmation_id=" + confirmation_id;
            updateSqlStatement.executeUpdate(updateQuery);

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* ignored */}
            }
        }

    }

    public void deleteItem(Item item){
        Connection connection = DBConnect.getDatabaseConnection();

        //TODO: implement this

    }

    public Item getItem(int itemID){
        /**
         *
         * List the item attributes here
         */

        Connection connection = DBConnect.getDatabaseConnection();

        try {
            Statement selectStatement = connection.createStatement();

            String selectQuery = "SELECT * from delivery where delivery_id=" + delivery_tracking_id;
            ResultSet resultSet = selectStatement.executeQuery(selectQuery);
            resultSet.next();

            tracking_number = resultSet.getInt("delivery_tracking_no");
            order_id = resultSet.getInt("order_id");
            confirmation_id = resultSet.getInt("delivery_conf_number");
            deliveryCreationDate = resultSet.getDate("delivery_date");

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

    }

    private String getDateSring(java.util.Date date) {
        String stringDate =  new SimpleDateFormat("yyyyMMdd").format(date);
        return stringDate;
    }


}

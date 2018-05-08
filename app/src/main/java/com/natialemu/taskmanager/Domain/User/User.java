package com.natialemu.taskmanager.Domain.User;

import com.natialemu.taskmanager.Domain.Item;

import java.util.List;

/**
 * Created by Nathnael on 4/8/2018.
 */

public class User {
    private String fullName;
    private String username;
    private String password;
    private List<Item> items;

    public User(String fullName, String username, String password) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

    public User(String fullName, String username, String password, List<Item> items) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.items = items;

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


/**
     * A user has a collection of items
     *
     */


}

package com.natialemu.taskmanager.Domain;

import java.util.Date;

/**
 * Created by Nathnael on 4/8/2018.
 * Future:a item can be in three states(priority may change along the way) --> Future plan
 *
 * 1. new
 * 2. completed
 * 3. revisited
 *
 */

public class Item {
    private  String category;
    private int priority;
    private String notes;
    private Date anticipatedCompletionDate;
    private Date creationDate;

    public Item(String category, int priority, String notes, String completitionDate, String creationDate){
        this.category = category;
        this.priority = priority;
        this.notes = notes;
        this.anticipatedCompletionDate = new Date(completitionDate);
        this.creationDate = new Date(completitionDate);

    }
    public Item(){
        category="";
        priority = 0;
        notes = "";
        anticipatedCompletionDate = new Date();
        creationDate = new Date();

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getAnticipatedCompletionDate() {
        return anticipatedCompletionDate;
    }

    public void setAnticipatedCompletionDate(Date anticipatedCompletionDate) {
        this.anticipatedCompletionDate = anticipatedCompletionDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (priority != item.priority) return false;
        if (category != null ? !category.equals(item.category) : item.category != null)
            return false;
        if (notes != null ? !notes.equals(item.notes) : item.notes != null) return false;
        if (anticipatedCompletionDate != null ? !anticipatedCompletionDate.equals(item.anticipatedCompletionDate) : item.anticipatedCompletionDate != null)
            return false;
        return creationDate != null ? creationDate.equals(item.creationDate) : item.creationDate == null;

    }

    @Override
    public int hashCode() {
        int result = category != null ? category.hashCode() : 0;
        result = 31 * result + priority;
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (anticipatedCompletionDate != null ? anticipatedCompletionDate.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }
}

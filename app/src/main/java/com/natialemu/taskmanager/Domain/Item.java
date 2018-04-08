package com.natialemu.taskmanager.Domain;

import java.util.Date;

/**
 * Created by Nathnael on 4/8/2018.
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
}

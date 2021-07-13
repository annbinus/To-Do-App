/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Ann Binus
 */
package ucf.assignments;

import java.util.ArrayList;

public class Item {
    private String description;
    private String dueDate;

    public String status;


    public Item(String description, String dueDate, String Status) {

        this.description = description;
        this.dueDate = dueDate; // format as shared
        this.status = Status;
    }

    //getter and setter methods for description
    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    //getter and setter methods for  dueDate
    public String getDueDate() {
        return dueDate;
    }


    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }


    //getter and setter methods for status
    public String getStatus() {
        return status;
    }


    public void setStatus(String complete) {
        this.status = complete;
    }


    //return string form of the contents in Item to write on file
    public String toString(){
        return this.description+","+this.dueDate+","+this.status;
    }
}

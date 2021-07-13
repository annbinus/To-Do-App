/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Ann Binus
 */
package ucf.assignments;

import javafx.collections.ArrayChangeListener;

import java.util.ArrayList;

public class List {

    private String title;
    private int storage;

    public int count;

    ArrayList<Item> itemList;

    public List(String title, int Storage) {
        this.title = title;
        this.storage = Storage;

        // use count to track the number of items in the list
        count = 0;

        //initialize a new arraylist to add "Item" object
        itemList = new ArrayList<>();
    }

    //add "Item" object to the list
    public void addItem(Item i){
        itemList.add(i);
        count++;
    }

    //remove "Item" object from the list
    public void removeItem(Item i){
        itemList.remove(i);
        count--;
    }

    //getter and setter methods for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBalance() {
        return storage-count;
    }

    public void setCapacity(int capacity) {
        this.storage = capacity;
    }

    public void editTitle(String newTitle){
        this.title = newTitle;
    }

    public void clearAllItems(){
        //reset the Item ArrayList
        itemList = new ArrayList<>();
        //reset counter to 0
        count = 0;
    }


    public void editDescription(Item item, String str){
        int flag = 0;

        //find task that contains matching description
        for(int i = 0; i<count; i++){

            if(item.equals(getItems().get(i))) {
                flag = i;
                break;
            }
        }

        //set description with new value
        getItems().get(flag).setDescription(str);
    }

    public void editDueDate(Item item , String str){
        int flag = 0;

        //find task that contains matching due date
        for(int i = 0; i<count; i++){

            if(item.equals(getItems().get(i))) {
                flag = i;
                break;
            }
        }

        //set due date with new value
        getItems().get(flag).setDueDate(str);
    }

    public void editStatus(Item item, String str){
        int flag = 0;

        //find task that contains matching due date
        for(int i = 0; i<count; i++){

            if(item.equals(getItems().get(i))) {
                flag = i;
                break;
            }
        }
        //set status with new value
        getItems().get(flag).setStatus(str);

    }

    //return items
    public ArrayList<Item> getItems(){
        return itemList;
    }

    //return complete item list
    public ArrayList<Item> getCompleteItems(){

        ArrayList<Item> completeItems = new ArrayList<>();

        //check for complete items and add it to the completeItems list
        for(Item item : getItems()){
            if(item.getStatus().equals("complete")){
                completeItems.add(item);
            }
        }

        return completeItems;
    }

    //get incomplete item list
    public ArrayList<Item> getIncompleteItems() {

        ArrayList<Item> incompleteItems = new ArrayList<>();

        //check for incomplete items and add it to the incompleteItems list
        for (Item item : getItems()) {
            if (item.getStatus().equals("incomplete")) {
                incompleteItems.add(item);
            }
        }

        return incompleteItems;
    }

}



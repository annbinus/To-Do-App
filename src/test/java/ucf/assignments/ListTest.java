/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Ann Binus
 */
package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ListTest {

    List list = new List("My List",100);

    private void load()  {

        File inputFile = new File("files/test.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(scan.hasNext()){
            String text = scan.nextLine();
            String[] item = text.split(",");
            list.addItem(new Item(item[0],item[1],item[2]));
        }
    }

    @Test
    void getTitleTest() {
        load();
        assertEquals("My List",list.getTitle());
    }

    @Test
    void setTitleTest()  {
        load(); 
        list.setTitle("My List");
        assertEquals("My List",list.getTitle()); 
    }

    @Test
    void getBalanceTest(){
        load(); 
        assertEquals(92,list.getBalance()); 
    }

    @Test
    void setBalanceTest()  {
        load(); 
        list.setBalance(90); 
        assertEquals(82, list.getBalance()); 
    }



    @Test
    void addItemTest() {
        load();

        //create Item item object
        Item item = new Item( "description", "2002-09-04", "complete");
        list.addItem(item);

        assertEquals(item, list.getItems().get(list.count-1));
    }

    @Test
    void removeItemTest()  {
        load();

        //create Item item object
        Item item = new Item( "description", "2002-10-18", "incomplete");
        list.addItem(item);

        //get remaining storage
        int storage = list.getBalance();

        list.removeItem(item);

        //get remaining capacity after remove.(item)
        int newStorage = list.getBalance();
        assertEquals(storage,newStorage-1);
    }


    @Test
    void editDescriptionTest()  {
        load();
        //create Item item object
        Item item = list.getItems().get(0);
        //edit description in list
        list.editDescription(item,"newDescription");
        assertEquals("newDescription",list.getItems().get(0).getDescription());
    }

    @Test
    void editDueDateTest()  {
        load();
        //create Item item object
        Item temp = list.getItems().get(0);
        //edit due date in list
        list.editDueDate(temp,"2025-02-05");
        assertEquals("2025-02-05",list.getItems().get(0).getDueDate());
    }

    @Test
    void editStatusTest() {
        load();
        //create Item item object
        Item item = list.getItems().get(0);
        //make status of task to complete in list
        list.editStatus(item,"complete");
        assertEquals("complete",list.getItems().get(0).getStatus());
    }

    @Test
    void getItemsTest() {
        load();
        ArrayList<Item> items = list.getItems(); //add all ites of the list to items Arraylist
        assertEquals(8, items.size()); //assert equals
    }

    @Test
    void getCompleteItemsTest()  {
        load();
        ArrayList<Item> items = list.getCompleteItems();
        assertEquals(3, items.size());
    }

    @Test
    void getIncompleteItemsTest()  {
        load();
        ArrayList<Item> items = list.getIncompleteItems();
        assertEquals(5, items.size());
    }

    @Test
    void clearAllItemsTest() {
        load();
        list.clearAllItems();
        assertEquals(0, list.getItems().size());
    }
}
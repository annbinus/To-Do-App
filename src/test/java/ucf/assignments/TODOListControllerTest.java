/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Ann Binus
 */
package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TODOListControllerTest {

    List list = new List("My New List",100); //initialize todolist

    private void newItemData() throws FileNotFoundException {
        //create a file to scan input from user
        File file = new File("files/test.txt");
        Scanner scan = new Scanner(file);

        //add data from file to string array
        while (scan.hasNext()) {
            String data = scan.nextLine();
            String[] item = data.split(",");
            list.addItem(new Item(item[0], item[1], item[2]));
        }
    }

    @Test
    void showAllItems()  {
        try {
            newItemData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(8,list.count);
    }

    @Test
    void showIncompleteItems()  {
        try {
            newItemData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(5, list.getIncompleteItems().size());
    }

    @Test
    void showCompleteItems()  {
        try {
            newItemData();
        } catch (FileNotFoundException e) {
            e.  printStackTrace();
        }
        assertEquals(3, list.getCompleteItems().size());
    }

    @Test
    void clearAllItems()  {
        try {
            newItemData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        list.clearAllItems();
        assertEquals(0, list.getItems().size());
    }
}
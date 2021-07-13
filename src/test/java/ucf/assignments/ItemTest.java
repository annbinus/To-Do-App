/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Ann Binus
 */
package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    Item item = new Item( "New task", "2021-10-13", "complete");

    @Test
    void getDescription() {
        //returns true for assetEquals if the description matched the string given
        assertEquals("New task", item.getDescription());
    }

    @Test
    void setDescription() {
        //set new description
        item.setDescription("New task 1");
        assertEquals("New task 1", item.getDescription());
    }

    @Test
    void getDueDate() {
        //returns true for assertEquals if the date matches the value given
        assertEquals("2021-10-13", item.getDueDate());
    }

    @Test
    void setDueDate() {

        item.setDueDate("2006-03-26");
        assertEquals("2006-03-26", item.getDueDate());
    }

    @Test
    void isComplete() {
        assertEquals("complete", item.getStatus());
    }

    @Test
    void setComplete() {
        //returns true for assertEquals if the status matches with what the user inputs
        item.setStatus("incomplete");
        assertEquals("incomplete", item.getStatus());
    }

}

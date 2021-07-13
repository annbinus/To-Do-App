/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Ann Binus
 */
package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class TODOListController implements Initializable {


    private static List myToDoList;
    @FXML
    TableView<Item> tableView;
    @FXML
    TableColumn<Item, String> desc;
    @FXML
    TableColumn<Item, String> dueDate;
    @FXML
    TableColumn<Item, String> status;

    @FXML
    Label storage;

    @FXML
    MenuItem showAll;
    @FXML
    MenuItem showComplete;
    @FXML
    MenuItem showIncomplete;

    @FXML
    Button addButton;
    @FXML
    Button removeButton;
    @FXML
    MenuItem fileUpload;
    @FXML
    MenuItem fileDownload;

    @FXML
    public void editDescription(TableColumn.CellEditEvent<Item, String> cell){
        //get changes made by user from the table
        Item itemSelected = tableView.getSelectionModel().getSelectedItem();
        //set the changes to item's description
        myToDoList.editDescription(itemSelected, cell.getNewValue());
    }

    @FXML
    public void editDueDate(TableColumn.CellEditEvent<Item, String> cell){
        //get changes made by user from the table
        Item itemSelected = tableView.getSelectionModel().getSelectedItem();
        //set the changes to item's due date
        myToDoList.editDueDate(itemSelected, cell.getNewValue());
    }

    @FXML
    public void editStatus(TableColumn.CellEditEvent<Item, String> cell){
        //get changes made by user from the table
        Item itemSelected = tableView.getSelectionModel().getSelectedItem();
        //set the changes to item's status
        myToDoList.editStatus(itemSelected, cell.getNewValue());
    }


    @FXML
    public void showAllItems(){
        //create a new ArrayList to add all items from 'List'
        ArrayList<Item> allItems = myToDoList.getItems();

        //set arraylist to tableView to display
        tableView.getItems().setAll(allItems);
    }

    @FXML
    public void showIncompleteItems(){
        //create a new ArrayList to add only incomplete items from 'List'
        ArrayList<Item> incomplete = myToDoList.getIncompleteItems();

        //set arraylist to tableView to display
        tableView.getItems().setAll(incomplete);
    }

    @FXML
    public void showCompleteItems() {
        //create a new ArrayList to add only completed items from 'List'
        ArrayList<Item> complete = myToDoList.getCompleteItems();

        //set arraylist to tableView to display
        tableView.getItems().setAll(complete);
    }

    @FXML
    public void clear() {
        //this method will clear all items from the todolist
        myToDoList.clearAllItems();
        tableView.getItems().setAll(myToDoList.getItems());
        storage.setText("Storage Left: " + myToDoList.getBalance());
    }

    //return items in list
    public static ArrayList<Item> getList(){
        return myToDoList.getItems();
    }


    //opens file containing the item data to get items to store to 'List' class
    public void openFile(){

        try {
            File file = new File("files/Item.txt");
            Scanner scan = new Scanner(file);

            //read line to get the description, due date, and status
            while(scan.hasNext()){
                String listItem = scan.nextLine();
                String[] item = listItem.split(",");
                myToDoList.addItem(new Item(item[0],item[1],item[2]));
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setCellFactory(){

        //properties of table columns are referenced to methods of Item class
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    public void setTableCellEditable(){

        //columns are set to be editable
        desc.setCellFactory(TextFieldTableCell.forTableColumn());
        dueDate.setCellFactory(TextFieldTableCell.forTableColumn());
        status.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //initialize the 'List' object
        myToDoList = new List("My ToDoList", 100);

        //open file to read data that user enters
        openFile();

        //associate the Item data with the table columns
        setCellFactory();

        //columns are added to tableView class
        tableView.getItems().setAll(myToDoList.getItems());

        // shows the remaining storage
        storage.setText("Storage Left: " + myToDoList.getTitle());

        tableView.setEditable(true);

        //set the table editable
        setTableCellEditable();
    }

    @FXML
    public void handleAddButton(ActionEvent event) {

        //check is there's storage or else show dialog box warning
        if (myToDoList.getBalance() <= 0) {
            JOptionPane.showMessageDialog(null, "The list is full, delete an Item to continue");
        }
        else {
            //load the Item.fxml to show the Add Task window
            URL url = null;
            try {
                url = Paths.get("./src/main/resources/ucf.assignments/Item.fxml").toUri().toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Parent root = null;
            try {
                root = FXMLLoader.load(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.setResizable(false);

            //close window when the Add button is pushed
            Stage prevStage = (Stage) addButton.getScene().getWindow();
            prevStage.close();
            stage.show();
        }
    }

    @FXML
    public void handleRemoveButton(Event event) {

        //get the selected item and remove from tableView
        Item item = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().remove(item);

        // remove item from the List
        myToDoList.removeItem(item);

        //get the storage left after item is removed
        //show the storage left
        storage.setText("Storage Left: " + myToDoList.getBalance());
    }

    @FXML
    public void handleFileOpen(Event event) {

        FileChooser fileChooser = new FileChooser();

        //title for the file chooser dialog window
        fileChooser.setTitle("Open Resource File");

        // the dialog window opens and the user-selected file is assigned to the File class
        File file = fileChooser.showOpenDialog(null);

        if (file == null) {

            //show the warning dialog window
            JOptionPane.showMessageDialog(null, "Choose a valid file");
        }
        else {

            //cleat all the present items in the table
            myToDoList.clearAllItems();

            Scanner scan = null;
            try {
                // create a new scanner with the file object
                scan = new Scanner(file);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            //loop through until the scanner has a not token
            while (scan.hasNext()) {

                //copy text to line
                String line = scan.nextLine();

                //data is  split into part associated with the Item methods
                //create array to store the data containing description, due date, and status
                String[] item = line.split(",");

                // add a new 'Item' object with data from file to List
                myToDoList.addItem(new Item(item[0], item[1], item[2]));
            }

            //set the tableView with the new data from file
            tableView.getItems().setAll(myToDoList.getItems());

            //show the storage left
            storage.setText("Storage Left: " + myToDoList.getBalance());
        }
    }


    @FXML
    public void handleFileDownload(Event event) {

        DirectoryChooser directoryChooser = new DirectoryChooser();

        //title for the directory chooser dialog window
        directoryChooser.setTitle("Choose a folder to save file");

        // the dialog window opens and the user-selected directory is assigned to the File class
        File file = directoryChooser.showDialog(null);

        try {

            //all the date from the present List is written on a text file
            FileWriter write = new FileWriter(file.toString() + "\\List.txt");

            //write the List items on the file
            for (Item item : myToDoList.getItems()) {
                write.write(item.toString() + "\n");
            }

            write.close();

            //show user that list is saved
            JOptionPane.showMessageDialog(null, "List saved");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

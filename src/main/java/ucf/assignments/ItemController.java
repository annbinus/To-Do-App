package ucf.assignments;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ItemController implements Initializable {
    @FXML
    DatePicker date;

    @FXML
    TextArea description;

    @FXML
    Button addTaskButton;


    @FXML
    public void addTask() throws MalformedURLException {

        //convert date to the required format
        LocalDate getDate = date.getValue();
        String dueDate = getDate.toString();

        //get description
        String description = this.description.getText();

        //check for description length
        if(description.length()>256){
            //show warning dialog window
            JOptionPane.showMessageDialog(null, "Description is too long","Description", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //create Item object
        Item newTask = new Item(description, dueDate, "incomplete");

        // add Item object to List
        TODOListController.getList().add(newTask);

        //after adding new item, write on the text file
        try{
            //use the Item.txt file to write data
            FileWriter fwrite = new FileWriter("files/Item.txt");

            //get each Item from List and write it on the text file
            for(Item item : TODOListController.getList()){
                fwrite.write(item.toString()+"\n");
            }

            //close file
            fwrite.close();

        } catch(Exception e){
            e.printStackTrace();
        }

        // open the main window after adding task
        URL url = Paths.get("./src/main/resources/ucf.assignments/TODOList.fxml").toUri().toURL();
        Parent root = null;
        try {
            root = FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        //close window when the Add Task button is pushed
        Stage stagePrevious = (Stage) addTaskButton.getScene().getWindow();
        stagePrevious.close();
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        date.setConverter(new StringConverter<>()
        {
            final private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            //Convert local date to string
            public String toString(LocalDate localDate) {
                if(localDate==null) {
                    return "";
                }
                return dateTimeFormatter.format(localDate);
            }

            //convert string to Local Date
            public LocalDate fromString(String dateString) {
                if(dateString==null || dateString.trim().isEmpty()) {
                    return null;
                }
                return LocalDate.parse(dateString,dateTimeFormatter);
            }
        });
    }
}

/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Ann Binus
 */
package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class TODOList extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        try {
            URL url = Paths.get("./src/main/resources/ucf.assignments/TODOList.fxml").toUri().toURL();
            Parent root = FXMLLoader.load(url);
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("TODOList");
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void stop() {
        //save all the items from 'List' to file
        try {

            FileWriter fwrite = new FileWriter("files/Item.txt");
            for (Item item : TODOListController.getList()) {
                fwrite.write(item.toString() + "\n");
            }
            System.out.println("Item added");

            fwrite.close();
        } catch (Exception e) {
            System.out.println("Exception" + e);
        }

    }
}

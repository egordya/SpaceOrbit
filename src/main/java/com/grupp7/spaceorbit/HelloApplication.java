package com.grupp7.spaceorbit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {

       // Creates the FXML loader
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        // set title for the stage
        stage.setTitle("menu tree");
        // creates & sets the scene
        stage.setScene(new Scene(root, 500,350));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
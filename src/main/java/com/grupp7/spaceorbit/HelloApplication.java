package com.grupp7.spaceorbit;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application  {


    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menueVeiw.fxml"));


        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("spaceorbit");
        stage.setMaximized(true);
    }


    public static void main(String[] args) {
        launch();
    }

}


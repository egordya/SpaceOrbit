package com.grupp7.spaceorbit;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.stage.WindowEvent;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @FXML
    StackPane theStackPane;

    double day = 0.0;

    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.exit(0);
            }
        });

        stage.show();



    }

    public static void main(String[] args) {
        launch();
    }

}


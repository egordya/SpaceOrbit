package com.grupp7.spaceorbit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.GravitationModel;
import model.NormalCelestialObject;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        NormalCelestialObject[] planteter = new NormalCelestialObject[10];
        GravitationModel test = new GravitationModel(planteter);
        NormalCelestialObject[] planteter2 = new NormalCelestialObject[11];
        test.



        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
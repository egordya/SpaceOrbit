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

        NormalCelestialObject[] planteter = new NormalCelestialObject[3];

        planteter[0] = new NormalCelestialObject(new Position(0,0), new Direction(180), 10, 10);
        planteter[1] = new NormalCelestialObject(new Position(0,0), new Direction(150), 10, 10);
        planteter[2] = new NormalCelestialObject(new Position(0,0), new Direction(140), 10, 10);
        GravitationModel test = new GravitationModel(planteter);
        test.doSimulationStep(0.1);





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
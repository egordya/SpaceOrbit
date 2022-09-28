package com.grupp7.spaceorbit;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.stage.Stage;

import model.gameModel.GameModel;
import model.gameModel.GameModelBuilder;
import model.gravitationModel.GravitationModel;
import model.modelObjects.CelestialObject;
import model.gravitationModel.ObjectForGravitationModel;
import utilitys.Vector2D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HelloApplication extends Application {

    double day = 0.0;

    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();





    }

    public static void main(String[] args) {
        launch();
    }

}


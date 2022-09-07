package com.grupp7.spaceorbit;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.GravitationModel;
import model.NormalCelestialObject;
import utilitys.Vector2D;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        NormalCelestialObject[] planeter = new NormalCelestialObject[3];

        Vector2D planet1PositionVector = new Vector2D(1,1);
        Vector2D planet1VelocityVector = new Vector2D(10,10);
        planeter[0] = new NormalCelestialObject(planet1PositionVector,planet1VelocityVector,2000000000);

        Vector2D planet2PositionVector = new Vector2D(10,40);
        Vector2D planet2VelocityVector = new Vector2D(10,10);
        planeter[1] = new NormalCelestialObject(planet2PositionVector,planet2VelocityVector,2000000000);

        Vector2D planet3PositionVector = new Vector2D(62,100);
        Vector2D planet3VelocityVector = new Vector2D(10,10);
        planeter[2] = new NormalCelestialObject(planet3PositionVector,planet3VelocityVector,2000000000);

//        planteter[0] = new NormalCelestialObject(new Position(0,0), new Direction(180),  10);
//        planteter[1] = new NormalCelestialObject(new Position(0,0), new Direction(150),  10);
//        planteter[2] = new NormalCelestialObject(new Position(0,0), new Direction(140), 10);
        GravitationModel test = new GravitationModel(planeter);
        SOView soView = new SOView();
        System.out.println(planet2PositionVector.add(planet1PositionVector).getX());
        System.out.println(planet2PositionVector.add(planet1PositionVector).getY());

        stage.setTitle("Space Orbit");
        Canvas canvas = new Canvas(640, 480);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        soView.run(gc,planeter);
        for(int i = 0 ; i< 10; i++) {
            test.doSimulationStep(0.1);
            soView.run(gc, planeter);
        }
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            //test.doSimulationStep(0.1);
            //soView.run(gc, planeter);
        }));
        //number of cycles in animation INDEFINITE = repeat indefinitely
        tl.setCycleCount(Timeline.INDEFINITE);
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
        tl.play();
        stage.show();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
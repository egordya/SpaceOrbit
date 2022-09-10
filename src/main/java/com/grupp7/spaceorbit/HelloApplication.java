package com.grupp7.spaceorbit;

import javafx.application.Application;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.stage.Stage;

import model.GravitationModel;
import model.NormalCelestialObject;
import model.ObjectForGravitationModel;
import utilitys.Vector2D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {

        NormalCelestialObject[] planteter = {
            new NormalCelestialObject(new Vector2D(0, 0.8), new Vector2D(0.25, 0), 850500),
            new NormalCelestialObject(new Vector2D(0,0.83), new Vector2D(0.22, 0), 200),
            new NormalCelestialObject(new Vector2D(0, -1.5), new Vector2D(-0.22, 0), 850500),
            new NormalCelestialObject(new Vector2D(0, -1.47), new Vector2D(-0.245, 0), 200),
            new NormalCelestialObject(new Vector2D(0, 0), new Vector2D(0, 0), 2200000000.0),
            new NormalCelestialObject(new Vector2D(0, -1.534), new Vector2D(-0.191, 0), 200),
            new NormalCelestialObject(new Vector2D(0.6, -30), new Vector2D(0, 1), 5200000000.0),
            new NormalCelestialObject(new Vector2D(0, 0.4), new Vector2D(0.25, 0), 100)
        };

        GravitationModel test = new GravitationModel(planteter);

        Circle[] fxCircles = {
            new Circle(0, 0, 3.5, Color.RED),
            new Circle(0, 0, 2.5, Color.BROWN),
            new Circle(0, 0, 4.5, Color.GREEN),
            new Circle(0,0, 2,  Color.CORAL),
            new Circle(0, 0, 20, Color.YELLOW),
            new Circle(0, 0, 3, Color.GRAY),
            new Circle(0, 0, 7, Color.BLACK),
            new Circle(0, 0, 4, Color.DARKBLUE)
        };

        Pane root = new Pane();
        root.getChildren().clear();

        for(Circle x : fxCircles){
            root.getChildren().add(x);
        }

        int yOffset = 1000;
        int xoOffset = 1800;
        Scene scene = new Scene(root, xoOffset, yOffset);
        stage.setScene(scene);
        stage.show();


        Timer tr = new Timer();
        tr.scheduleAtFixedRate(new TimerTask(){
            //override run methid
            @Override
            public void run(){
                ArrayList<ObjectForGravitationModel> CelestialObjects = new ArrayList<>();
                for (int extra = 0; extra<300; extra++){
                    CelestialObjects = test.doSimulationStep(0.0001);
                }

                int k = 320;

                int i = 0;
                for (Node x : root.getChildren()){
                    Circle a = (Circle) x;
                    a.setCenterX(CelestialObjects.get(i).getPos().getX() * k + xoOffset/2);
                    a.setCenterY(CelestialObjects.get(i).getPos().getY() * k + yOffset/2);

                    i++;
                }
            }
        }, 0, 20);
    }


    public static void main(String[] args) {
        launch();
    }

}


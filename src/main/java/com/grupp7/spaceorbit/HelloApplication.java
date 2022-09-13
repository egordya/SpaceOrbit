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

    double day = 0.0;

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {

        NormalCelestialObject[] planteter = {
            //new NormalCelestialObject(new Vector2D(0, 0.8), new Vector2D(0.25, 0), 850500),
            //new NormalCelestialObject(new Vector2D(0,0.83), new Vector2D(0.22, 0), 200),
            //new NormalCelestialObject(new Vector2D(0, -1.5), new Vector2D(-0.22, 0), 850500),
            //new NormalCelestialObject(new Vector2D(0, -1.47), new Vector2D(-0.245, 0), 200),
            //new NormalCelestialObject(new Vector2D(0, 0), new Vector2D(0, 0), 2200000000.0),
            //new NormalCelestialObject(new Vector2D(0, -1.534), new Vector2D(-0.191, 0), 200),
            //new NormalCelestialObject(new Vector2D(0.6, -30), new Vector2D(0, 1), 5200000000.0),
            //new NormalCelestialObject(new Vector2D(0, 0.4), new Vector2D(0.25, 0), 100)
            new NormalCelestialObject(new Vector2D(0, 0), new Vector2D(0, 0), 1.989 * Math.pow(10,30)),
            new NormalCelestialObject(new Vector2D(0, 69.8 * Math.pow(10,6) * Math.pow(10,3)), new Vector2D(38.86 * Math.pow(10,3), 0), 0.330 * Math.pow(10,24)),
            new NormalCelestialObject(new Vector2D(0,108.9 * Math.pow(10,6) * Math.pow(10,3)), new Vector2D(34.79 * Math.pow(10,3), 0), 4.87 * Math.pow(10,24)),
            new NormalCelestialObject(new Vector2D(0, 152.1 * Math.pow(10,6) * Math.pow(10,3)), new Vector2D(29.29 * Math.pow(10,3), 0), 5.97 * Math.pow(10,24)),

        };

        GravitationModel test = new GravitationModel(planteter);

        Circle[] fxCircles = {
            new Circle(0, 0, 10, Color.YELLOW),
            new Circle(0, 0, 4, Color.DARKGRAY),
            new Circle(0, 0, 5, Color.LIGHTSALMON),
            new Circle(0, 0, 5, Color.BLUE),
            //new Circle(0,0, 2,  Color.CORAL),
            //new Circle(0, 0, 20, Color.YELLOW),
            //new Circle(0, 0, 3, Color.GRAY),
            //new Circle(0, 0, 7, Color.BLACK),
            //new Circle(0, 0, 4, Color.DARKBLUE)
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
                for (int extra = 0; extra<20000; extra++){
                    CelestialObjects = test.doSimulationStep(1);
                }

                day += 20000.0 / 60.0 / 60.0 / 24.0;
                day = day % 365;
                System.out.println("dag: " + day);


                double k = Math.pow(10, -8.6);

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


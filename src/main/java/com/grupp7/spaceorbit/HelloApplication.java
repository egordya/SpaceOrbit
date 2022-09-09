package com.grupp7.spaceorbit;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.stage.Stage;

import javafx.util.Duration;
import model.GravitationModel;
import model.NormalCelestialObject;
import model.ObjectForGravitationModel;
import utilitys.Vector2D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {

        NormalCelestialObject[] planteter = new NormalCelestialObject[6];
        planteter[0] = new NormalCelestialObject(new Vector2D(0, 0.8), new Vector2D(0.25,0), 10000);
        planteter[1] = new NormalCelestialObject(new Vector2D(0, -1.5), new Vector2D(-0.22, 0), 850500);
        planteter[2] = new NormalCelestialObject(new Vector2D(0, 0.001), new Vector2D(0, 0), 2200000000.0);
        planteter[3] = new NormalCelestialObject(new Vector2D(0, -1.534), new Vector2D(-0.191, 0), 200);
        planteter[4] = new NormalCelestialObject(new Vector2D(0.7, -15), new Vector2D(0, 0.5), 5200000000.0);
        planteter[5] = new NormalCelestialObject(new Vector2D(0, 0.4), new Vector2D(0.25, 0), 100);

        GravitationModel test = new GravitationModel(planteter);



        Pane root = new Pane();

        root.getChildren().clear();
        Circle planet1 = new Circle(0, 0, 4, Color.RED);
        Circle planet2 = new Circle(0, 0, 4.5, Color.GREEN);
        Circle planet3 = new Circle(0, 0, 20, Color.YELLOW);
        Circle planet4 = new Circle(0, 0, 3, Color.GRAY);
        Circle planet5 = new Circle(0, 0, 7, Color.BLACK);
        Circle planet6 = new Circle(0, 0, 4, Color.DARKBLUE);
        root.getChildren().add(planet1);
        root.getChildren().add(planet2);
        root.getChildren().add(planet3);
        root.getChildren().add(planet4);
        root.getChildren().add(planet5);
        root.getChildren().add(planet6);

        int s = 1000;
        int x = 1800;
        Scene scene = new Scene(root, x, s);

        stage.setScene(scene);
        stage.show();


        Timer tr = new Timer();
        tr.scheduleAtFixedRate(new TimerTask(){
            //override run methid
            @Override
            public void run(){
                ArrayList<ObjectForGravitationModel> objekts = new ArrayList<>();
                for (int extra = 0; extra<300; extra++){
                    objekts = test.doSimulationStep(0.0001);
                }


                double[] obj1pos = {objekts.get(0).getPos().getX(), objekts.get(0).getPos().getY()};
                double[] obj2pos = {objekts.get(1).getPos().getX(), objekts.get(1).getPos().getY()};
                double[] obj3pos = {objekts.get(2).getPos().getX(), objekts.get(2).getPos().getY()};
                double[] obj4pos = {objekts.get(3).getPos().getX(), objekts.get(3).getPos().getY()};
                double[] obj5pos = {objekts.get(4).getPos().getX(), objekts.get(4).getPos().getY()};
                double[] obj6pos = {objekts.get(5).getPos().getX(), objekts.get(5).getPos().getY()};


                int k = 320;

                planet1.setCenterX(obj1pos[0] * k + x/2);
                planet1.setCenterY(obj1pos[1] * k + s/2);

                planet2.setCenterX(obj2pos[0] * k + x/2);
                planet2.setCenterY(obj2pos[1] * k + s/2);

                planet3.setCenterX(obj3pos[0] * k + x/2);
                planet3.setCenterY(obj3pos[1] * k + s/2);

                planet4.setCenterX(obj4pos[0] * k + x/2);
                planet4.setCenterY(obj4pos[1] * k + s/2);

                planet5.setCenterX(obj5pos[0] * k + x/2);
                planet5.setCenterY(obj5pos[1] * k + s/2);

                planet6.setCenterX(obj6pos[0] * k + x/2);
                planet6.setCenterY(obj6pos[1] * k + s/2);

            }
        }, 0, 20);
    }







    public static void main(String[] args) {
        launch();
    }

}


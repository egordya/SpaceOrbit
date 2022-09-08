package com.grupp7.spaceorbit;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.GravitationModel;
import model.NormalCelestialObject;
import model.ObjectForGravitationModel;
import utilitys.Vector2D;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        NormalCelestialObject[] planteter = new NormalCelestialObject[3];

        planteter[0] = new NormalCelestialObject(new Vector2D(-0.2, 1), new Vector2D(0.02,0), 100000000);
        planteter[1] = new NormalCelestialObject(new Vector2D(0, -1), new Vector2D(-0.02, 0), 100000000);
        planteter[2] = new NormalCelestialObject(new Vector2D(1, 0), new Vector2D(-0.02, 0), 100000000);

        GravitationModel test = new GravitationModel(planteter);


        ArrayList<double[]> obj1 = new ArrayList<>();
        ArrayList<double[]> obj2 = new ArrayList<>();
        ArrayList<double[]> obj3 = new ArrayList<>();

        for (int i = 0; i < 30000; i++) {
            ArrayList<ObjectForGravitationModel> objekts = test.doSimulationStep(0.005);

            double[] obj1pos = {objekts.get(0).getPos().getX(), objekts.get(0).getPos().getY()};
            double[] obj2pos = {objekts.get(1).getPos().getX(), objekts.get(1).getPos().getY()};
            double[] obj3pos = {objekts.get(2).getPos().getX(), objekts.get(2).getPos().getY()};

            obj1.add(obj1pos);
            obj2.add(obj2pos);
            obj3.add(obj3pos);
        }




        Circle circle = new Circle(100);

        Circle dot = new Circle(250, 250, 5, Color.RED);

        ArrayList<Circle> onj1points = new ArrayList<>();
        ArrayList<Circle> onj2points = new ArrayList<>();
        ArrayList<Circle> onj3points = new ArrayList<>();

        for (double[] x : obj1){
            onj1points.add(new Circle(x[0] * 300 + 500, x[1] * 300 + 500, 0.5, Color.RED));
        }

        for (double[] x : obj2){
            onj1points.add(new Circle(x[0] * 300 + 500, x[1] * 300 + 500, 0.5, Color.BLUE));
        }

        for (double[] x : obj3){
            onj3points.add(new Circle(x[0] * 300 + 500, x[1] * 300 + 500, 0.5, Color.GREEN));
        }

        Pane root = new Pane();

        for (Circle x : onj1points){
            root.getChildren().add(x);
        }

        for (Circle x : onj2points){
            root.getChildren().add(x);
        }

        for (Circle x : onj3points){
            root.getChildren().add(x);
        }


        double[] start1 = obj1.get(0);
        double[] start2 = obj2.get(0);
        double[] start3 = obj3.get(0);

        root.getChildren().add(new Circle(start1[0] * 300 + 500, start1[1] * 300 + 500, 5, Color.RED));
        root.getChildren().add(new Circle(start2[0] * 300 + 500, start2[1] * 300 + 500, 5, Color.BLUE));
        root.getChildren().add(new Circle(start3[0] * 300 + 500, start3[1] * 300 + 500, 5, Color.GREEN));


        Scene scene = new Scene(root, 1000, 1000);


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
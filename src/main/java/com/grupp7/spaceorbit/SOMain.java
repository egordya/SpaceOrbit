package com.grupp7.spaceorbit;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;



public class SOMain extends Application {
    private SOController soController = new SOController();
    private SOView soView = new SOView();

    @Override
    public void start(Stage stage) throws IOException {
//        stage.setTitle("Space Orbit");
//        Canvas canvas = new Canvas(640, 480);
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> soView.run(gc)));
//        //number of cycles in animation INDEFINITE = repeat indefinitely
//        tl.setCycleCount(Timeline.INDEFINITE);
//        stage.setScene(new Scene(new StackPane(canvas)));
//        stage.show();
//        tl.play();
//        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
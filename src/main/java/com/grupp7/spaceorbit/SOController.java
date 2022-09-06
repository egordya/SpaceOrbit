package com.grupp7.spaceorbit;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class SOController implements Initializable {

    @FXML
    private Label welcomeText;

    @FXML
    public ImageView planetMenuGif;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void initialize(URL arg0, ResourceBundle arg1) {

        // translate
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(planetMenuGif);
        translate.setDuration(Duration.millis(5000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByX(250);
        translate.setByY(250);
        translate.setAutoReverse(true);
        translate.play();
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(planetMenuGif);
        rotate.setDuration(Duration.millis(2500));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(360);
        rotate.play();
    }
}

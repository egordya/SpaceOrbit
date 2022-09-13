package com.grupp7.spaceorbit;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class HelloController {

    double prefbuttonHight = 30;
    double prefbuttonWidth = 90;

    @FXML private StackPane root;

    @FXML private VBox mainVbox;
    @FXML private VBox customVbox;

    @FXML private Button startContinue;
    @FXML private Button lvlSelect;
    @FXML private Button customization;
    @FXML private Button lvlBuild;
    @FXML private Button settings;
    @FXML private Button quit;

    @FXML private Button skins;
    @FXML private Button back;


    //sets the images on all buttons
    public void initialize (){
        root.getChildren().remove(customVbox);
        //sets pic to startbutton
        ImageView startimageView = new ImageView(Objects.requireNonNull(getClass().getResource("/Buttons/StartButton.png")).toExternalForm());
        startContinue.setGraphic(startimageView);
        // lvlselect button
        ImageView lvlselectimageView = new ImageView(Objects.requireNonNull(getClass().getResource("/Buttons/LevelButton.png")).toExternalForm());
        lvlSelect.setGraphic(lvlselectimageView);

        ImageView quitimageView = new ImageView(Objects.requireNonNull(getClass().getResource("/Buttons/QuitButton.png")).toExternalForm());
        quit.setGraphic(quitimageView);

        //resize all the views
        startimageView.setFitHeight(prefbuttonHight);
        startimageView.setFitWidth(prefbuttonWidth);

        lvlselectimageView.setFitHeight(prefbuttonHight);
        lvlselectimageView.setFitWidth(prefbuttonWidth);

        quitimageView.setFitHeight(prefbuttonHight);
        quitimageView.setFitWidth(prefbuttonWidth);


    }

    //quits the game
    public void quit(ActionEvent actionEvent) {
        Platform.exit();
    }
    //This method should call the startGame method
    public void startGame(ActionEvent actionEvent) {
    }

    // Swiitch to customVbox
    public void customizationClicked(ActionEvent actionEvent) {
        root.getChildren().remove(mainVbox);
        root.getChildren().add(customVbox);

    }
    //Switch back to main Vbox
    public void backToMain(ActionEvent actionEvent) {
        root.getChildren().add(mainVbox);
        root.getChildren().remove(customVbox);
    }
}

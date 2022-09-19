package com.grupp7.spaceorbit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable, Mediator{

    @FXML
    StackPane theStackPane;

    MenuController menuController;
    GameController gameController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuController = new MenuController();
        gameController = new GameController();
        theStackPane.getChildren().add(menuController);
        theStackPane.getChildren().add(gameController);
    }


    @FXML
    public void startGame(ActionEvent event){

    }

    @Override
    public void notify(Object pointer) {

    }

    private void showMenu(){

    }

    private void showGame(){

    }



}

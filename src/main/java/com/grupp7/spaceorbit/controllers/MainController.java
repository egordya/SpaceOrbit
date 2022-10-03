package com.grupp7.spaceorbit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import model.menuModel.MenuModel;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable, Mediator{

    @FXML
    StackPane theStackPane;

    MenuView menuView;
    GameView gameView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuView = new MenuView(this, new MenuModel());
        gameView = new GameView(this);

        theStackPane.getChildren().add(gameView);
        theStackPane.getChildren().add(menuView);

    }


    @Override
    public void notify(Object pointer, MediatorCommand command) throws FileNotFoundException {
        if (pointer == menuView){
            theStackPane.getChildren().clear();
            theStackPane.getChildren().add(gameView);

            //String[] levelPaths = menuView.getRemainingLevels();
            //List<String> levelPathsList = Arrays.stream(levelPaths).toList();

            //String firstLevelPath = levelPathsList.get(0);
            //levelPathsList.remove(firstLevelPath);
            //String[] levelPathsArray = levelPathsList.toArray(new String[0]);

            gameView.loadGameModel("firstLevelPath", null);
        }

        else if (pointer == gameView) {
            theStackPane.getChildren().clear();
            theStackPane.getChildren().add(menuView);
        }
    }

    private void showMenu(){

    }

    private void showGame(){

    }

}

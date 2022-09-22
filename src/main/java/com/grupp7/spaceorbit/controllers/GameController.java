package com.grupp7.spaceorbit.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import model.gameModel.GameModel;
import model.gameModel.GameModelBuilder;
import model.gameModel.Observer;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GameController extends AnchorPane implements Observer {

    Mediator mediator;
    GameModel gameModel;

    public GameController(Mediator mediator) {

        this.mediator = mediator;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    public void loadGameModel(String jsonPath) throws FileNotFoundException {
        this.gameModel = GameModelBuilder.getGameModel(jsonPath);
        this.gameModel.addObserver(this);
    }

    @Override
    public void commandFromModel() {

    }
}

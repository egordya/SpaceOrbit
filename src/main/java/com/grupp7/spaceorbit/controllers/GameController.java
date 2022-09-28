package com.grupp7.spaceorbit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.gameModel.GameModel;
import model.gameModel.GameModelBuilder;
import model.gameModel.Observer;
import model.gameModel.ObserverCommand;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GameController extends AnchorPane implements Observer {
    
    @FXML
    Pane renderSurface;
    
    Mediator mediator;
    GameModel gameModel;

    String pathToCurrentLevel;

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
        pathToCurrentLevel = jsonPath;
        this.gameModel = GameModelBuilder.getGameModel(jsonPath);
        this.gameModel.addObserver(this);
        init();

        // för test, ta bort när klart

    }

    private void init(){

        Drawable[] planets = gameModel.getPlanets();
        Drawable[] players = gameModel.getPlayers();
        Drawable[] targets = gameModel.getTargets();


        for (Drawable p : planets){
            renderSurface.getChildren().add(p.getShape());
        }

        for (Drawable p : players){
            renderSurface.getChildren().add(p.getShape());
        }

        for (Drawable p : targets){
            renderSurface.getChildren().add(p.getShape());
        }




    }

    @FXML
    private void pause(){
        gameModel.pauseGame();
    }

    @FXML
    private void resume(){
        gameModel.startGame();
    }

    @FXML
    private void restart() throws FileNotFoundException {
        gameModel.pauseGame();
        renderSurface.getChildren().clear();
        this.gameModel = GameModelBuilder.getGameModel(pathToCurrentLevel);
        this.gameModel.addObserver(this);
        init();
    }

    @Override
    public void commandFromModel(ObserverCommand command) {
        if(command == ObserverCommand.Win){
            gameModel.pauseGame();
        }
    }
}

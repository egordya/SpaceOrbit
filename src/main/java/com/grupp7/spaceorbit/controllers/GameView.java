package com.grupp7.spaceorbit.controllers;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import model.gameModel.GameModel;
import model.gameModel.GameModelBuilder;
import model.gameModel.Observer;
import model.gameModel.ObserverCommand;
import utilitys.Vector2D;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GameView extends AnchorPane implements Observer {
    
    @FXML
    Pane renderSurface;
    @FXML
    HBox winBox;
    @FXML
    Pane anchor;

    Line[] lines;


    Mediator mediator;
    GameModel gameModel;

    GameController gameController;

    String pathToCurrentLevel;

    double pX, pY;
    double rX, rY;



    public GameView(Mediator mediator) {

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

    @Override
    public void commandFromModel(ObserverCommand command) {
        if(command == ObserverCommand.Win){
            Platform.runLater(() -> {
                anchor.getChildren().add(winBox);
            });

            gameModel.pauseGame();
            System.out.println("win");
        } else if (command == ObserverCommand.Update) {
            Platform.runLater(() -> {
                showObjects();
            });
        }
    }


    @FXML
    void nextLevel() throws FileNotFoundException {
        String[] paths = this.gameModel.getAllNextLevelPaths();
        List<String> pathsList = Arrays.stream(paths).toList();

        String firstPath = pathsList.get(0);
        pathsList.remove(firstPath);

        this.loadGameModel(firstPath, pathsList.toArray(new String[0]));

    }


    public void loadGameModel(String jsonPath, String[] allNextLevelPaths) throws FileNotFoundException {
        pathToCurrentLevel = jsonPath;

        this.gameModel = GameModelBuilder.getGameModel(jsonPath);
        this.gameModel.addObserver(this);
        this.gameModel.setAllNextLevelPaths(allNextLevelPaths);

        this.gameController = new GameController(gameModel);

        renderSurface.setOnMousePressed(gameController);
        renderSurface.setOnMouseReleased(gameController);
        renderSurface.setOnMouseDragged(gameController);

        showObjects();

        lines = new Line[gameModel.getPlayers().length];
        for(int i = 0; i<gameModel.getPlayers().length; i++){
            lines[i] = new Line();
        }
        // för test, ta bort när klart
    }


    private void showObjects(){
        Drawable[] planets = gameModel.getPlanets();
        Drawable[] players = gameModel.getPlayers();
        Drawable[] targets = gameModel.getTargets();
        anchor.getChildren().remove(winBox);
        renderSurface.getChildren().clear();

        for (Drawable p : planets){
            renderSurface.getChildren().add(p.getGeometry());
        }

        for (Drawable p : players){
            renderSurface.getChildren().add(p.getGeometry());
        }

        for (Drawable p : targets){
            renderSurface.getChildren().add(p.getGeometry());
        }
    }

    private void drawArrow(double[] setX, double[] setY) {

        for(Line l : lines){
            renderSurface.getChildren().remove(l);

            l.setStrokeWidth(1);
        }

        for(int i = 0; i<lines.length; i++){
            lines[i].setStartX(gameModel.getPlayers()[i].getXPos());
            lines[i].setStartY(gameModel.getPlayers()[i].getYPos());
            lines[i].setEndX(setX[i]);
            lines[i].setEndY(setY[i]);
        }

        for(Line l : lines){
            renderSurface.getChildren().add(l);
        }
    }


    private void shoot(){
        double vY  = (rY - pY);
        double vX = (rX - pX);


        Vector2D[] vectors = new Vector2D[gameModel.getPlayers().length];
        for(int i = 0; i<gameModel.getPlayers().length; i++){
            vectors[i] = new Vector2D(vX, vY);
        }

        gameModel.setPlayerVelocity(vectors);
        gameModel.startGame();
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
        this.gameModel = GameModelBuilder.getGameModel(pathToCurrentLevel);
        this.gameModel.addObserver(this);
        showObjects();
    }

    @FXML
    private void backToMainMenu() throws FileNotFoundException {
        gameModel.pauseGame();
        this.gameModel = null;
        mediator.notify(this, MediatorCommand.STANDARD);
    }

}

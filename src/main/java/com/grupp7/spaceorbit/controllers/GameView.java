package com.grupp7.spaceorbit.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import model.gameModel.GameModel;
import model.gameModel.GameModelBuilder;
import model.gameModel.Observer;
import model.gameModel.ObserverCommand;
import utilitys.ImageCache;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameView extends AnchorPane implements Observer {
    
    @FXML
    Pane renderSurface;
    @FXML
    HBox winBox;
    @FXML
    Pane anchor;

    Mediator mediator;
    GameModel gameModel;
    GameController gameController;
    String pathToCurrentLevel;
    String[] allNextLevelPaths;

    ImageCache imageCache = new ImageCache();

    Image test = new Image(getClass().getResource("/img/testhole3.gif").toString());
    Image test2 = new Image(getClass().getResource("/img/kanye.jpg").toString());
    Image test3 = new Image(getClass().getResource("/img/testtst2.gif").toString());

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

    public void loadGameModel(String jsonPath, String[] allNextLevelPaths) throws FileNotFoundException {
        pathToCurrentLevel = jsonPath;

        this.gameModel = GameModelBuilder.getGameModel(jsonPath);
        this.gameModel.addObserver(this);
        this.allNextLevelPaths = allNextLevelPaths;

        this.gameController = new GameController(gameModel);

        renderSurface.setOnMousePressed(gameController);
        renderSurface.setOnMouseReleased(gameController);
        renderSurface.setOnMouseDragged(gameController);

        showObjects();
    }

    @Override
    public void commandFromModel(ObserverCommand command) {
        if(command == ObserverCommand.Win){
            Platform.runLater(() -> {
                anchor.getChildren().add(winBox);
            });

            gameModel.togglePauseGame();
            System.out.println("win");
        }
        else if (command == ObserverCommand.Update) {
            Platform.runLater(() -> {
                showObjects();
            });
        }
        else if(command == ObserverCommand.Restart){

            Platform.runLater(() -> {
                try {
                    restart();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private void showObjects(){

        ArrayList<Drawable> DrawableList = new ArrayList<>();
        DrawableList.addAll(Arrays.stream(gameModel.getPlanets()).toList());
        DrawableList.addAll(Arrays.stream(gameModel.getPlayers()).toList());
        DrawableList.addAll(Arrays.stream(gameModel.getTargets()).toList());

        anchor.getChildren().remove(winBox);
        renderSurface.getChildren().clear();

        for (Drawable p : DrawableList){

            String path = getClass().getResource(p.getImagePath()).toString();
            imageCache.loadImage(path);
            Image image = imageCache.getImage(path);

            p.getGeometry().setFill(new ImagePattern(image));
            renderSurface.getChildren().add(p.getGeometry());
        }


        if(gameModel.getShowPlayerArrows()){
            Drawable[] arrows = gameModel.getPlayerArrows();
            for (Drawable p : arrows){
                p.getGeometry().setStroke(Color.WHITE);
                renderSurface.getChildren().add(p.getGeometry());
            }
        }

    }




    @FXML
    private void pause(){
        gameModel.togglePauseGame();
    }

    @FXML
    private void resume(){
        gameModel.startGame();
    }

    @FXML
    private void restart() throws FileNotFoundException {
        gameModel.togglePauseGame();
        loadGameModel(pathToCurrentLevel, allNextLevelPaths);
    }

    @FXML
    private void nextLevel() throws FileNotFoundException {
        List<String> pathsList = Arrays.stream(this.allNextLevelPaths).toList();

        String firstPath = pathsList.get(0);
        pathsList.remove(firstPath);

        this.loadGameModel(firstPath, pathsList.toArray(new String[0]));

    }

    @FXML
    private void backToMainMenu() throws FileNotFoundException {
        gameModel.togglePauseGame();
        this.gameModel = null;
        mediator.notify(this, MediatorCommand.STANDARD);
    }


}

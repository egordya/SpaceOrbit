package com.grupp7.spaceorbit.views;

import com.grupp7.spaceorbit.controllers.GameController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Shape;
import model.gameModel.*;
import model.modelObjects.Geometry;
import utilitys.ImageCache;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GameView extends AnchorPane implements Observer {
    
    @FXML
    Pane renderSurface;
    @FXML
    StackPane winBox;

    Mediator mediator;
    GameModel gameModel;
    GameController gameController;
    String pathToCurrentLevel;
    String[] allNextLevelPaths;

    ImageCache imageCache = new ImageCache();
    IGameModellBuilder gameModellBuilder;

    public GameView(Mediator mediator, IGameModellBuilder gameModellBuilder) {

        this.mediator = mediator;
        this.gameModellBuilder = gameModellBuilder;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    public void loadGameModel(String jsonPath, String[] allNextLevelPathsx) throws FileNotFoundException {

        pathToCurrentLevel = jsonPath;
        this.allNextLevelPaths = allNextLevelPathsx;

        this.gameModel = gameModellBuilder.getGameModel(jsonPath);
        this.gameModel.addObserver(this);

        this.gameController = new GameController(gameModel);

        renderSurface.setOnMousePressed(gameController);
        renderSurface.setOnMouseReleased(gameController);
        renderSurface.setOnMouseDragged(gameController);
        winBox.setVisible(false);
        winBox.toBack();
        //winBox.toBack();
        showObjects();
        resume();
    }

    @Override
    public void commandFromModel(ObserverCommand command) {
        if(command == ObserverCommand.Win){
                    winBox.toFront();
                    winBox.setVisible(true);
                    System.out.println("Win");






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
        //anchor.getChildren().remove(winBox);
        winBox.toFront();
        renderSurface.getChildren().clear();

        for (Drawable p : DrawableList){

            Geometry<Shape> s = p.getGeometry();


            String path = getClass().getResource(p.getImagePath()).toString();
            imageCache.loadImage(path);
            Image image = imageCache.getImage(path);

            s.getGeometry().setFill(new ImagePattern(image));
            renderSurface.getChildren().add(s.getGeometry());
        }

        if(gameModel.getShowPlayerArrows()){
            Drawable[] arrows = gameModel.getPlayerArrows();
            for(Drawable p : arrows){
                Geometry<Shape> s = p.getGeometry();
                s.getGeometry().setStroke(Color.WHITE);
                renderSurface.getChildren().add(s.getGeometry());
            }
        }




    }




    @FXML
    private void pause(){
        gameController.togglePauseGame();
    }

    @FXML
    private void resume(){
        gameController.startGame();
    }

    @FXML
    private void restart() throws FileNotFoundException {
        gameController.terminate();
        loadGameModel(pathToCurrentLevel, allNextLevelPaths);
    }

    @FXML
    private void nextLevel() throws FileNotFoundException {
        imageCache.clearCache();
        this.gameController.terminate();
        ArrayList<String>  pathsList = new ArrayList<>();
        for(String x : allNextLevelPaths){
            pathsList.add(x);
        }
        String firstPath = pathsList.get(0);
        pathsList.remove(firstPath);
        loadGameModel(firstPath, pathsList.toArray(new String[0]));


    }

    @FXML
    private void backToMainMenu() throws FileNotFoundException {

        imageCache.clearCache();
        this.gameController.terminate();
        mediator.notify(this, MediatorCommand.STANDARD);
    }
}

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

    Mediator mediator;
    GameModel gameModel;
    GameController gameController;
    String pathToCurrentLevel;
    String[] allNextLevelPaths;

    Image test = new Image(getClass().getResource("/img/testhole3.gif").toString());
    Image test2 = new Image(getClass().getResource("/img/kanye.jpg").toString());
    Image test3 = new Image(getClass().getResource("/img/testtst2.gif").toString());

    /**
     * constructs a gameView
     * @param mediator the messenger between the observers and the model
     */
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

    /**
     * loads a new gameModel
     * @param jsonPath the json-file that describes the level that the newly created gameModel will run
     * @param allNextLevelPaths an array of paths to all remaining levels
     * @throws FileNotFoundException if the json-files is not found the exception will not be caught
     */
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

    /**
     * performs actions for a given command
     * @param command the command
     */
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

    /**
     * draws all the objects
     */
    private void showObjects(){
        Drawable[] planets = gameModel.getPlanets();
        Drawable[] players = gameModel.getPlayers();
        Drawable[] targets = gameModel.getTargets();
        anchor.getChildren().remove(winBox);
        renderSurface.getChildren().clear();

        for (Drawable p : planets){
            p.getGeometry().setFill(new ImagePattern(test3));
            renderSurface.getChildren().add(p.getGeometry());
        }

        for (Drawable p : players){
            p.getGeometry().setFill(new ImagePattern(test2));
            renderSurface.getChildren().add(p.getGeometry());

        }

        // man måste cachea bilderna / gif
        for (Drawable p : targets){
            renderSurface.getChildren().add(p.getGeometry());
            p.getGeometry().setFill(new ImagePattern(test));
        }

        if(gameModel.getShowPlayerArrows()){
            Drawable[] arrows = gameModel.getPlayerArrows();
            for (Drawable p : arrows){
                p.getGeometry().setStroke(Color.WHITE);
                renderSurface.getChildren().add(p.getGeometry());
            }
        }

    }


    /**
     * pauses the game
     */
    @FXML
    private void pause(){
        gameModel.togglePauseGame();
    }

    /**
     * resumes the game
     */
    @FXML
    private void resume(){
        gameModel.startGame();
    }

    /**
     * restarts the current level
     * @throws FileNotFoundException if no files are found for the levels the exception will not be caught
     */
    @FXML
    private void restart() throws FileNotFoundException {
        gameModel.togglePauseGame();
        loadGameModel(pathToCurrentLevel, allNextLevelPaths);
    }

    /**
     * loads a gameModel for the next level
     * @throws FileNotFoundException if no files are found for the levels the exception will not be caught
     */
    @FXML
    private void nextLevel() throws FileNotFoundException {
        List<String> pathsList = Arrays.stream(this.allNextLevelPaths).toList();

        String firstPath = pathsList.get(0);
        pathsList.remove(firstPath);

        this.loadGameModel(firstPath, pathsList.toArray(new String[0]));

    }

    /**
     * takes the user back to the menu
     * @throws FileNotFoundException if no fxml-file are found for the menu the exception will not be caught
     */
    @FXML
    private void backToMainMenu() throws FileNotFoundException {
        gameModel.togglePauseGame();
        this.gameModel = null;
        mediator.notify(this, MediatorCommand.STANDARD);
    }


}

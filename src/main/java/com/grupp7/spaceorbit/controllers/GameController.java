package com.grupp7.spaceorbit.controllers;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import model.gameModel.GameModel;
import model.gameModel.GameModelBuilder;
import model.gameModel.Observer;
import model.gameModel.ObserverCommand;
import utilitys.Vector2D;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GameController extends AnchorPane implements Observer {
    
    @FXML
    Pane renderSurface;

    Line[] lines;

    Mediator mediator;
    GameModel gameModel;

    String pathToCurrentLevel;

    double pX, pY;
    double rX, rY;



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



        renderSurface.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                pX = e.getX();
                pY = e.getY();
            }
        });
        renderSurface.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                rX = e.getX();
                rY = e.getY();

                for(Line l : lines){
                    renderSurface.getChildren().remove(l);
                }

                shoot();

            }
        });

        renderSurface.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {


                double deltaX = e.getX() - pX;
                double deltaY = e.getY() - pY;

                gameModel.getPlayers()[0].getXPos();
                gameModel.getPlayers()[0].getYPos();

                double[] setX = new double[gameModel.getPlayers().length];
                double[] setY = new double[gameModel.getPlayers().length];
                int i = 0;
                for(Drawable x : gameModel.getPlayers()) {
                    setX[i] = x.getXPos() + deltaX;
                    setY[i] = x.getYPos() + deltaY;
                    i++;
                }

                drawArrow(setX, setY);
            }
        });

    }

    @Override
    public void commandFromModel(ObserverCommand command) {
        if(command == ObserverCommand.Win){
            gameModel.pauseGame();
        }
    }


    public void loadGameModel(String jsonPath) throws FileNotFoundException {
        pathToCurrentLevel = jsonPath;
        this.gameModel = GameModelBuilder.getGameModel(jsonPath);
        this.gameModel.addObserver(this);
        init();

        lines = new Line[gameModel.getPlayers().length];
        for(int i = 0; i<gameModel.getPlayers().length; i++){
            lines[i] = new Line();
        }


        // för test, ta bort när klart
    }


    private void init(){
        Drawable[] planets = gameModel.getPlanets();
        Drawable[] players = gameModel.getPlayers();
        Drawable[] targets = gameModel.getTargets();

        renderSurface.getChildren().clear();

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

    private void drawArrow(double[] setX, double[] setY) {

        for(Line l : lines){
            renderSurface.getChildren().remove(l);
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
        init();
    }

    @FXML
    private void backToMainMenu(){
        gameModel.pauseGame();
        this.gameModel = null;
        mediator.notify(this, MediatorCommand.STANDARD);
    }

}

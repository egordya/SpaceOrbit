package com.grupp7.spaceorbit.controllers;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
import model.gameModel.GameModel;
import model.modelObjects.ArrowObject;
import model.modelObjects.Geometry;
import model.modelObjects.JavaFXGeometry;
import utilitys.ArrowPolyLineFactory;
import utilitys.Vector2D;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameController implements EventHandler<MouseEvent> {

    private GameModel model;

    private Vector2D pressedPos = new Vector2D(0,0);

    final long timePeriod = 20;
    private Timer tr;
    private boolean isRunning;
    private boolean isPaused;

    int allowedMoves = 1;
    int current = 0;

    public GameController(GameModel model) {
        this.model = model;
    }

    public void setGameModel(GameModel model){
        this.model = model;
    }

    @Override
    public void handle(MouseEvent event) {
        EventType<? extends MouseEvent> type = event.getEventType();

        if (type == MouseEvent.MOUSE_PRESSED){
            handleMousePressed(event);
        }

        else if (type == MouseEvent.MOUSE_RELEASED){
            handleMouseReleased(event);
        }

        else if (type == MouseEvent.MOUSE_DRAGGED){
            handleMouseDragged(event);
        }
        else{
            throw new IllegalArgumentException("EventType not implemented");
        }
    }

    public void startGame(){
        if (!isRunning && !isPaused) {
            this.tr = new Timer();
            this.tr.schedule(getTimerTask(), 0, timePeriod);
            isRunning = true;
        }
    }

    public void togglePauseGame(){
        if(isRunning && !isPaused) {
            tr.cancel();
            isRunning = false;
            isPaused = true;
        }
        else if(!isRunning && isPaused) {
            isPaused = false;
            startGame();
        }
    }

    public void setPlayerVelocity(Vector2D[] vectors){
        if(current < allowedMoves) {
            current++;
            model.setPlayerVelocity(vectors);
            model.SetShowPlayerArrows(false);
        }

    }


    private void handleMousePressed(MouseEvent mouseEvent){
        pressedPos = new Vector2D(mouseEvent.getX(), mouseEvent.getY());
    }

    private void handleMouseReleased(MouseEvent mouseEvent){
        Vector2D releasedPos = new Vector2D(mouseEvent.getX(), mouseEvent.getY());

        Vector2D[] vectors = new Vector2D[model.getPlayers().length];
        for(int i = 0; i<model.getPlayers().length; i++){
            vectors[i] = releasedPos.sub(pressedPos);
        }
        boolean[] effectedByGravityBooleans = new boolean[model.getPlayers().length];
        for(int i = 0; i<model.getPlayers().length; i++){
            effectedByGravityBooleans[i] = true;
        }
        setPlayerVelocity(vectors);
        model.setPlayerAffectedByGravity(effectedByGravityBooleans);

    }

    private void handleMouseDragged(MouseEvent mouseEvent){

        if(current < allowedMoves){
            model.SetShowPlayerArrows(true);
        }

        Vector2D draggedPos = new Vector2D(mouseEvent.getX(), mouseEvent.getY());
        Vector2D delta = draggedPos.sub(pressedPos);

        ArrowPolyLineFactory arrowPolyLineFactoryFactory = new ArrowPolyLineFactory();

        ArrayList<ArrowObject> arrows = new ArrayList<>();
        for (int i = 0; i<model.getPlayers().length; i++){
            Vector2D playerPos = new Vector2D(model.getPlayers()[i].getXPos(), model.getPlayers()[i].getYPos());
            Polyline arrow = arrowPolyLineFactoryFactory.getArrow(playerPos, playerPos.add(delta));
            arrows.add(new ArrowObject(new JavaFXGeometry(arrow)));
        }

        model.setPlayersArrow(arrows.toArray(new ArrowObject[0]));

    }

    private TimerTask getTimerTask(){
        return new TimerTask(){
            @Override
            public void run(){
                model.gameStep(timePeriod * Math.pow(10, -3));
            }
        };
    }


}

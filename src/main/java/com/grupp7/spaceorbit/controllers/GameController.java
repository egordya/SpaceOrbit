package com.grupp7.spaceorbit.controllers;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import model.gameModel.GameModel;
import utilitys.Vector2D;

public class GameController implements EventHandler<MouseEvent> {

    private GameModel model;

    public GameController(GameModel model) {
        this.model = model;
    }

    @Override
    public void handle(MouseEvent event) {
        EventType type = event.getEventType();

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


    private void handleMousePressed(MouseEvent mouseEvent){
    //    rX = e.getX();
    //    rY = e.getY();
    }

    private void handleMouseReleased(MouseEvent mouseEvent){
        //double deltaX = e.getX() - pX;
        //double deltaY = e.getY() - pY;
        //shoot();
    }

    private void handleMouseDragged(MouseEvent mouseEvent){
        //double[] setX = new double[gameModel.getPlayers().length];
        //double[] setY = new double[gameModel.getPlayers().length];
        //int i = 0;
        //for(Drawable x : gameModel.getPlayers()) {
        //    setX[i] = x.getXPos() + deltaX;
        //   setY[i] = x.getYPos() + deltaY;
        //    i++;
    }

    private void shoot(){
        //double vY  = (rY - pY);
        //double vX = (rX - pX);


        //Vector2D[] vectors = new Vector2D[gameModel.getPlayers().length];
        //for(int i = 0; i<gameModel.getPlayers().length; i++){
        //    vectors[i] = new Vector2D(vX, vY);
       //}

        //gameModel.setPlayerVelocity(vectors);
        //gameModel.startGame();
    }
}

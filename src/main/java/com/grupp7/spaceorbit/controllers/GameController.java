package com.grupp7.spaceorbit.controllers;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import model.gameModel.GameModel;
import utilitys.Vector2D;

public class GameController implements EventHandler<MouseEvent> {

    private GameModel model;

    private Vector2D pressedPos = new Vector2D(0,0);

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

        model.SetShowPlayerArrows(false);
        model.setPlayerVelocity(vectors);
        model.startGame();

    }

    private void handleMouseDragged(MouseEvent mouseEvent){
        Vector2D draggedPos = new Vector2D(mouseEvent.getX(), mouseEvent.getY());
        Vector2D delta = draggedPos.sub(pressedPos);
        model.setPlayersArrow(delta);

    }


}

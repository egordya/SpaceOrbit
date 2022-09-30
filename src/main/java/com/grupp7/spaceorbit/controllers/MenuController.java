package com.grupp7.spaceorbit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MenuController extends AnchorPane{
    Mediator mediator;
    public MenuController(Mediator mediator) {

        this.mediator = mediator;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    @FXML
    public void testbutton(){
        mediator.notify(this, MediatorCommand.STANDARD);
    }
    @FXML
    public void quitgame(){System.exit(0);}


}





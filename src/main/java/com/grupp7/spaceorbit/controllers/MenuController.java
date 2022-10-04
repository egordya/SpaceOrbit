package com.grupp7.spaceorbit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.File;
import java.io.IOException;

public class MenuController extends AnchorPane {
    Mediator mediator;

    @FXML
    FlowPane lvlSelectorPane;


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
    public void lvlselector(){

    }

    @FXML
    public void testbutton() {
        mediator.notify(this, MediatorCommand.STANDARD);
    }

    @FXML
    public void quitgame() {
        System.exit(0);
    }

    @FXML
    public void settings() {
        this.getChildren().clear();

        this.getChildren().add(lvlSelectorPane);
        File[] lvlfolder = new File("src/main/resources/json/levels").listFiles();

        for (File file : lvlfolder) {
            if (file.isFile()) {
                lvlSelectorPane.getChildren().add(new Button((file.getName().replaceFirst("[.][^.]+$", ""))));}}}



}




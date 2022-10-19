package com.grupp7.spaceorbit.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.menuModel.MenuModel;
import javafx.event.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class MenuView extends AnchorPane{
    Mediator mediator;
    MenuModel model;

    @FXML
    FlowPane lvlSelectorPane;

    /**
     * Constructs a view for the menu
     * @param mediator the messenger between the observers and the model
     * @param model the Game Model
     */
    public MenuView(Mediator mediator, MenuModel model) {

        this.mediator = mediator;
        this.model = model;

        model.getAllLevels();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    /**
     * just a test function. Doesn't really do anything
     */
    @FXML
    public void testbutton(){
        try {
            mediator.notify(this, MediatorCommand.STANDARD);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * quits the game
     */
    @FXML
    public void quitgame(){System.exit(0);}


    /**
     * starts the process of building a level
     * @throws FileNotFoundException if the fxml-file for custom level builder is not found an exception will not be caught
     */
    @FXML
    public void buildNewLevel() throws FileNotFoundException {
        mediator.notify(this, MediatorCommand.BUILDLVL);
    }


    /**
     * takes the user to the area in which a level can be selected
     */
    @FXML
    public void lvlSelector() {
        this.getChildren().clear();

        this.getChildren().add(lvlSelectorPane);
        File[] lvlfolder = new File("src/main/resources/json/levels").listFiles();

        for (File file : lvlfolder) {
            if (file.isFile()) {
                lvlSelectorPane.getChildren().add(new Button((file.getName().replaceFirst("[.][^.]+$", ""))));}}}


    /**
     * retrieves an array of all remaining levels
     * @return an array of paths to json-files
     */
    public String[] getRemainingLevels() {return null;}

    /**
     * retrieves the current level
     * @return a poth to a json-file
     */
    public String getCurrentLevel(){
        //Button bp = (Button) e.getTarget();
        //String path = "src/main/resources/json/levels/" + bp.getText() + "json";
        //return path;
        return null;
    }

}





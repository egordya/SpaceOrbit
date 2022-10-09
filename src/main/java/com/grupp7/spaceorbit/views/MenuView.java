package com.grupp7.spaceorbit.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.menuModel.MenuModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MenuView extends AnchorPane{
    Mediator mediator;
    MenuModel model;

    @FXML
    FlowPane lvlSelectorPane;

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

    @FXML
    public void testbutton(){
        try {
            mediator.notify(this, MediatorCommand.STANDARD);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void quitgame(){System.exit(0);}


    @FXML
    public void buildNewLevel() throws FileNotFoundException {
        mediator.notify(this, MediatorCommand.BUILDLVL);
    }


    @FXML
    public void lvlSelector() {
        this.getChildren().clear();

        this.getChildren().add(lvlSelectorPane);
        File[] lvlfolder = new File("src/main/resources/json/levels").listFiles();

        for (File file : lvlfolder) {
            if (file.isFile()) {
                lvlSelectorPane.getChildren().add(new Button((file.getName().replaceFirst("[.][^.]+$", ""))));}}}




    public String[] getRemainingLevels() {return null;}

    public String getCurrentLevel(){
        //Button bp = (Button) e.getTarget();
        //String path = "src/main/resources/json/levels/" + bp.getText() + "json";
        //return path;
        return null;
    }

}





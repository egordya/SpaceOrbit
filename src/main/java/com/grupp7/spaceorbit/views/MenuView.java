package com.grupp7.spaceorbit.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import model.menuModel.MenuModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuView extends AnchorPane{
    Mediator mediator;
    MenuModel model;

    @FXML
    FlowPane lvlSelectorPane;
    @FXML
    StackPane sp;
    @FXML
    Button back;

    String[] remainingPaths;
    String path = "src/main/resources/json/levels/level1.json";

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
        back.setVisible(true);
        if(lvlSelectorPane.getChildren().size() < 3){
            createLvlButtons();
        }
    }

    public void back(){
        this.getChildren().remove(lvlSelectorPane);
        this.getChildren().add(sp);

    }


    public void getRemainingLvls(){
        List<String> level = new ArrayList<String>(List.of(model.getAllLevels()));
        String[] levels = model.getAllLevels();
        for (int i = 0; i< levels.length; i++){
            if(level.get(i).contains(path.substring(path.length()-10))){
                remainingPaths = Arrays.copyOfRange(levels,i+1, levels.length);
                break;
            }}}


    public void createLvlButtons(){
        List<Button> buttons = model.getlvlbuttons();
        for (Button button : buttons) {
            lvlSelectorPane.getChildren().add(button);
            button.setOnAction((new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    path = ("src/main/resources/json/levels/" + button.getText() + ".json");
                    getRemainingLvls();
                    testbutton();
                }}));}}

    public String getCurrentLevel(){
        //Button bp = (Button) e.getTarget();
        //String path = "src/main/resources/json/levels/" + bp.getText() + "json";
        //return path;
        return null;
    }

}





package com.grupp7.spaceorbit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import model.menuModel.MenuModel;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements Initializable, Mediator{

    @FXML
    StackPane theStackPane;

    MenuView menuView;
    GameView gameView;
    CustomLevelController customLevelController;


    /**
     * initializes the program. Creates necessary views and controllers
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuView = new MenuView(this, new MenuModel());
        gameView = new GameView(this);
        customLevelController = new CustomLevelController(this);


        theStackPane.getChildren().add(gameView);
        theStackPane.getChildren().add(menuView);


    }


    /**
     * changes which view that is displayed
     * @param pointer a pointer that points to the view that is currently displayed
     * @param command a command for more things needs to be done
     * @throws FileNotFoundException if the file of the view that shall be displayed the exception will not be caught
     */
    @Override
    public void notify(Object pointer, MediatorCommand command) throws FileNotFoundException {
        if (pointer == menuView){
            theStackPane.getChildren().clear();
            theStackPane.getChildren().add(gameView);


            //String[] levelPaths = menuView.getRemainingLevels();
            //List<String> levelPathsList = Arrays.stream(levelPaths).toList();

            //String firstLevelPath = levelPathsList.get(0);
            //levelPathsList.remove(firstLevelPath);
            //String[] levelPathsArray = levelPathsList.toArray(new String[0]);

            //gameView.loadGameModel(menuView.getCurrentLevel(), null);
            gameView.loadGameModel("src/main/resources/json/levels/level2.json", null);
        }

        if((pointer == menuView) && (command == MediatorCommand.BUILDLVL)){
            theStackPane.getChildren().clear();
            theStackPane.getChildren().add(customLevelController);
        }

        else if (pointer == gameView) {
            theStackPane.getChildren().clear();
            theStackPane.getChildren().add(menuView);
        }
    }

    private void showMenu(){

    }

    private void showGame(){

    }

}

package com.grupp7.spaceorbit.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import model.gameModel.GameModelBuilder;
import model.menuModel.MenuModel;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements Initializable, Mediator{

    @FXML
    StackPane theStackPane;

    MenuView menuView;
    GameView gameView;
    CustomLevelView customLevelView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuView = new MenuView(this, new MenuModel());
        gameView = new GameView(this);
        customLevelView = new CustomLevelView(this);

        theStackPane.getChildren().add(gameView);
        theStackPane.getChildren().add(menuView);

    }


    @Override
    public void notify(Object pointer, MediatorCommand command) throws FileNotFoundException {
        if ((pointer == menuView) && (command == MediatorCommand.STANDARD)){
            theStackPane.getChildren().clear();
            theStackPane.getChildren().add(gameView);

            //String[] levelPaths = menuView.getRemainingLevels();
            //List<String> levelPathsList = Arrays.stream(levelPaths).toList();

            //String firstLevelPath = levelPathsList.get(0);
            //levelPathsList.remove(firstLevelPath);
            //String[] levelPathsArray = levelPathsList.toArray(new String[0]);

            //gameView.loadGameModel(menuView.getCurrentLevel(), null);
            menuView.getRemainingLvls();
            gameView.loadGameModel(menuView.path, menuView.remainingPaths);
        }

        else if((pointer == menuView) && (command == MediatorCommand.BUILDLVL)){
            theStackPane.getChildren().clear();
            theStackPane.getChildren().add(customLevelView);
        }

        else if((pointer == customLevelView) && (command == MediatorCommand.STANDARD)){
            theStackPane.getChildren().clear();
            theStackPane.getChildren().add(menuView);
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

package com.grupp7.spaceorbit;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;

public class MenuController {


    public void startGame(ActionEvent event){
        HelloApplication main = new HelloApplication();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        main.startGameMain();
    }

}

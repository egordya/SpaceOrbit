package com.grupp7.spaceorbit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;

import javax.json.*;
//import org.json.simple.JSONobject;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class CustomLevelController extends AnchorPane {

    @FXML
    private AnchorPane addObjectCustomLevelAnchorPane;

    @FXML
    private ComboBox<String> imageComboBoxInput;

    @FXML
    private TextField massInput;

    @FXML
    private AnchorPane nameCustomLevelAnchorPane;

    @FXML
    private TextField nameInput;

    @FXML
    private ImageView planetPreview;

    @FXML
    private TextField radiusInput;

    @FXML
    private TextField startXInput;

    @FXML
    private TextField startYInput;

    @FXML
    private ComboBox<Boolean> staticComboBoxInput;

    @FXML
    private ChoiceBox<String> typeChoiceBox;

    @FXML
    private TextField levelNameTextField;

    @FXML
    private AnchorPane levelOverviewAnchorPane;

    @FXML
    private Label levelNameLabel;

    String levelName;
    Mediator mediator;
    Writer writer;

    ObservableList<String> finalTestFile = FXCollections.observableArrayList();
    ObservableList<String> finalFile = FXCollections.observableArrayList();
    ObservableList<String> planetList = FXCollections.observableArrayList();
    ObservableList<String> playerList = FXCollections.observableArrayList();
    ObservableList<String> targetList = FXCollections.observableArrayList();
    JsonArray planetListt = Json.createArrayBuilder().build();
    JsonArray targetListt = Json.createArrayBuilder().build();
    JsonArray playerListt = Json.createArrayBuilder().build();
    JsonObject planetObject = Json.createObjectBuilder().build();
    JsonArrayBuilder someBuilder = Json.createArrayBuilder();



    public CustomLevelController(Mediator mediator) {
        this.mediator = mediator;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/customlevel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public JsonObject submitButton() throws IOException {
        String name = nameInput.getText();
        int mass = Integer.parseInt(massInput.getText());
        int radius = Integer.parseInt(radiusInput.getText());
        double posX = Double.parseDouble(startXInput.getText());
        double posY = Double.parseDouble(startYInput.getText());
        String imagePath = imageComboBoxInput.getValue();
        String type = typeChoiceBox.getValue();

        JsonObject valuetest = Json.createObjectBuilder()
                    .add("type",type)
                    .add("name", name)
                    .add("mass", mass)
                    .add("startPosX", posX)
                    .add("startPosY", posY)
                    .add("imagePath", imagePath)
                    .add("radius", radius)
                    .build();

        someBuilder.add(valuetest);

      /*  System.out.println(valuetest.get("type").toString());
            if(type.equals("Planet")){
              //  planetObject.put("planet", valuetest);
                //System.out.println(planetObject);
            }
            if(type.equals("Target")){
                targetList.add(valuetest.toString());

            }
            if(type.equals("PlayerObject")){
            playerList.add(valuetest.toString());
        }*/
            return valuetest;
    }

    @FXML
    public void submitButtonTwo() throws IOException {
        JsonArray jArray = someBuilder.build();
        JsonObject jsonFile = Json.createObjectBuilder()
                .add("planets", planetListt)
                .add("targets", targetListt)
                .build();

        FileWriter fileWriter = new FileWriter("src/main/resources/json/levels/" + levelName + ".json");
        JsonWriter jWrite = Json.createWriter(fileWriter);
        jWrite.writeArray(jArray);
        jWrite.close();

/*
        jGen.writeStartArray()
                .write(planetList.toString())
                .writeEnd()
                .writeStartArray()
                .write(targetList.toString())
                .writeEnd();

                jGen.close();
        System.out.println(jGen.toString());*/

    }

    @FXML
    public void nameNewLevel() throws IOException {
        levelName = levelNameTextField.getText();
        levelNameLabel.setText(levelName);
        nameCustomLevelAnchorPane.toBack();
    }

    @FXML
    public void addObject() throws IOException{
        levelOverviewAnchorPane.toBack();
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}

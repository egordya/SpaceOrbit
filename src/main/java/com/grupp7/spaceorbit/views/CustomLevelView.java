package com.grupp7.spaceorbit.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.json.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class CustomLevelView extends AnchorPane {

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

    JsonArrayBuilder planetArrayBuilder = Json.createArrayBuilder();
    JsonArrayBuilder targetArrayBuilder = Json.createArrayBuilder();
    JsonArrayBuilder playerArrayBuilder = Json.createArrayBuilder();

    public CustomLevelView(Mediator mediator) {

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
    public void submitButton() throws IOException {

        String name = nameInput.getText();
        int mass = Integer.parseInt(massInput.getText());
        int radius = Integer.parseInt(radiusInput.getText());
        double posX = Double.parseDouble(startXInput.getText());
        double posY = Double.parseDouble(startYInput.getText());
        String imagePath = imageComboBoxInput.getValue();
        String type = typeChoiceBox.getValue();
        imageComboBoxInput.getSelectionModel().selectFirst();
        String isFixed = staticComboBoxInput.getValue().toString();

        switch (imagePath) {
            // Planets
            case "Dark Green" -> imagePath = "src/main/resources/img/darkGreen.gif";
            case "Cloudy Blue" -> imagePath = "src/main/resources/img/cloudyBlue.gif";
            case "Dark Red" -> imagePath = "src/main/resources/img/darkRed.gif";
            case "Hazy Clouds" -> imagePath = "src/main/resources/img/hazyClouds.gif";
            case "Light Green" -> imagePath = "src/main/resources/img/lightGreen.gif";
            case "PlaKanyet West" -> imagePath = "src/main/resources/img/kanye.jpg";
            // Player Objects
            case "Black and White" -> imagePath = "src/main/resources/img/blackWhitePlayer2.gif";
        }

        JsonObject jsonLevelObject = Json.createObjectBuilder()
                    .add("type",type)
                    .add("name", name)
                    .add("mass", mass)
                    .add("startPosX", posX)
                    .add("startPosY", posY)
                    .add("imagePath", imagePath)
                    .add("fixedPosition", isFixed)
                    .add("radius", radius)
                    .build();

        String typeString = jsonLevelObject.getString("type");
        if(typeString.equals("Planet")){
            planetArrayBuilder.add(jsonLevelObject);
        }else if(typeString.equals("Target")){
            targetArrayBuilder.add(jsonLevelObject);
        }else{
            playerArrayBuilder.add(jsonLevelObject);
        }
    }

    @FXML
    public void submitButtonTwo() throws IOException {
        JsonArray planetArray = planetArrayBuilder.build();
        JsonArray targetArray = targetArrayBuilder.build();
        JsonArray playerArray = playerArrayBuilder.build();

        JsonObject jsonFile = Json.createObjectBuilder()
                .add("planets", planetArray)
                .add("targets", targetArray)
                .add("players", playerArray)
                .build();

        FileWriter fileWriter = new FileWriter("src/main/resources/json/levels/" + levelName + ".json");
        JsonWriter jWrite = Json.createWriter(fileWriter);
        jWrite.writeObject(jsonFile);
        jWrite.close();

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

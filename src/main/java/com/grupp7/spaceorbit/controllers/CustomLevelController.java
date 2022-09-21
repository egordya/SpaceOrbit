package com.grupp7.spaceorbit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import java.io.FileWriter;
import java.io.IOException;


public class CustomLevelController extends AnchorPane {

    @FXML
    private ComboBox<String> imageComboBoxInput;

    @FXML
    private TextField massInput;

    @FXML
    private TextField nameInput;

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

    Mediator mediator;

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
    public void submitButton() throws IOException {
        String name = nameInput.getText();
        int mass = Integer.parseInt(massInput.getText());
        int radius = Integer.parseInt(radiusInput.getText());
        double posX = Double.parseDouble(startXInput.getText());
        double posY = Double.parseDouble(startYInput.getText());
        String imagePath = imageComboBoxInput.getValue();
        String type = typeChoiceBox.getValue();


        JsonObject json = Json.createObjectBuilder()
                .add("Type", type).build();

        JsonBuilderFactory factory = Json.createBuilderFactory(json);

        JsonArray value = factory.createArrayBuilder()
                .add(factory.createObjectBuilder()
                        .add("name", name)
                        .add("mass", mass)
                        .add("startPosX", posX)
                        .add("startPosY", posY)
                        .add("imagePath", imagePath)
                        .add("radius", radius))
                .build();

        JsonObject finalObject = Json.createObjectBuilder()
                .add(type, value).build();

        FileWriter fileWriter = new FileWriter("src/main/resources/json/levels/" + name +  ".json");
        System.out.println(finalObject);

        fileWriter.write(String.valueOf(finalObject));
        fileWriter.flush();
    }




    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}

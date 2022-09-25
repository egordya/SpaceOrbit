package com.grupp7.spaceorbit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;

import javax.json.*;
import java.io.FileWriter;
import java.io.IOException;


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

    Mediator mediator;
    ObservableList<String> finalTestFile = FXCollections.observableArrayList();
    JsonObject finalPlanetObject = Json.createObjectBuilder()
            .add("type", "Planet").build();

    ObservableList<String> finalFile = FXCollections.observableArrayList();



    public CustomLevelController(Mediator mediator) {
        this.mediator = mediator;
        finalTestFile.add(finalPlanetObject.toString());
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
    public ObservableList<String> submitButton() throws IOException {
        String name = nameInput.getText();
        int mass = Integer.parseInt(massInput.getText());
        int radius = Integer.parseInt(radiusInput.getText());
        double posX = Double.parseDouble(startXInput.getText());
        double posY = Double.parseDouble(startYInput.getText());
        String imagePath = imageComboBoxInput.getValue();
        String type = typeChoiceBox.getValue();

        JsonObject json = Json.createObjectBuilder()
                .add("Type", type).build();

        JsonBuilderFactory factory = Json.createBuilderFactory(finalPlanetObject);

        JsonArray value = factory.createArrayBuilder()
                .add(factory.createObjectBuilder()
                        .add("name", name)
                        .add("mass", mass)
                        .add("startPosX", posX)
                        .add("startPosY", posY)
                        .add("imagePath", imagePath)
                        .add("radius", radius))
                .build();


            finalTestFile.add(value.toString());

        System.out.println("final Planet text: " + finalPlanetObject);
        System.out.println("Final file to be written to json file: " + finalTestFile.toString());
        return finalTestFile;
    }

    @FXML
    public void submitButtonTwo() throws IOException {
        //Kanske loopa igenom varje array för att lägga till i en ny array?
        finalFile.add(finalPlanetObject.toString() + finalTestFile.toString());
        FileWriter fileWriter = new FileWriter("src/main/resources/json/levels/testingpurpose.json");
        fileWriter.write(String.valueOf(finalFile));
        fileWriter.flush();
    }


    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}

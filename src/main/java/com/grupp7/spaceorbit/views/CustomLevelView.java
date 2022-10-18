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
import model.customLevelModels.CustomLevelModel;

import javax.json.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class CustomLevelView extends AnchorPane {
    CustomLevelModel cml = new CustomLevelModel();
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
        String isFixed = staticComboBoxInput.getValue().toString();
        cml.createJsonObject(name, mass, radius, posX, posY, imagePath, type, isFixed);
    }

    @FXML
    public void submitButtonTwo() throws IOException {
        cml.finishAll(levelName);
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

package com.grupp7.spaceorbit.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.customLevelModels.CustomLevelModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;


public class CustomLevelView extends AnchorPane {
    CustomLevelModel cml = new CustomLevelModel();

    @FXML
    ListView<String> objectsListView = new ListView<String>();

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
    private TextField velYInput;

    @FXML
    private TextField velXInput;

    @FXML
    private ImageView planetPreview;

    @FXML
    private TextField radiusInput;

    @FXML
    private TextField startXInput;

    @FXML
    private TextField startYInput;

    @FXML
    private ComboBox<String> staticComboBoxInput;

    @FXML
    private ChoiceBox<String> typeChoiceBox;

    @FXML
    private TextField levelNameTextField;

    @FXML
    private AnchorPane levelOverviewAnchorPane;

    @FXML
    private Label levelNameLabel;

    @FXML
    private Label objectAdded;

    @FXML
    private Label levelNameMissing;

    @FXML
    private Label levelAddedLabel;

    String levelName;
    Mediator mediator;
    ObservableList<String> objectList = FXCollections.observableArrayList();

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

    /**
     * @throws IOException
     * Gathers user input data from GUI, checks if all mandatory parameters are set.
     * Adds type and starting positions to list of objects in objectsListView.
     */
    @FXML
    public void submitButton() throws IOException {
        String name = nameInput.getText();
        int mass = Integer.parseInt(massInput.getText());
        int radius = Integer.parseInt(radiusInput.getText());
        double posX = Double.parseDouble(startXInput.getText());
        double posY = Double.parseDouble(startYInput.getText());
        String imagePath = imageComboBoxInput.getValue();
        String type = typeChoiceBox.getValue();
        String isFixed = staticComboBoxInput.getValue();
        double velX = Double.parseDouble(velXInput.getText());
        double velY = Double.parseDouble(velYInput.getText());

        if(!Objects.equals(name, "") && type != null && isFixed != null && imagePath != null) {
            cml.createJsonObject(name, mass, radius, posX, posY, velX, velY, imagePath, type, isFixed);
            objectAdded.setText(name + " added!");
            objectList.add(name + "  Type:   " + type + "    Start position(x,y):   (" + posX + " , " + posY + ")");
        }else{
            objectAdded.setText("Missing parameters!");
        }
    }

    /**
     * @throws IOException
     * Sends gathered data to model.
     * If cml.levelBuilt returns true, confirms that level json is added.
     */
    @FXML
    public void createLevel() throws IOException {
            cml.createJson(levelName);
            if(cml.levelBuilt){
                levelAddedLabel.setText("Your level was added to the Game. Enjoy!");
            }else{
                levelAddedLabel.setText("You are missing required objects (Planet, Target, Player)");
            }
    }


    /**
     * @throws IOException
     * Sets name of level.
     * Used for naming json file.
     */
    @FXML
    public void nameNewLevel() throws IOException {
        levelName = levelNameTextField.getText();
        levelName = levelName.replace(" ", "_");
        if(!levelName.equals("")) {
            levelNameLabel.setText(levelName);
            nameCustomLevelAnchorPane.toBack();
            levelNameMissing.setText("");
        }else{
            levelNameMissing.setText("Please write a name for your level! ");
        }
    }

    @FXML
    public void showAddObjectListPane() throws IOException{
        addObjectCustomLevelAnchorPane.toFront();
    }

    @FXML
    public void backToList() throws IOException{
        objectsListView.setItems(objectList);
        levelOverviewAnchorPane.toFront();
    }

    @FXML
    public void backToMainMenu() throws FileNotFoundException {
        objectsListView.toFront();
        mediator.notify(this, MediatorCommand.STANDARD);

    }
    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}

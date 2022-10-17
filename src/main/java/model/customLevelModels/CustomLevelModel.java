package model.customLevelModels;

import javax.json.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class CustomLevelModel {
        Boolean playerExists = false;
        Boolean targetExists = false;
        Boolean planetExists = false;
        public Boolean levelBuilt = false;

        Writer writer;

        JsonArrayBuilder planetArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder targetArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder playerArrayBuilder = Json.createArrayBuilder();

        /**
         * @param imageName User input from list in GUI.
         * Based on input given by the user, returns image path
         * If, for some reason, none is given, returns standard image.
         * @return imagePath
         */
        private String setImagePath(String imageName) {
                String imagePath;
                switch (imageName) {
                        // Planets
                        case "Dark Green" -> imagePath = "/img/darkGreen.gif";
                        case "Cloudy Blue" -> imagePath = "src/main/resources/img/cloudyBlue.gif";
                        case "Dark Red" -> imagePath = "src/main/resources/img/darkRed.gif";
                        case "Hazy Clouds" -> imagePath = "src/main/resources/img/hazyClouds.gif";
                        case "Light Green" -> imagePath = "src/main/resources/img/lightGreen.gif";
                        case "PlaKanyet West" -> imagePath = "src/main/resources/img/kanye.jpg";
                        // Target Objects

                        // Player Objects
                        case "Black/White" -> imagePath = "src/main/resources/img/blackWhitePlayer.gif";
                        case "Gold" -> imagePath = "src/main/resources/img/goldPlayer.gif";
                        case "Red" -> imagePath = "src/main/resources/img/redPlayer.gif";
                        case "White/Orange" -> imagePath = "src/main/resources/img/whiteOrangePlayer.gif";
                        default -> imagePath = "src/main/resources/img/kanye.jpg";
                }
                return imagePath;
        }

        /**
         * Converts given strings to JsonObject.
         * Objects can be of type Player, Target or Planet
         * @param name name of object
         * @param mass mass of object
         * @param radius radius of object
         * @param posX starting position of object, x-axis
         * @param posY starting position of object, y-axis
         * @param velX starting velocity of object, x-axis
         * @param velY starting position of object, y-axis
         * @param imageName name of image (displayed in GUI), converts to imagePath
         * @param type type of object
         * @param isFixed decides whether the object's position is static.
         */
        public void createJsonObject(String name, double mass, double radius, double posX, double posY,
                                     double velX, double velY, String imageName, String type, String isFixed){

                String imagePath = setImagePath(imageName);
                JsonObject jsonLevelObject = Json.createObjectBuilder()
                        .add("type",type)
                        .add("name", name)
                        .add("mass", mass)
                        .add("radius", radius)
                        .add("posX", posX)
                        .add("posY", posY)
                        .add("velX", velX)
                        .add("velY", velY)
                        .add("image_path", imagePath)
                        .add("isAffectedByGravity", isFixed)
                        .build();

                String typeString = jsonLevelObject.getString("type");
                addToList(jsonLevelObject, typeString);
        }

        /**
         * @param jsonLevelObject JsonObject created by player(Target, Player or Planet)
         * @param typeString type of object the player created
         * Method adds JsonObject to a JsonArrayBuilder
         * One boolean for each type of object. All must be true for level to be created.
         */
        private void addToList(JsonObject jsonLevelObject, String typeString) {
                if(typeString.equals("Planet")){
                        planetArrayBuilder.add(jsonLevelObject);
                        planetExists = true;
                }else if(typeString.equals("Target")){
                        targetArrayBuilder.add(jsonLevelObject);
                        targetExists = true;
                }else{
                        playerArrayBuilder.add(jsonLevelObject);
                        playerExists = true;
                }
        }

        /**
         * @param levelName Level name set by user
         * Method builds the jsonArray as long as all building blocks are supplied.
         * Once built, may not be modified as of current version.
         *
         * @throws IOException
         */
        public void createJson(String levelName) throws IOException {
                if(targetExists && playerExists && planetExists) {

                        JsonArray planetArray = planetArrayBuilder.build();
                        JsonArray targetArray = targetArrayBuilder.build();
                        JsonArray playerArray = playerArrayBuilder.build();

                        JsonObject jsonFile = Json.createObjectBuilder()
                                .add("planets", planetArray)
                                .add("targets", targetArray)
                                .add("players", playerArray)
                                .build();

                        writeJsonFile(levelName, jsonFile);
                }else{
                        levelBuilt = false;
                }
        }

        /**
         * @param levelName Level name set by user
         * @param jsonFile JsonFile contains all previously build JsonObjects
         * Writes json file to specified json folder.
         * @throws IOException
         */
        private void writeJsonFile(String levelName, JsonObject jsonFile) throws IOException {
                FileWriter fileWriter = new FileWriter("src/main/resources/json/levels/" + levelName + ".json");
                JsonWriter jWrite = Json.createWriter(fileWriter);
                jWrite.writeObject(jsonFile);
                jWrite.close();
                levelBuilt = true;
        }

        /**
         * @return returns true if a level has been to the json level folder.
         *
         */
        public boolean levelBuilt(){
                return levelBuilt;
        }
}



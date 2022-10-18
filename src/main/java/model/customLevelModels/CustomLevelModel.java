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
                        case "Cloudy Blue" -> imagePath = "/img/cloudyBlue.gif";
                        case "Dark Red" -> imagePath = "/img/darkRed.gif";
                        case "Hazy Clouds" -> imagePath = "/img/hazyClouds.gif";
                        case "Light Green" -> imagePath = "/img/lightGreen.gif";
                        case "PlaKanyet West" -> imagePath = "/img/kanye.jpg";
                        // Target Objects

                        // Player Objects
                        case "Black/White" -> imagePath = "/img/blackWhitePlayer.gif";
                        case "Gold" -> imagePath = "/img/goldPlayer.gif";
                        case "Red" -> imagePath = "/img/redPlayer.gif";
                        case "White/Orange" -> imagePath = "/img/whiteOrangePlayer.gif";
                        default -> imagePath = "/img/kanye.jpg";
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
                                     double velX, double velY, String imageName, String type, Boolean isFixed){
                String imagePath = setImagePath(imageName);
                JsonObject jsonLevelObject = Json.createObjectBuilder()
                        .add("name", name)
                        .add("mass", mass)
                        .add("radius", radius)
                        .add("isAffectedByGravity", isFixed)
                        .add("posX", posX)
                        .add("posY", posY)
                        .add("velX", velX)
                        .add("velY", velY)
                        .add("img_path", imagePath.toString().replace("\"", ""))
                        .build();


                addToList(jsonLevelObject, type);
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
                                .add("playerObject", playerArray)
                                .add("target", targetArray)
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



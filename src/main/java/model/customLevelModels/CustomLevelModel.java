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

                        FileWriter fileWriter = new FileWriter("src/main/resources/json/levels/" + levelName + ".json");
                        JsonWriter jWrite = Json.createWriter(fileWriter);
                        jWrite.writeObject(jsonFile);
                        jWrite.close();
                        levelBuilt = true;
                }else{
                        levelBuilt = false;
                }
        }
        public boolean levelBuilt(){
                return levelBuilt;
        }
}



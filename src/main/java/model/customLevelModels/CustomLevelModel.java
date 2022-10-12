package model.customLevelModels;

import javax.json.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class CustomLevelModel {

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
                        // Player Objects
                        case "Black and White" -> imagePath = "src/main/resources/img/blackWhitePlayer2.gif";
                        default -> imagePath = "kanye";
                }
                return imagePath;
        }

        public void createJsonObject(String name, double mass, double radius, double posX, double posY,
                                     String imageName, String type, String isFixed){

                String imagePath = setImagePath(imageName);
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

        public void createJson(String levelName) throws IOException {
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
}



package com.grupp7.spaceorbit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.json.*;


class LevelModel {

    public static void main(String[] args)  throws Exception {

        JsonObject levelObject = getJsonObject();

        PlanetModel.createPlanet(levelObject);
        PlayerModel.createPlayerObject(levelObject);
        TargetModel.createTarget(levelObject);
    }


    private static JsonObject getJsonObject() throws FileNotFoundException {
        String jsonPath = "src/main/resources/json/levels/level2.json";
        InputStream levelTest = new FileInputStream(jsonPath);
        JsonReader reader = Json.createReader(levelTest);
        JsonObject levelObject = reader.readObject();
        reader.close();
        return levelObject;
    }
}
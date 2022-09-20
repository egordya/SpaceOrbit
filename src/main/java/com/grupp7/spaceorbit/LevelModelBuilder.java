package com.grupp7.spaceorbit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.json.*;


class LevelModelBuilder {
    ArrayList<CelestialObjectBuilder> planets = new ArrayList<>();
    ArrayList<PlayerModel> players = new ArrayList<>();                         //Anteckningar för Olof och endast Olof dessa tre listor behöver man slå ihop till en lista av normalCelestialObjects
    ArrayList<TargetModel> targets = new ArrayList<>();                         // All data ska in som parametrar new CelestialObject()

    public void buildLevel()  throws Exception {

        JsonObject levelObject = getJsonObject();
        CelestialObjectBuilder.createPlanet(levelObject,planets);
        PlayerModel.createPlayerObject(levelObject, players);
        TargetModel.createTarget(levelObject, targets);
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
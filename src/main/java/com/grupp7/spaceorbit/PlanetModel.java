package com.grupp7.spaceorbit;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class PlanetModel {
    JsonValue planetName;
    JsonValue planetMass;
    JsonValue planetRadius;
    JsonValue fixedPosition;
    JsonValue posX;
    JsonValue posY;
    JsonValue velX;
    JsonValue velY;
    JsonValue img_path;

    public static void createPlanet(JsonObject levelObject) {
        JsonArray planetArray = levelObject.getJsonArray("planets");

        for (int i = 0; i < planetArray.size(); i++) {
            JsonObject planetObject = planetArray.getJsonObject(i);
            PlanetModel planetModel = new PlanetModel(planetObject);
            System.out.println(planetModel);
        }
    }

        public PlanetModel(JsonObject planetObject) {
            planetName = planetObject.get("name");
            planetMass = planetObject.get("mass");
            planetRadius = planetObject.get("radius");
            fixedPosition = planetObject.get("fixedPosition");
            posX = planetObject.get("posX");
            posY = planetObject.get("posY");
            velX = planetObject.get("velX");
            velY = planetObject.get("velY");
            img_path = planetObject.get("img_path");
        }
}

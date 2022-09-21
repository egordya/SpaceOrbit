package model.modelObjects;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.ArrayList;

public class CelestialObjectBuilder {
    JsonValue planetName;
    JsonValue planetMass;
    JsonValue planetRadius;
    JsonValue fixedPosition;
    JsonValue posX;
    JsonValue posY;
    JsonValue velX;
    JsonValue velY;
    JsonValue img_path;

    public static ArrayList<CelestialObjectBuilder> createPlanet(JsonObject levelObject, ArrayList<CelestialObjectBuilder> allObjects) {
        JsonArray planetArray = levelObject.getJsonArray("planets");

        for (int i = 0; i < planetArray.size(); i++) {
            JsonObject planetObject = planetArray.getJsonObject(i);
            CelestialObjectBuilder celestialObjectBuilder = new CelestialObjectBuilder(planetObject);
            allObjects.add(celestialObjectBuilder);
            System.out.println(celestialObjectBuilder);
        }
        return allObjects;
    }

        public CelestialObjectBuilder(JsonObject planetObject) {
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

package com.grupp7.spaceorbit;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.ArrayList;

public class PlayerModel {
    JsonValue playerName;
    JsonValue playerMass;
    JsonValue playerRadius;
    JsonValue fixedPosition;
    JsonValue posX;
    JsonValue posY;
    JsonValue velX;
    JsonValue velY;
    JsonValue img_path;

    public static void createPlayerObject(JsonObject levelObject, ArrayList<PlayerModel> allobjects) {
        JsonArray playerArray = levelObject.getJsonArray("playerObject");
        JsonObject playerObject = playerArray.getJsonObject(0);
        PlayerModel playerModel = new PlayerModel(playerObject);
        allobjects.add(playerModel);
        System.out.println(playerModel);
    }

        public PlayerModel(JsonObject playerObject) {
            playerName = playerObject.get("name");
            playerMass = playerObject.get("mass");
            playerRadius = playerObject.get("radius");
            fixedPosition = playerObject.get("fixedPosition");
            posX = playerObject.get("posX");
            posY = playerObject.get("posY");
            velX = playerObject.get("velX");
            velY = playerObject.get("velY");
            img_path = playerObject.get("img_path");
        }
}

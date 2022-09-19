package com.grupp7.spaceorbit;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class TargetModel {

    JsonValue targetName;
    JsonValue targetMass;
    JsonValue targetRadius;
    JsonValue fixedPosition;
    JsonValue posX;
    JsonValue posY;
    JsonValue velX;
    JsonValue velY;
    JsonValue img_path;


    public static void createTarget(JsonObject levelObject) {
        JsonArray targetArray = levelObject.getJsonArray("target");
        for (int i = 0; i < targetArray.size(); i++) {
            JsonObject targetObject = targetArray.getJsonObject(i);
            TargetModel targetModel = new TargetModel(targetObject);
            System.out.println(targetModel);
        }
    }


        public TargetModel(JsonObject targetObject) {
            targetName = targetObject.get("name");
            targetMass = targetObject.get("mass");
            targetRadius = targetObject.get("radius");
            fixedPosition = targetObject.get("fixedPosition");
            posX = targetObject.get("posX");
            posY = targetObject.get("posY");
            velX = targetObject.get("velX");
            velY = targetObject.get("velY");
            img_path = targetObject.get("img_path");
    }
}


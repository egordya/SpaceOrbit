package com.grupp7.spaceorbit;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.stream.JsonParser;

public class LevelModel {

    public static void main(String[] args)  throws Exception {

        InputStream levelTest = new FileInputStream("/Users/madmax/Documents/level1.json");
        JsonReader reader = Json.createReader(levelTest);
        JsonObject levelObject = reader.readObject();
        reader.close();

        JsonArray planetArray = levelObject.getJsonArray("planets");
        JsonObject planetObject = planetArray.getJsonObject(1);
        System.out.println(planetObject.getString("name"));
        System.out.println(planetArray.getJsonObject(0).getString("name"));

        String name = planetObject.getString("name");

        System.out.println(name);

    }
}
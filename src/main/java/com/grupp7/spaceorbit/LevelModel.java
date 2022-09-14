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
    public static void main(String[] args) {
        // Create Json and print
        JsonObject json = Json.createObjectBuilder()
                .add("name", "Falco")
                .add("age", BigDecimal.valueOf(3))
                .add("biteable", Boolean.FALSE).build();
        String result = json.toString();

        System.out.println(result);

        // Read back
        JsonReader jsonReader = Json.createReader(new StringReader("{\"name\":\"Falco\",\"age\":3,\"bitable\":false}"));
        JsonObject jobj = jsonReader.readObject();
        System.out.println(jobj);

        // Output
        //  {"name":"Falco","age":3,"biteable":false}
        //  {"name":"Falco","age":3,"bitable":false}

    }
}
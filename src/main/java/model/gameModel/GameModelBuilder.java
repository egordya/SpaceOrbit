package model.gameModel;

import model.gravitationModel.GravitationModel;
import model.modelObjects.CelestialObject;
import utilitys.Vector2D;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;


public class GameModelBuilder {


    public static GameModel getGameModel(String pathToJsonLevel) throws FileNotFoundException {


        String jsonPath = "src/main/resources/json/levels/level1.json";
        InputStream levelTest = new FileInputStream(pathToJsonLevel);
        JsonReader reader = Json.createReader(levelTest);
        JsonObject levelObject = reader.readObject();
        reader.close();

        ArrayList<CelestialObject> planets = getPlanets(levelObject);
        ArrayList<CelestialObject> targets = getTargets(levelObject);
        ArrayList<CelestialObject> players = getPlayers(levelObject);

        ArrayList<CelestialObject> allCelestialObjects = new ArrayList<>();
        allCelestialObjects.addAll(planets);
        allCelestialObjects.addAll(targets);
        allCelestialObjects.addAll(players);

        CelestialObject[] allCelestialObjectsArray = allCelestialObjects.toArray(new CelestialObject[0]);

        GravitationModel gravitationModel = createGravitationModel(allCelestialObjectsArray);

        GameModel product = new GameModel();
        product.setGravitationModel(gravitationModel);
        product.setPlanets(planets.toArray(new CelestialObject[0]));
        product.setTargets(targets.toArray(new CelestialObject[0]));
        product.setPlayers(players.toArray(new CelestialObject[0]));
        product.setAllCelestialObjects(allCelestialObjectsArray);

        return product;
    }

    private static GravitationModel createGravitationModel(CelestialObject[] allObjects){
        GravitationModel gravitationModel = new GravitationModel(allObjects);
        return gravitationModel;
    }

    private static ArrayList<CelestialObject> getPlanets(JsonObject levelObject){
        ArrayList<CelestialObject> planets = new ArrayList<>();
        JsonArray planetArray = levelObject.getJsonArray("planets");
        for (int i = 0; i < planetArray.size(); i++) {
            JsonObject planetObject = planetArray.getJsonObject(i);
            planets.add(createCelestialObjects(planetObject, "planet"));
        }
        return planets;
    }

    private static ArrayList<CelestialObject> getTargets(JsonObject levelObject){
        ArrayList<CelestialObject> targets = new ArrayList<>();
        JsonArray planetArray = levelObject.getJsonArray("target");
        for (int i = 0; i < planetArray.size(); i++) {
            JsonObject planetObject = planetArray.getJsonObject(i);
            targets.add(createCelestialObjects(planetObject, "target"));
        }
        return targets;
    }

    private static ArrayList<CelestialObject> getPlayers(JsonObject levelObject){
        ArrayList<CelestialObject> player = new ArrayList<>();
        JsonArray planetArray = levelObject.getJsonArray("playerObject");
        for (int i = 0; i < planetArray.size(); i++) {
            JsonObject planetObject = planetArray.getJsonObject(i);
            player.add(createCelestialObjects(planetObject, "player"));
        }
        return player;
    }

    private static CelestialObject createCelestialObjects(JsonObject jsonObject, String type){
        String planetName = jsonObject.get("name").toString();
        double planetMass = Double.parseDouble(jsonObject.get("mass").toString());
        double planetRadius = Double.parseDouble(jsonObject.get("radius").toString());
        boolean isAffectedByGravity = Boolean.parseBoolean(jsonObject.get("isAffectedByGravity").toString());
        double posX = Double.parseDouble(jsonObject.get("posX").toString());
        double posY = Double.parseDouble(jsonObject.get("posY").toString());
        double velX = Double.parseDouble(jsonObject.get("velX").toString());
        double velY = Double.parseDouble(jsonObject.get("velY").toString());
        String img_path = jsonObject.get("img_path").toString();

        Vector2D positionVec = new Vector2D(posX, posY);
        Vector2D velocityVec = new Vector2D(velX, velY);
        // String name, boolean hasCrash, String type, String crashWithType
        CelestialObject theproduct = new CelestialObject(positionVec, velocityVec, planetMass, planetRadius, img_path, isAffectedByGravity, planetName,type);
        return  theproduct;
    }

    //private static
}

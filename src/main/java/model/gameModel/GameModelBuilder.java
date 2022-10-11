package model.gameModel;

import javafx.scene.shape.Circle;
import model.modelObjects.CelestialObject;
import model.modelObjects.JavaFXGeometry;
import utilitys.Vector2D;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.json.*;


public class GameModelBuilder {

    public static GameModel getGameModel(String pathToJsonLevel) throws FileNotFoundException {


        String jsonPath = "src/main/resources/json/levels/level3.json";
        InputStream levelTest = new FileInputStream(pathToJsonLevel);
        JsonReader reader = Json.createReader(levelTest);
        JsonObject levelObject = reader.readObject();
        reader.close();

        ArrayList<CelestialObject> planets = getCelestialObjetsFromJson(levelObject, "planets", "planet");
        ArrayList<CelestialObject> targets = getCelestialObjetsFromJson(levelObject, "target", "target");
        ArrayList<CelestialObject> players = getCelestialObjetsFromJson(levelObject, "playerObject", "player");

        GameModel product = new GameModel();
        product.setPlanets(planets.toArray(new CelestialObject[0]));
        product.setTargets(targets.toArray(new CelestialObject[0]));
        product.setPlayers(players.toArray(new CelestialObject[0]));

        return product;
    }

    private static ArrayList<CelestialObject> getCelestialObjetsFromJson(JsonObject levelObject, String jsonFilter, String type){
        ArrayList<CelestialObject> planets = new ArrayList<>();
        JsonArray planetArray = levelObject.getJsonArray(jsonFilter);
        for (int i = 0; i < planetArray.size(); i++) {
            JsonObject planetObject = planetArray.getJsonObject(i);
            planets.add(createCelestialObjects(planetObject, type));
        }
        return planets;
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
        String img_path = jsonObject.get("img_path").toString().replace("\"", "");

        Vector2D positionVec = new Vector2D(posX, posY);
        Vector2D velocityVec = new Vector2D(velX, velY);
        // String name, boolean hasCrash, String type, String crashWithType

        Circle circle = new Circle();
        circle.setRadius(planetRadius);

        CelestialObject theproduct = new CelestialObject(positionVec, velocityVec, planetMass, new JavaFXGeometry(circle), img_path, isAffectedByGravity, planetName,type);
        return  theproduct;
    }

    //private static
}

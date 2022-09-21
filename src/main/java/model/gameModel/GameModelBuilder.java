package model.gameModel;

import model.collisionModel.CollisionModel;
import model.collisionModel.Collisionable;
import model.gravitationModel.GravitationModel;
import model.gravitationModel.ObjectForGravitationModel;
import model.modelObjects.CelestialObject;
import utilitys.Vector2D;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.json.*;


class GameModelBuilder {

    GameModel theProduct = new GameModel();

    ArrayList<CelestialObjectBuilder> planets = new ArrayList<>();
    ArrayList<PlayerModel> players = new ArrayList<>();                         //Anteckningar för Olof och endast Olof dessa tre listor behöver man slå ihop till en lista av normalCelestialObjects
    ArrayList<TargetModel> targets = new ArrayList<>();                         // All data ska in som parametrar new CelestialObject()

    public void buildLevel()  throws Exception {

        JsonObject levelObject = getJsonObject();
        CelestialObjectBuilder.createPlanet(levelObject,planets);
        PlayerModel.createPlayerObject(levelObject, players);
        TargetModel.createTarget(levelObject, targets);
    }

    public GameModel getProduct(){
        return null;
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
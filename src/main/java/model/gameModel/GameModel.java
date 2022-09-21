package model.gameModel;

import model.collisionModel.CollisionModel;
import model.modelObjects.CelestialObject;
import model.gravitationModel.GravitationModel;

import java.util.ArrayList;

public class GameModel {

    GravitationModel gravitationModel;
    CollisionModel collisionModel;

    CelestialObject[] celestialObjects;
    CelestialObject[] targets;

    ArrayList<Observer> observers = new ArrayList<>();

    //planets(new Vector2D(0, 69.8 *Math.pow(10,6) * Math.pow(10,3)), new Vector2D(38.86 *Math.pow(10,3), 0), 0.330 * Math.pow(10,24), 2),

    //package private
    GameModel() {
    }

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    private void notifyObservers(){
        for (Observer o : observers){
            o.commandFromModel();
        }
    }

    ///package private
    void setGravitationModel(GravitationModel gravitationModel) {
        this.gravitationModel = gravitationModel;
    }

    //package private
    void setCollisionModel(CollisionModel collisionModel){
        this.collisionModel = collisionModel;
    }

    //package private
    void setTargets(CelestialObject[] targets){
        this.targets = targets;
    }

    //package private
    void setCelestialObjects(CelestialObject[] celestialObjects){
        this.celestialObjects = celestialObjects;
    }
}

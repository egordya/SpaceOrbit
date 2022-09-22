package model.gameModel;

import model.collisionModel.CollisionModel;
import model.modelObjects.CelestialObject;
import model.gravitationModel.GravitationModel;
import utilitys.Vector2D;

import java.util.ArrayList;
import java.util.Timer;


public class GameModel {

    GravitationModel gravitationModel;
    CollisionModel collisionModel;

    CelestialObject[] planets;
    CelestialObject[] targets;
    CelestialObject[] players;
    CelestialObject[] allCelestialObjects;

    ArrayList<Observer> observers = new ArrayList<>();

    Timer timer;



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





    //method
    //    start
    //    setplayerVelociraptor
    //    getDrawble
    //      planets
    //      targets
    //      players
    //    gameEnded
    //    gamePause




    //TODO
    // - Rest of the code goes here
    // - Timer
    // - Regler:
        // TODO - Man vinner när player krockar med target
        //  - man förlorar om player kommer 1000 pixlar utanför skärmen
        //  - man förlorar om player krockar i planet
        //      Om man förlorar startas leveln om.
    //(scoringsystem) tid och stjärnor


    public void startGame(){

    }

    public void pause(){

    }

    public void endGame(){

    }

    public void setPlayerVelocity(Vector2D[] velocitys){
        for(int i = 0; i<players.length; i++){
            this.players[i].setVelocityVector(velocitys[i]);
        }
        //fixa för fler spelare senare
    }

    public CelestialObject[] getPlanets(){
        return planets;
    }

    public CelestialObject[] getPlayers(){
        return players;
    }

    public CelestialObject[] getTargets(){
        return targets;
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
    void setPlanets(CelestialObject[] planets){
        this.planets = planets;
    }

    void setPlayers(CelestialObject[] players){
        this.players = players;
    }

    void setAllCelestialObjects(CelestialObject[] celestialObjects){
        this.allCelestialObjects = celestialObjects;
    }

}

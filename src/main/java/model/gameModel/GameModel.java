package model.gameModel;

import model.modelObjects.CelestialObject;
import utilitys.Collision_2_Tuple;

import java.util.*;


public class GameModel extends AGameModel{

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



    //planets(new Vector2D(0, 69.8 *Math.pow(10,6) * Math.pow(10,3)), new Vector2D(38.86 *Math.pow(10,3), 0), 0.330 * Math.pow(10,24), 2),

    //package private
    GameModel() {}

    /**
     * @return an array containing all CelestialObjects
     */
    private CelestialObject[] getAllCelestialObjects(){
        ArrayList<CelestialObject> allCelestialObjects = new ArrayList<>();
        allCelestialObjects.addAll(Arrays.stream(planets).toList());
        allCelestialObjects.addAll(Arrays.stream(targets).toList());
        allCelestialObjects.addAll(Arrays.stream(players).toList());
        return allCelestialObjects.toArray(new CelestialObject[0]);
    }

    /**
     * Makes one gamestep (one tick)
     * @param time the amount of time that will be used in calculations
     */
    @Override
    void gameStep(double time){
        simulationStep(time);
        handleCollisions();
        handleWin();
    }

    /**
     * Checks when the user has one and notifies the observers
     */
    private void handleWin() {
        if (players.length == 0){
            notifyObservers(ObserverCommand.Win);
        }
    }

    /**
     * makes all the necessary calculations regarding gravitation of one single tick
     * @param time the amount of time all forces affects each other in its current state
     */
    private void simulationStep(double time){
        gravitationModel.doSimulationStep(getAllCelestialObjects(), time);
    }

    /**
     * handles all collisions
     */
    private void handleCollisions() {

        Collision_2_Tuple[] collided = collisionModel.checkForCollisions(getAllCelestialObjects());

        for(Collision_2_Tuple x : collided){
            handlePlayerTargetCollision(x);
        }
    }

    /**
     * checks if player has collided with a target or a planet
     * restarts the game if player has collided with planet, otherwise it updates the game
     * @param x a tuple containing 2 objects that have collided
     */
    private void handlePlayerTargetCollision(Collision_2_Tuple x){
        if (x.getFirstCollided().getType().equals("player") && x.getSecondCollided().getType().equals("target")) {
            List<CelestialObject> players = new ArrayList<>(Arrays.stream(this.players).toList());
            players.remove(players.get(players.indexOf(x.getFirstCollided())));
            this.players = players.toArray(new CelestialObject[0]);
            notifyObservers(ObserverCommand.Update);
        }
        else if(x.getFirstCollided().getType().equals("player") && x.getSecondCollided().getType().equals("planet")){
            notifyObservers(ObserverCommand.Restart);
        }
    }



}

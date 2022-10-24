package model.gameModel;

import model.collisionModels.Collisionable;
import model.modelObjects.CelestialObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GameModel extends AGameModel{

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
    public void gameStep(double time){
        simulationStep(time);
        handleCollisions();
        checkWin();
        notifyObservers(ObserverCommand.Update);
    }

    /**
     * Checks when the user has one and notifies the observers
     */
    private void checkWin() {
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

        collisionModel.checkCollisions(getAllCelestialObjects());
        Collisionable[][] pairs = collisionModel.getCollidedPairs();

        for(Collisionable[] x : pairs){
            handlePlayerTargetCollision(x);
        }
    }

    /**
     * checks if player has collided with a target or a planet
     * restarts the game if player has collided with planet, otherwise it updates the game
     * @param TwoCollidedObjects a tuple containing 2 objects that have collided
     */
    private void handlePlayerTargetCollision(Collisionable[] TwoCollidedObjects){
        if (TwoCollidedObjects[0].getType().equals("player") && TwoCollidedObjects[1].getType().equals("target")) {
            List<CelestialObject> players = new ArrayList<>(Arrays.stream(this.players).toList());
            players.remove(players.get(players.indexOf(TwoCollidedObjects[0])));
            this.players = players.toArray(new CelestialObject[0]);
        }
        else if(TwoCollidedObjects[0].getType().equals("player") && TwoCollidedObjects[1].getType().equals("planet")){
            notifyObservers(ObserverCommand.Restart);
        }
    }



}

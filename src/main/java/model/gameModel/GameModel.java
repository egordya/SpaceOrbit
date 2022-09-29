package model.gameModel;

import com.grupp7.spaceorbit.controllers.Drawable;
import model.collisionModel.CollisionModel;
import model.collisionModel.Collisionable;
import model.modelObjects.CelestialObject;
import model.gravitationModel.GravitationModel;
import utilitys.Vector2D;

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

    private CelestialObject[] getAllCelestialObjects(){
        ArrayList<CelestialObject> allCelestialObjects = new ArrayList<>();
        allCelestialObjects.addAll(Arrays.stream(planets).toList());
        allCelestialObjects.addAll(Arrays.stream(targets).toList());
        allCelestialObjects.addAll(Arrays.stream(players).toList());
        return allCelestialObjects.toArray(new CelestialObject[0]);
    }

    @Override
    void gameStep(double time){
        simulationStep(time);
        handleCollisions();
        handleWinLose();
    }

    private void handleWinLose() {
        if (players.length == 0){
            notifyObservers(ObserverCommand.Win);
        }
    }

    private void simulationStep(double time){
        gravitationModel.updateGravitationModelObjects(getAllCelestialObjects());
        gravitationModel.doSimulationStep(time);
    }

    private void handleCollisions() {

        Collisionable[][] collided = collisionModel.checkForCollisions(getAllCelestialObjects());
        for(Collisionable[] x : collided){
            handlePlayerTargetCollision(x);
        }
    }

    private void handlePlayerTargetCollision(Collisionable[] x){
        if (x[0].getType().equals("player") && x[1].getType().equals("target")) {
            List<CelestialObject> players = new ArrayList<>(Arrays.stream(this.players).toList());
            players.remove(players.get(players.indexOf(x[0])));
            this.players = players.toArray(new CelestialObject[0]);
            notifyObservers(ObserverCommand.Update);
        }
    }



}

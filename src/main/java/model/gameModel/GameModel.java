package model.gameModel;

import model.collisionModels.Collisionable;
import model.modelObjects.CelestialObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GameModel extends AGameModel{

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
    public void gameStep(double time){
        simulationStep(time);
        handleCollisions();
        checkWin();
        notifyObservers(ObserverCommand.Update);
    }

    private void checkWin() {
        if (players.length == 0){
            notifyObservers(ObserverCommand.Win);
        }
    }

    private void simulationStep(double time){
        gravitationModel.doSimulationStep(getAllCelestialObjects(), time);
    }

    private void handleCollisions() {

        collisionModel.checkCollisions(getAllCelestialObjects());
        Collisionable[][] pairs = collisionModel.getCollidedPairs();

        for(Collisionable[] x : pairs){
            handlePlayerTargetCollision(x);
        }
    }

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

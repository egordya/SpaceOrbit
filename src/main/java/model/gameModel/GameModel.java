package model.gameModel;

import com.grupp7.spaceorbit.controllers.Drawable;
import model.collisionModel.CollisionModel;
import model.collisionModel.Collisionable;
import model.modelObjects.CelestialObject;
import model.gravitationModel.GravitationModel;
import utilitys.Vector2D;

import java.util.*;


public class GameModel{

    GravitationModel gravitationModel;
    CollisionModel collisionModel = new CollisionModel();
    CelestialObject[] allCelestialObjects;
    CelestialObject[] players;
    CelestialObject[] targets;
    CelestialObject[] planets;
    ArrayList<Observer> observers = new ArrayList<>();
    Timer tr;
    final long timePeriod = 20;

    boolean isRunning = false;

    //planets(new Vector2D(0, 69.8 *Math.pow(10,6) * Math.pow(10,3)), new Vector2D(38.86 *Math.pow(10,3), 0), 0.330 * Math.pow(10,24), 2),

    //package private
    GameModel() {

    }

    private void notifyObservers(ObserverCommand command){
        for (Observer o : observers){
            o.commandFromModel(command);
        }
    }

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    private TimerTask getTimerTask(){
        return new TimerTask(){
            @Override
            public void run(){
                gameStep(timePeriod * Math.pow(10, -3));
            }
        };
    }

    public void startGame(){
        if (!isRunning) {
            this.tr = new Timer();
            this.tr.schedule(getTimerTask(), 0, timePeriod);
            isRunning = true;
        }
    }

    public void pauseGame(){
        if(isRunning) {
            tr.cancel();
            isRunning = false;
        }
    }

    public void setPlayerVelocity(Vector2D[] velocitys){
        if(!isRunning) {
            for (int i = 0; i < players.length; i++) {
                this.players[i].setVelocityVector(velocitys[i]);
            }
        }
        //fixa för fler spelare senare
    }

    public Drawable[] getAllDrawableObjects(){
        return allCelestialObjects;
    }

    public Drawable[] getPlayers() {
        return players;
    }

    public Drawable[] getTargets() {
        return targets;
    }

    public Drawable[] getPlanets() {
        return planets;
    }

    ///package private
    void setGravitationModel(GravitationModel gravitationModel) {
        this.gravitationModel = gravitationModel;
    }


    //package private
    void setAllCelestialObjects(CelestialObject[] celestialObjects){
        this.allCelestialObjects = celestialObjects;
    }

    void setPlayers(CelestialObject[] players) {
        this.players = players;
    }

    void setTargets(CelestialObject[] targets) {
        this.targets = targets;
    }

    void setPlanets(CelestialObject[] planets) {
        this.planets = planets;
    }

    private void gameStep(double time){
        simulationStep(time);
        handleCollisions();
    }


    private void handleCollisions() {
        Collisionable[][] collided = collisionModel.checkForCollisions(allCelestialObjects);
        for(Collisionable[] x : collided){
            if (x[0].getType().equals("player") && x[1].getType().equals("target")) {
                notifyObservers(ObserverCommand.Win);
//                List<CelestialObject> lista =  Arrays.stream(players).toList();
//                lista.get(lista.indexOf(x[0])).setVelocityVector(new Vector2D(0,0));
//                lista.get(lista.indexOf(x[0])).setAffectedByGravity(false);
//
//                lista.get(lista.indexOf(x[1])).setVelocityVector(new Vector2D(0,0));
//                lista.get(lista.indexOf(x[1])).setAffectedByGravity(false);
            }
        }
    }

    private void simulationStep(double time){
        gravitationModel.doSimulationStep(time);
    }


}

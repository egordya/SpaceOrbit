package model.gameModel;

import com.grupp7.spaceorbit.controllers.Drawable;
import model.collisionModel.CollisionModel;
import model.gravitationModel.GravitationModel;
import model.modelObjects.CelestialObject;
import utilitys.Vector2D;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public abstract class AGameModel {
    GravitationModel gravitationModel = new GravitationModel();
    CollisionModel collisionModel = new CollisionModel();
    CelestialObject[] players;
    CelestialObject[] targets;
    CelestialObject[] planets;
    ArrayList<Observer> observers = new ArrayList<>();
    Timer tr;
    final long timePeriod = 20;

    boolean isRunning = false;

    public void addObserver(Observer observer){
        observers.add(observer);
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

    public Drawable[] getPlayers() {
        return players;
    }

    public Drawable[] getTargets() {
        return targets;
    }

    public Drawable[] getPlanets() {
        return planets;
    }

    //package private
    void setPlayers(CelestialObject[] players) {
        this.players = players;
    }

    void setTargets(CelestialObject[] targets) {
        this.targets = targets;
    }

    void setPlanets(CelestialObject[] planets) {
        this.planets = planets;
    }

    private TimerTask getTimerTask(){
        return new TimerTask(){
            @Override
            public void run(){
                gameStep(timePeriod * Math.pow(10, -3));
            }
        };
    }

    void notifyObservers(ObserverCommand command){
        for (Observer o : observers){
            o.commandFromModel(command);
        }
    }

    abstract void gameStep(double time);


}

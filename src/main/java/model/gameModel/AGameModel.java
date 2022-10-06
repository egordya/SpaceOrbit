package model.gameModel;

import com.grupp7.spaceorbit.controllers.Drawable;
import model.collisionModel.CollisionModel;
import model.gravitationModel.GravitationModel;
import model.modelObjects.ArrowObject;
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

    ArrowObject[] playerArrows = new ArrowObject[0];
    ArrayList<Observer> observers = new ArrayList<>();
    Timer tr;
    final long timePeriod = 10;

    boolean showArrows = true;
    boolean isRunning = false;
    boolean isPaused = false;

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void startGame(){
        if (!isRunning && !isPaused) {
            this.tr = new Timer();
            this.tr.schedule(getTimerTask(), 0, timePeriod);
            isRunning = true;
        }
    }

    public void togglePauseGame(){
        if(isRunning && !isPaused) {
            tr.cancel();
            isRunning = false;
            isPaused = true;
        }
        else if(!isRunning && isPaused) {
            isPaused = false;
            startGame();
        }
    }

    public void setPlayerVelocity(Vector2D[] velocitys){
        if(!isRunning && !isPaused) {
            for (int i = 0; i < players.length; i++) {
                this.players[i].setVelocityVector(velocitys[i]);
            }
        }
        //fixa för fler spelare senare
    }

    public void setPlayersArrow(Vector2D delta){
        ArrayList<ArrowObject> arrows = new ArrayList<>();
        for (int i = 0; i<players.length; i++){
            arrows.add(new ArrowObject(players[i].getPos(), players[i].getPos().add(delta)));
        }
        playerArrows = arrows.toArray(new ArrowObject[0]);
        notifyObservers(ObserverCommand.Update);
    }

    public void SetShowPlayerArrows(boolean show){
        showArrows = show;
        notifyObservers(ObserverCommand.Update);
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

    public Drawable[] getPlayerArrows(){
        return playerArrows;
    }

    public boolean getShowPlayerArrows(){
        return showArrows;
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

    void notifyObservers(ObserverCommand command){
        for (Observer o : observers){
            o.commandFromModel(command);
        }
    }

    private TimerTask getTimerTask(){
        return new TimerTask(){
            @Override
            public void run(){
                gameStep(timePeriod * Math.pow(10, -3));
            }
        };
    }

    abstract void gameStep(double time);


}

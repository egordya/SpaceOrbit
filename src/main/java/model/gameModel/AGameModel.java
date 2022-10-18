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

    /**
     * Adds an Observer in the ObserverList
     * @param observer the Observer that will be added
     */
    public void addObserver(Observer observer){
        observers.add(observer);
    }

    /**
     * Starts the game
     */
    public void startGame(){
        if (!isRunning && !isPaused) {
            this.tr = new Timer();
            this.tr.schedule(getTimerTask(), 0, timePeriod);
            isRunning = true;
        }
    }

    /**
     * Pauses the game if not already paused
     */
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

    /**
     * Sets all players velocity
     * @param velocitys List of all playerVelocitys
     */
    public void setPlayerVelocity(Vector2D[] velocitys){
        if(!isRunning && !isPaused) {
            for (int i = 0; i < players.length; i++) {
                this.players[i].setVelocityVector(velocitys[i]);
            }
        }
    }

    /**
     * sets the arrow that indicates speed and direction for one player
     * @param delta Input from the user in the form of a 2D vector
     */
    public void setPlayersArrow(Vector2D delta){
        ArrayList<ArrowObject> arrows = new ArrayList<>();
        for (int i = 0; i<players.length; i++){
            arrows.add(new ArrowObject(players[i].getPos(), players[i].getPos().add(delta)));
        }
        playerArrows = arrows.toArray(new ArrowObject[0]);
        notifyObservers(ObserverCommand.Update);
    }

    /**
     * Updates boolean show
     * @param show boolean. Arrows will be displayed if true
     */
    public void SetShowPlayerArrows(boolean show){
        showArrows = show;
        notifyObservers(ObserverCommand.Update);
    }

    /**
     * @return players in a Drawable array
     */
    public Drawable[] getPlayers() {
        return players;
    }

    /**
     * @return targets in a Drawable array
     */
    public Drawable[] getTargets() {
        return targets;
    }

    /**
     * @return planets in a Drawable array
     */
    public Drawable[] getPlanets() {
        return planets;
    }

    /**
     * @return playerArrows in a Drawable array
     */
    public Drawable[] getPlayerArrows(){
        return playerArrows;
    }

    /**
     * @return boolean showArrows.
     */
    public boolean getShowPlayerArrows(){
        return showArrows;
    }

    /**
     * sets the array of players
     * @param players array of players
     */
    //package private
    void setPlayers(CelestialObject[] players) {
        this.players = players;
    }

    /**
     * sets the array of targets
     * @param targets array of targets
     */
    void setTargets(CelestialObject[] targets) {
        this.targets = targets;
    }

    /**
     * sets the array of planets
     * @param planets array of planets
     */
    void setPlanets(CelestialObject[] planets) {
        this.planets = planets;
    }

    /**
     * notifys the observers with a command
     * @param command command to the model
     */
    void notifyObservers(ObserverCommand command){
        for (Observer o : observers){
            o.commandFromModel(command);
        }
    }

    /**
     * @return a TimerTask that executes the gameStep method in a seperate thread
     */
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

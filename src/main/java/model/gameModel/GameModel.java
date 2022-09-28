package model.gameModel;

import com.grupp7.spaceorbit.controllers.Drawable;
import model.collisionModel.CollisionModel;
import model.modelObjects.CelestialObject;
import model.gravitationModel.GravitationModel;
import utilitys.Vector2D;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class GameModel{

    GravitationModel gravitationModel;
    CollisionModel collisionModel;

    CelestialObject[] planets;
    CelestialObject[] targets;
    CelestialObject[] players;
    CelestialObject[] allCelestialObjects;

    ArrayList<Observer> observers = new ArrayList<>();

    private Thread thread;           // Timer variable
    private boolean running = false; // Timer variable

    boolean gameLoopLock = false;


    Timer tr;

    final long timePeriod = 20;


    //planets(new Vector2D(0, 69.8 *Math.pow(10,6) * Math.pow(10,3)), new Vector2D(38.86 *Math.pow(10,3), 0), 0.330 * Math.pow(10,24), 2),

    //package private
    GameModel() {

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
        this.tr = new Timer();
        this.tr.schedule(getTimerTask(), 0, timePeriod);
    }

    public void pause(){
        tr.cancel();
    }

    public void endGame(){
        tr.cancel();
        // notify controller that its game over or win

    }

    public void setPlayerVelocity(Vector2D[] velocitys){
        for(int i = 0; i<players.length; i++){
            this.players[i].setVelocityVector(velocitys[i]);
        }
        //fixa för fler spelare senare
    }

    public void init(){
        collisionModel.checkForCollisions();
    }

    public Drawable[] getPlanets(){
        return planets;
    }

    public Drawable[] getPlayers(){
        return players;
    }

    public Drawable[] getTargets(){
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
    //package private
    void setPlayers(CelestialObject[] players){
        this.players = players;
    }
    //package private
    void setAllCelestialObjects(CelestialObject[] celestialObjects){
        this.allCelestialObjects = celestialObjects;
    }

    private void gameStep(double time){
        //simulationStep(time);
        //handleCollisions();
    }


    private void handleCollisions() {
        for (CelestialObject x : players){
            if(x.getHasCrashed() && x.getCrashWithType().equals("target")){
                System.out.println("PLAYER HIT TARGET");
            }
        }
    }

    private void simulationStep(double time){
        gravitationModel.doSimulationStep(0.1);
        collisionModel.checkForCollisions();
    }

}

package model.gameModel;

import com.grupp7.spaceorbit.views.Drawable;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import model.collisionModels.CollisionModel;
import model.gravitationModel.GravitationModel;
import model.modelObjects.ArrowObject;
import model.modelObjects.CelestialObject;
import model.modelObjects.Geometry;
import utilitys.Vector2D;

import java.util.ArrayList;

public abstract class AGameModel {

    GravitationModel gravitationModel = new GravitationModel();

    CollisionModel collisionModel = new CollisionModel();

    CelestialObject[] players;
    CelestialObject[] targets;
    CelestialObject[] planets;

    ArrowObject[] playerArrows = new ArrowObject[0];
    ArrayList<Observer> observers = new ArrayList<>();


    boolean showArrows = true;

    /**
     * Adds an Observer in the ObserverList
     * @param observer the Observer that will be added
     */
    public void addObserver(Observer observer){
        observers.add(observer);
    }

    /**
     * Sets all players velocity
     * @param velocitys List of all playerVelocitys
     */
    public void setPlayerVelocity(Vector2D[] velocitys){
        for (int i = 0; i < players.length; i++) {
            this.players[i].setVelocityVector(velocitys[i]);
        }
    }

    /**
     * Sets the affectedByGravity variable
     * @param isAffectedByGravity boolean that describes if the object is affected by gravity or not
     */
    public void setPlayerAffectedByGravity(boolean[] isAffectedByGravity){
        for (int i = 0; i < players.length; i++) {
            this.players[i].setAffectedByGravity(isAffectedByGravity[i]);
        }
    }
    /**
     * sets the arrow that indicates speed and direction for one player
     * @param arrows list of arrows
     */
    public void setPlayersArrow(ArrowObject[] arrows){
        playerArrows = arrows;
    }

    /**
     * Updates boolean show
     * @param show boolean. Arrows will be displayed if true
     */
    public void SetShowPlayerArrows(boolean show){
        showArrows = show;
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


    abstract public void gameStep(double time);


}

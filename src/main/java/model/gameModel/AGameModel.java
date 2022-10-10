package model.gameModel;

import com.grupp7.spaceorbit.views.Drawable;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import model.collisionModel.CollisionModel;
import model.gravitationModel.GravitationModel;
import model.modelObjects.ArrowObject;
import model.modelObjects.CelestialObject;
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

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void setPlayerVelocity(Vector2D[] velocitys){
        for (int i = 0; i < players.length; i++) {
            this.players[i].setVelocityVector(velocitys[i]);
        }
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

    public Drawable<Circle>[] getPlayers() {
        return players;
    }

    public Drawable<Circle>[] getTargets() {
        return targets;
    }

    public Drawable<Circle>[] getPlanets() {
        return planets;
    }

    public Drawable<Polyline>[] getPlayerArrows(){
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


    abstract public void gameStep(double time);


}

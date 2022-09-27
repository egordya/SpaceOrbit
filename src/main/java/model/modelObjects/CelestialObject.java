package model.modelObjects;

import com.grupp7.spaceorbit.controllers.Drawable;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import model.collisionModel.Collisionable;
import model.gravitationModel.ObjectForGravitationModel;
import utilitys.Vector2D;



public class CelestialObject implements ObjectForGravitationModel, Collisionable, Drawable {

    private Vector2D position;
    private Vector2D velocityVector;
    private double mass;
    private Circle planetHitbox = new Circle(0,0,0);
    private double radius;
    private String imagePath;
    private boolean fixedPosition;
    private String name;

    private Boolean hasCrash;
    private String type;
    private String crashWithType;



    public CelestialObject(Vector2D position, Vector2D velocityVector, double mass, double radius, String imagePath,
                           boolean fixedPosition, String name, boolean hasCrash, String type, String crashWithType) {
        this.position = position;
        this.velocityVector = velocityVector;
        this.mass = mass;
        this.radius = radius;
        this.planetHitbox.setRadius(this.radius);
        this.imagePath = imagePath;
        this.fixedPosition = fixedPosition;
        this.name = name;
        this.hasCrash = hasCrash;
        this.type = type;
        this.crashWithType = crashWithType;
    }

    public boolean getHasCrashed(){
        return this.hasCrash;
    }

    public String getCrashWithType(){
        return this.crashWithType;
    }

    @Override
    public void moveStep(double time) {
        Vector2D positionStep = velocityVector.scalarMultiplication(time);
        position = position.add(positionStep);
    }

    @Override
    public Vector2D getPos() {
        return position;
    }

    @Override
    public Vector2D getVelocityVector() {
        return velocityVector;
    }


    @Override
    public double getMass() {
        return mass;
    }

    @Override
    public boolean getIsEffectedByGravity() {
        return true;
    }

    @Override
    public void setPos(Vector2D position) {
        this.position = position;
    }

    @Override
    public void setVelocityVector(Vector2D velocityVector) {
        this.velocityVector = velocityVector;
    }


    @Override
    public void setMass(double mass) {
        this.mass = mass;
    }

    @Override
    public CelestialObject clone() {
        return new CelestialObject(position, velocityVector, mass, radius, imagePath, fixedPosition, name, hasCrash, type, crashWithType);
    }

    @Override
    public void crash(String crashWithType) {
        this.hasCrash = true;
        this.crashWithType = crashWithType;
    }


    @Override
    public String getType(){
        return this.type;
    }

    public Circle getHitbox(){
        return this.planetHitbox;
    }


    public void setHitboxPos(){
        this.planetHitbox.setCenterX(this.getPos().getX());
        this.planetHitbox.setCenterY(this.getPos().getY());

    }

    @Override
    public double getXPos() {
        return position.getX();
    }

    @Override
    public double getYPos() {
        return position.getY();
    }

    @Override
    public Shape getShape() {
        return planetHitbox;
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public String getName() {
        return name;
    }
}

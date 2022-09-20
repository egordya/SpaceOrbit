package model;

import javafx.scene.shape.Circle;
import utilitys.Vector2D;



public class CelestialObject implements ObjectForGravitationModel,Collisionable {

    Vector2D position;
    Vector2D velocityVector;
    double mass;
    Circle planetHitbox = new Circle(0,0,0);
    double radius;

    public CelestialObject(Vector2D position, Vector2D velocityVector, double mass, double radius) {
        this.position = position;
        this.velocityVector = velocityVector;
        this.mass = mass;
        this.radius = radius;
        this.planetHitbox.setRadius(this.radius);
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
    public ObjectForGravitationModel clone() {
        return new CelestialObject(position, velocityVector, mass, radius);
    }

    public Circle getHitbox(){
        return this.planetHitbox;
    }

    public void crash(){
        System.out.println("Collision!");
        System.out.println(this);
    }

    public void setHitboxPos(){
        this.planetHitbox.setCenterX(this.getPos().getX());
        this.planetHitbox.setCenterY(this.getPos().getY());
    }
}

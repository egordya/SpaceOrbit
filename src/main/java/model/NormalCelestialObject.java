package model;

import utilitys.Vector2D;

public class NormalCelestialObject implements ObjectForGravitationModel {

    Vector2D position;
    Vector2D velocityVector;
    double speed;
    double mass;

    public NormalCelestialObject(Vector2D position, Vector2D velocityVector, double mass) {
        this.position = position;
        this.velocityVector = velocityVector;
        this.mass = mass;
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
        return new NormalCelestialObject(position, velocityVector, mass);
    }
}

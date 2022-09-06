package model;

import utilitys.Direction;
import utilitys.Position;

public class NormalCelestialObject implements GravitationModelObject {

    Position position;
    Direction direction;
    double speed;
    double mass;

    public NormalCelestialObject(Position position, Direction direction, double speed, double mass) {
        this.direction = direction;
        this.position = position;
        this.speed = speed;
        this.mass = mass;
    }

    @Override
    public Position getPos() {
        return position;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public Direction getDirection() {
        return direction;
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
    public void setPos(Position position) {
        this.position = position;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void setMass(double mass) {
        this.mass = mass;
    }
}

package model;

public interface ObjectForGravitationModel {
    Position getPos();
    double getSpeed();
    Direction getDirection();
    double getMass();

    boolean getIsEffectedByGravity();

    void setPos(Position position);
    void setSpeed(double speed);
    void setDirection(Direction direction);
    void setMass(double mass);
    ObjectForGravitationModel clone();

}

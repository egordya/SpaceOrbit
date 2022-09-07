package model;

import utilitys.Vector2D;

public interface ObjectForGravitationModel {
    Vector2D getPos();

    Vector2D getVelocityVector();
    double getMass();

    boolean getIsEffectedByGravity();

    void setPos(Vector2D position);
    void setVelocityVector(Vector2D direction);
    void setMass(double mass);
    ObjectForGravitationModel clone();

}

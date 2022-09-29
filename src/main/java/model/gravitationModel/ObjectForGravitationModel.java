package model.gravitationModel;

import utilitys.Vector2D;

public interface ObjectForGravitationModel {

    void moveStep(double time);
    Vector2D getPos();
    Vector2D getVelocityVector();

    double getMass();
    boolean getIsEffectedByGravity();
    void setPos(Vector2D position);
    void setVelocityVector(Vector2D direction);
    ObjectForGravitationModel clone();
}
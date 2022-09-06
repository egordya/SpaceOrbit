package model;

import javafx.geometry.Pos;
import utilitys.Direction;
import utilitys.Position;

public interface OrbitModelObject {
    Position getPos();
    double getSpeed();
    Direction getDirection();
    double getMass();
    void setPos(Position pos);
    void setSpeed(double speed);
    void setDirection(Direction angle);
    void setMass(double mass);
}

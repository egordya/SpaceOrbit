package model;

import javafx.scene.shape.Shape;

public interface Collisionable {

    Shape getHitbox();
    void crash();
    void setHitboxPos();
}

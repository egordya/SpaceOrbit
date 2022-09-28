package model.collisionModel;

import javafx.scene.shape.Shape;

public interface Collisionable {

    String getType();
    Shape getHitbox();
    Collisionable clone();
}

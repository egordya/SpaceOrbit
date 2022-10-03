package model.collisionModel;

import javafx.scene.shape.Shape;

public interface Collisionable {

    String getType();
    Shape getGeometry();
    Collisionable clone();
}

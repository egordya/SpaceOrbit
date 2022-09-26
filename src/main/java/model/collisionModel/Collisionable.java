package model.collisionModel;

import javafx.scene.shape.Shape;

public interface Collisionable {

    String getType();
    void crash(String typeThatHasBeenCollidedWith);
    Shape getHitbox();
    void setHitboxPos();
}

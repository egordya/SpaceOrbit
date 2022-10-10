package model.collisionModels;

import model.modelObjects.Geometry;

public interface Collisionable {
    Geometry getGeometry();
    String getType();
}

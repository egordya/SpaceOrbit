package com.grupp7.spaceorbit.views;

import javafx.scene.shape.Shape;
import model.modelObjects.Geometry;

public interface Drawable {
    double getXPos();
    double getYPos();
    Geometry getGeometry();
    String getImagePath();
    String getName();
}

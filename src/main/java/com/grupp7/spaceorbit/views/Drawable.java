package com.grupp7.spaceorbit.views;

import javafx.scene.shape.Shape;

public interface Drawable {
    double getXPos();
    double getYPos();
    Shape getGeometry();
    String getImagePath();
    String getName();
}
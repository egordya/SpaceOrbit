package com.grupp7.spaceorbit.views;

import javafx.scene.shape.Shape;

public interface Drawable<Type> {
    double getXPos();
    double getYPos();
    Type getGeometry();
    String getImagePath();
    String getName();
}

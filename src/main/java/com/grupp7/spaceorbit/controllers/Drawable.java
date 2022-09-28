package com.grupp7.spaceorbit.controllers;

import javafx.scene.shape.Shape;

public interface Drawable {
    double getXPos();
    double getYPos();
    Shape getShape();
    String getImagePath();
    String getName();
    String getType();
}

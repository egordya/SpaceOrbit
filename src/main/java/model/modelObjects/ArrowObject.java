package model.modelObjects;

import com.grupp7.spaceorbit.views.Drawable;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
import utilitys.Vector2D;

public class ArrowObject implements Drawable {

    Geometry geometry;
    double xPos;
    double yPos;

    public ArrowObject(Geometry geometry) {
        this.geometry = geometry;
    }

    @Override
    public double getXPos() {
        return xPos;
    }

    @Override
    public double getYPos() {
        return yPos;
    }

    @Override
    public Geometry getGeometry() {
        return geometry;
    }

    @Override
    public String getImagePath() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

}

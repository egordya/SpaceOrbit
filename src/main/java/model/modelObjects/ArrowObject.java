package model.modelObjects;

import com.grupp7.spaceorbit.views.Drawable;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
import utilitys.Vector2D;

public class ArrowObject implements Drawable {

    Geometry geometry;
    double xPos;
    double yPos;

    /**
     * Constructs an arrow that visualises the input from the user
     */
    public ArrowObject(Geometry geometry) {
        this.geometry = geometry;
    }

    /**
     * retrieves the X coordinate
     * @return the X coordinate
     */
    @Override
    public double getXPos() {
        return xPos;
    }

    /**
     * retrieves the Y coordinate
     * @return the Y coordinate
     */
    @Override
    public double getYPos() {
        return yPos;
    }

    /**
     * retrieves the geometry of the arrow
     * @return the geometry of the arrow
     */
    @Override
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * retrieves the path to the image for the arrow
     * @return the path to the image for the arrow
     */
    @Override
    public String getImagePath() {
        return null;
    }

    /**
     * retrieves the name af the arrow
     * @return the name of the arrow
     */
    @Override
    public String getName() {
        return null;
    }

}

package model.modelObjects;

import javafx.application.Platform;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class JavaFXGeometry implements Geometry<Shape>{

    private Shape geometry;

    /**
     * Constructor for a JavaFXGeometry
     * @param geometry takes in the geometry of a JavaFX Shape
     */
    public JavaFXGeometry(Shape geometry) {
        this.geometry = geometry;
    }

    /**
     * @return returns the geometry of the Shape class.
     */
    @Override
    public Shape getGeometry() {
        return this.geometry;
    }


    /**
     * Checks if this shape overlaps with a given shape.
     * @param geometry The given shape.
     * @return True if overlaps, False if no overlap.
     */
    @Override
    public boolean intersects(Shape geometry) {
        return this.geometry.getBoundsInParent().intersects(geometry.getBoundsInParent());
    }

    /**
     * Changes the position of the geometry
     * @param x takes in the X-Coordinate
     * @param y takes in the Y-Coordinate
     */
    @Override
    public void relocate(double x, double y) {
        geometry.relocate(x - (geometry.getBoundsInParent().getMaxX() - geometry.getBoundsInParent().getMinX()) / 2, y - (geometry.getBoundsInParent().getMaxY() - geometry.getBoundsInParent().getMinY()) / 2);
    }
}

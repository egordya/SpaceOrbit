package model.modelObjects;

import javafx.application.Platform;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class JavaFXGeometry implements Geometry<Shape>{

    private Shape geometry;

    public JavaFXGeometry(Shape geometry) {
        this.geometry = geometry;
    }

    @Override
    public Shape getGeometry() {
        return this.geometry;
    }

    @Override
    public boolean intersects(Shape geometry) {
        return this.geometry.getBoundsInParent().intersects(geometry.getBoundsInParent());
    }

    @Override
    public void relocate(double x, double y) {
        geometry.relocate(x - (geometry.getBoundsInParent().getMaxX() - geometry.getBoundsInParent().getMinX()) / 2, y - (geometry.getBoundsInParent().getMaxY() - geometry.getBoundsInParent().getMinY()) / 2);
    }
}

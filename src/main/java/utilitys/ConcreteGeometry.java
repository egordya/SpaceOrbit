package utilitys;

import javafx.scene.shape.Shape;

public class ConcreteGeometry implements Geometry<Shape>{
    @Override
    public Shape getGeometry() {
        return null;
    }

    @Override
    public boolean intersects(Geometry a) {
        return false;
    }
}

package model.modelObjects;

public interface Geometry<GeometryType>{
    GeometryType getGeometry();
    boolean intersects(GeometryType geometry);
    void relocate(double x, double y);
}

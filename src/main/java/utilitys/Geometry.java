package utilitys;

public interface Geometry <UIShape>{

    UIShape getGeometry();
    boolean intersects(Geometry geometry);

}

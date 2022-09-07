package utilitys;

public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public  Vector2D scalarMultiplication(double scalar){
        return null;
    }

    public Vector2D add(Vector2D otherVector){
        return new Vector2D(this.x + otherVector.getX(), this.y + otherVector.getY());
    }

    public Vector2D getUnitVector(){
        return null;
    }

}

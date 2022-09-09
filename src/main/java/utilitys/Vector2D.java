package utilitys;

import java.util.ArrayList;

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
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    public Vector2D add(Vector2D otherVector){
        return new Vector2D(this.x + otherVector.getX(), this.y + otherVector.getY());
    }

    public Vector2D sub(Vector2D otherVector){
        return new Vector2D(this.x - otherVector.getX(), this.y - otherVector.getY());
    }

    public Vector2D getUnitVector(){
        return scalarMultiplication(1/ getVectorLength());
    }

    public double getVectorLength(){
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y,2));
    }

    @Override
    public String toString() {
        return "("+x+", "+y+")";
    }

    public static ArrayList<Vector2D> getUnitVectorsOfVectors(ArrayList<Vector2D> vector2DS) {
        ArrayList<Vector2D> unitVectors = new ArrayList<>();
        for(Vector2D x : vector2DS){
            unitVectors.add(x.getUnitVector());
        }

        return unitVectors;

    }

    public static ArrayList<Double> getLengthOfVectors(ArrayList<Vector2D> vector2DS) {

        ArrayList<Double> lengthOfVectors = new ArrayList<>();
        for (Vector2D x : vector2DS){
            lengthOfVectors.add(x.getVectorLength());
        }

        return lengthOfVectors;
    }

}

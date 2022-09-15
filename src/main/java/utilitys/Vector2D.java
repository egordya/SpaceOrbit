package utilitys;

import java.util.ArrayList;

/**
 * The immutable Vector2D class is a representation of a 2d vector
 * It contains methods for executing simple vector operations, such as vector addition and subtraction.
 */
final public class Vector2D {
    private double x;
    private double y;

    /**
     * Will create a new Vector2D object
     * @param x the first element value of this Vector2D object
     * @param y the second element value of this Vector2D object
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * the getX method will retrieve the first element value of this Vector2D object
     * @return the double value of the first element.
     */
    public double getX() {
        return x;
    }

    /**
     * the getY method will retrieve the second element value of this Vector2D object
     * @return the double value of the second element.
     */
    public double getY() {
        return y;
    }

    /**
     * The scalarMultiplication method will multiple this vector by a scalar value
     * @param scalar the scalar value to multiply this Vector2D with.
     * @return a new Vector2D object that is the product of the scalarMultiplication with this Vector2D object.
     */
    public  Vector2D scalarMultiplication(double scalar){
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    /**
     * The add method will sum up this Vector2D object with another.
     * @param otherVector the Vector2D object to be added to this Vector2D object
     * @return a new Vector2D object that is the sum of this Vector2D object with another
     */
    public Vector2D add(Vector2D otherVector){
        return new Vector2D(this.x + otherVector.getX(), this.y + otherVector.getY());
    }

    /**
     * The sub method will subtract this Vector2D object with another.
     * @param otherVector the Vector2D object to be subtracted from this Vector2D object
     * @return a new Vector2D object that is the subtraction of this Vector2D object with another
     */
    public Vector2D sub(Vector2D otherVector){
        return new Vector2D(this.x - otherVector.getX(), this.y - otherVector.getY());
    }

    /**
     * the getUnitVector method will calculate the UnitVector of this Vector2D object
     * @return a new Vector2D object that is the UnitVector of this Vector2D object
     */
    public Vector2D getUnitVector(){
        return scalarMultiplication(1/ getVectorLength());
    }

    /**
     * the getVectorLength method will calculate the length of this Vector2D object
     * @return the double value of the length
     */
    public double getVectorLength(){
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y,2));
    }

    /**
     * the toString method will retrieve the string representation of this Vector2D object
     * @return the string value
     */
    @Override
    public String toString() {
        return "("+x+", "+y+")";
    }

    /**
     * the getUnitVectorsOfVectors weill calculate all the unitVectors of a Vector2D list
     * @param vector2DS the Vector2D list
     * @return new Vector2D list of all the unitVectors
     */
    public static ArrayList<Vector2D> getUnitVectorsOfVectors(ArrayList<Vector2D> vector2DS) {
        ArrayList<Vector2D> unitVectors = new ArrayList<>();
        for(Vector2D x : vector2DS){
            unitVectors.add(x.getUnitVector());
        }

        return unitVectors;

    }

    /**
     * the getLengthOfVectors will calculate the lengths of a Vector2D list
     * @param vector2DS the Vector2D list
     * @return new Double list of all the lengths
     */
    public static ArrayList<Double> getLengthOfVectors(ArrayList<Vector2D> vector2DS) {

        ArrayList<Double> lengthOfVectors = new ArrayList<>();
        for (Vector2D x : vector2DS){
            lengthOfVectors.add(x.getVectorLength());
        }

        return lengthOfVectors;
    }

}

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
        return new Vector2D(this.getX()*scalar,this.getY()*scalar);
    }

    public Vector2D add(Vector2D otherVector){  //Denna funkar inte
        return new Vector2D(this.x + otherVector.getX(), this.y + otherVector.getY());
    }

    public Vector2D subtract(Vector2D otherVector){
        return new Vector2D(this.x - otherVector.getX(), this.y - otherVector.getY());
    }

    public Vector2D scalarDivision(double scalar){
        return new Vector2D(this.getX()/scalar,this.getY()/scalar);
    }

    public Vector2D getUnitVector(){
        return null;
    }

    public double getVectorSize(){
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y,2));
    }

}

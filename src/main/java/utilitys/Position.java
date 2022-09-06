package utilitys;

public class Position {
    double x;
    double y;

    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }

    void setX(double x){
        this.x = x;
    }

    void setY(double y){
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

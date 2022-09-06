package utilitys;

public class Direction {
    double direction;

    Direction(double x){
        direction = x % 360;
    }

    public void setDirection(double direction) {
        this.direction = direction % 360;
    }

    public double getDirection() {
        return direction;
    }
}

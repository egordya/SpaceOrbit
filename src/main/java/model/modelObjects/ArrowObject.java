package model.modelObjects;

import com.grupp7.spaceorbit.controllers.Drawable;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;

public class ArrowObject implements Drawable {

    Polyline arrow = new Polyline();
    double xPos;
    double yPos;

    public ArrowObject(double startX, double startY, double endX, double endY) {
        xPos = startX;
        yPos = startY;


        double x1 = startX;
        double y1 = startY;

        int h = 10;
        int d = 10;

        int dx = (int) (endX - x1), dy = (int) (endY - y1);
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        arrow.getPoints().addAll(startX, startY, endX, endY, ym, xm, endX, endY, yn, xn);








    }

    @Override
    public double getXPos() {
        return xPos;
    }

    @Override
    public double getYPos() {
        return yPos;
    }

    @Override
    public Shape getGeometry() {
        return arrow;
    }

    @Override
    public String getImagePath() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

}

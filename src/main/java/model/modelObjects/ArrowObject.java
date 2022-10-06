package model.modelObjects;

import com.grupp7.spaceorbit.controllers.Drawable;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
import utilitys.Vector2D;

public class ArrowObject implements Drawable {

    Polyline arrow = new Polyline();
    double xPos;
    double yPos;

    public ArrowObject(Vector2D startPos, Vector2D endPos) {


        Vector2D arrowVector = endPos.sub(startPos).getUnitVector();

        double angle = 0.5; // 1 = 45 degrees
        double length = 10;

        Vector2D orthogonal1 = new Vector2D(arrowVector.getY() * -1, arrowVector.getX()).scalarMultiplication(angle);
        Vector2D orthogonal2 = new Vector2D(arrowVector.getY(), arrowVector.getX() * -1).scalarMultiplication(angle);

        Vector2D arrowHeadOneEndPos = arrowVector.scalarMultiplication(-1).add(orthogonal1).scalarMultiplication(length).add(endPos);
        Vector2D arrowHeadTwoEndPos = arrowVector.scalarMultiplication(-1).add(orthogonal2).scalarMultiplication(length).add(endPos);

        arrow.getPoints().addAll(startPos.getX(), startPos.getY(), endPos.getX(), endPos.getY(), arrowHeadOneEndPos.getX(), arrowHeadOneEndPos.getY(), endPos.getX(), endPos.getY(), arrowHeadTwoEndPos.getX(), arrowHeadTwoEndPos.getY());

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

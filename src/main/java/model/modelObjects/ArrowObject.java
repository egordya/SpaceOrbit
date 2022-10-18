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

    /**
     * Constructs an arrow that visualises the input from the user
     * @param startPos a vector that describes the point where the user started to drag their mouse
     * @param endPos a vector that describes the point where the user ended to drag their mouse
     */
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

    /**
     * retrieves the X coordinate
     * @return the X coordinate
     */
    @Override
    public double getXPos() {
        return xPos;
    }

    /**
     * retrieves the Y coordinate
     * @return the Y coordinate
     */
    @Override
    public double getYPos() {
        return yPos;
    }

    /**
     * retrieves the geometry of the arrow
     * @return the geometry of the arrow
     */
    @Override
    public Shape getGeometry() {
        return arrow;
    }

    /**
     * retrieves the path to the image for the arrow
     * @return the path to the image for the arrow
     */
    @Override
    public String getImagePath() {
        return null;
    }

    /**
     * retrieves the name af the arrow
     * @return the name of the arrow
     */
    @Override
    public String getName() {
        return null;
    }

}

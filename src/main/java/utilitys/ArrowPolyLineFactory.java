package utilitys;

import javafx.scene.shape.Polyline;

public class ArrowPolyLineFactory {
    public ArrowPolyLineFactory() {
    }

    public Polyline getArrow(Vector2D startPos, Vector2D endPos){

        Polyline arrow = new Polyline();
        Vector2D arrowVector = endPos.sub(startPos).getUnitVector();

        double angle = 0.5; // 1 = 45 degrees
        double length = 10;

        Vector2D orthogonal1 = new Vector2D(arrowVector.getY() * -1, arrowVector.getX()).scalarMultiplication(angle);
        Vector2D orthogonal2 = new Vector2D(arrowVector.getY(), arrowVector.getX() * -1).scalarMultiplication(angle);

        Vector2D arrowHeadOneEndPos = arrowVector.scalarMultiplication(-1).add(orthogonal1).scalarMultiplication(length).add(endPos);
        Vector2D arrowHeadTwoEndPos = arrowVector.scalarMultiplication(-1).add(orthogonal2).scalarMultiplication(length).add(endPos);

        arrow.getPoints().addAll(startPos.getX(), startPos.getY(), endPos.getX(), endPos.getY(), arrowHeadOneEndPos.getX(), arrowHeadOneEndPos.getY(), endPos.getX(), endPos.getY(), arrowHeadTwoEndPos.getX(), arrowHeadTwoEndPos.getY());

        return arrow;
    }
}

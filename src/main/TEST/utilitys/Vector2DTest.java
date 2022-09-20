package utilitys;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Vector2DTest {

    Vector2D testObj = new Vector2D(5,5);

    @Test
    void getX() {
        Assertions.assertEquals(5, testObj.getX());
    }

    @Test
    void getY() {
        Assertions.assertEquals(5, testObj.getY());
    }

    @Test
    void scalarMultiplication() {
        Vector2D scalarMultiplicationVector = testObj.scalarMultiplication(2);
        Assertions.assertEquals(10, scalarMultiplicationVector.getY());
        Assertions.assertEquals(10, scalarMultiplicationVector.getY());

        Assertions.assertEquals(5, testObj.getY());
        Assertions.assertEquals(5, testObj.getX());
    }

    @Test
    void add() {
        Vector2D addedVector = testObj.add(testObj);
        Assertions.assertEquals(10, addedVector.getY());
        Assertions.assertEquals(10, addedVector.getY());

        Assertions.assertEquals(5, testObj.getY());
        Assertions.assertEquals(5, testObj.getX());
    }

    @Test
    void sub() {
        Vector2D subVector = testObj.sub(testObj);
        Assertions.assertEquals(0, subVector.getY());
        Assertions.assertEquals(0, subVector.getY());

        Assertions.assertEquals(5, testObj.getY());
        Assertions.assertEquals(5, testObj.getX());
    }

    @Test
    void getUnitVector() {
        Vector2D unitVector = testObj.getUnitVector();
        double a = Math.abs(Math.sqrt(2)/2 - unitVector.getX());
        Assertions.assertEquals(true, a<Math.pow(10, -10));

        Assertions.assertEquals(5, testObj.getY());
        Assertions.assertEquals(5, testObj.getX());

    }

    @Test
    void getVectorLength() {
        double vectorLenght = testObj.getVectorLength();
        double d = Math.abs(Math.sqrt(testObj.getY()*testObj.getY() + testObj.getX()* testObj.getX()) - vectorLenght);
        Assertions.assertEquals(true, d<Math.pow(10, -10));
        Assertions.assertEquals(5, testObj.getY());
        Assertions.assertEquals(5, testObj.getX());
    }

}
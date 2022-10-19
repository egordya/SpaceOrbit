package model.modelObjects;

import model.modelObjects.CelestialObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utilitys.Vector2D;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Circle;

public class CelestialObjectTest {/*
    CelestialObject planet1 = new CelestialObject(new Vector2D(0,0), new Vector2D(1,1), 100,10, "",true,"planet1","planet");
    @Test
    void updateHitboxPosTest(){
        Circle geometry = planet1.getGeometry();
        Assertions.assertEquals(new Circle(0,0,10).getCenterX(),geometry.getCenterX());
        Assertions.assertEquals(new Circle(0,0,10).getCenterY(),geometry.getCenterY());
    }

    @Test
    void moveStepTest(){
        planet1.moveStep(1);
        Assertions.assertEquals(1,planet1.getPos().getY());
        Assertions.assertEquals(1,planet1.getPos().getX());
    }

    @Test
    void getterTest(){
        //getVelocityVector
        Assertions.assertEquals(1,planet1.getVelocityVector().getY());
        Assertions.assertEquals(1,planet1.getVelocityVector().getX());

        //getMass
        Assertions.assertEquals(100,planet1.getMass());

        //getIsEffectedByGravity
        Assertions.assertEquals(true,planet1.getIsEffectedByGravity());

        //getType
        Assertions.assertEquals("planet",planet1.getType());

        //getPos
        Assertions.assertEquals(0,planet1.getXPos());
        Assertions.assertEquals(0,planet1.getYPos());

        //getImagePath
        Assertions.assertEquals("",planet1.getImagePath());

        //getName
        Assertions.assertEquals("planet1",planet1.getName());
    }

    @Test
    void setterTest(){
        //setPos
        planet1.setPos(new Vector2D(10,10));
        Assertions.assertEquals(10,planet1.getXPos());
        Assertions.assertEquals(10,planet1.getYPos());

        //setAffectedByGravity
        planet1.setAffectedByGravity(false);
        Assertions.assertEquals(false,planet1.getIsEffectedByGravity());

        //serVelocityVector
        planet1.setVelocityVector(new Vector2D(0,0));
        Assertions.assertEquals(0,planet1.getVelocityVector().getY());
        Assertions.assertEquals(0,planet1.getVelocityVector().getX());

        //setMass
        planet1.setMass(200);
        Assertions.assertEquals(200,planet1.getMass());

    }

    @Test
    void cloneTest(){
        CelestialObject planet1Cloned = planet1.clone();
        Assertions.assertEquals(planet1.getPos().getY(),planet1Cloned.getPos().getY());
        Assertions.assertEquals(planet1.getPos().getX(),planet1Cloned.getPos().getX());
        Assertions.assertEquals(planet1.getVelocityVector().getX(),planet1Cloned.getVelocityVector().getX());
        Assertions.assertEquals(planet1.getVelocityVector().getY(),planet1Cloned.getVelocityVector().getY());
        Assertions.assertEquals(planet1.getMass(),planet1Cloned.getMass());
        Assertions.assertEquals(planet1.getGeometry().getCenterX(),planet1Cloned.getGeometry().getCenterX());
        Assertions.assertEquals(planet1.getGeometry().getCenterY(),planet1Cloned.getGeometry().getCenterY());
        Assertions.assertEquals(planet1.getGeometry().getRadius(),planet1Cloned.getGeometry().getRadius());
        Assertions.assertEquals(planet1.getImagePath(),planet1Cloned.getImagePath());
        Assertions.assertEquals(planet1.getIsEffectedByGravity(),planet1Cloned.getIsEffectedByGravity());
        Assertions.assertEquals(planet1.getName(),planet1Cloned.getName());
        Assertions.assertEquals(planet1.getType(),planet1Cloned.getType());
    }*/


}

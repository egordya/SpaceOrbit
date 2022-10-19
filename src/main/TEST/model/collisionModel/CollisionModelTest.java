package model.collisionModel;

import javafx.scene.shape.Circle;
import model.collisionModels.CollisionModel;
import model.collisionModels.Collisionable;
import model.modelObjects.CelestialObject;
import model.modelObjects.JavaFXGeometry;
import org.junit.jupiter.api.Test;
import utilitys.Collision_2_Tuple;
import utilitys.Vector2D;

import static org.junit.jupiter.api.Assertions.*;



class CollisionModelTest {

    CollisionModel model = new CollisionModel();

    @Test
    void checkForCollisions() {
        Circle c = new Circle();
        c.setRadius(1);
        JavaFXGeometry geometry1 = new JavaFXGeometry(c);

        Circle c2 = new Circle();
        c.setRadius(1);
        JavaFXGeometry geometry2 = new JavaFXGeometry(c2);

        Vector2D position1 = new Vector2D(30, 30);
        Vector2D position2 = new Vector2D(50, 50);

        CelestialObject a = new CelestialObject(position1, new Vector2D(0,0), 0, geometry1, "", true, "a", "a");
        CelestialObject b = new CelestialObject(position2, new Vector2D(0,0), 0, geometry2, "", true, "b", "b");


        CelestialObject[] allObjects = {a, b};


        model.checkCollisions(allObjects);
        Collisionable[][] collisionables = model.getCollidedPairs();

        assertEquals(2, collisionables.length);

        a.setPos(new Vector2D(30, 30));
        b.setPos(new Vector2D(31, 31));

        model.checkCollisions(allObjects);
        Collisionable[][] collisionables2 = model.getCollidedPairs();

        assertEquals(4, collisionables2.length);


    }
}
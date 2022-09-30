package model.collisionModel;

import model.modelObjects.CelestialObject;
import org.junit.jupiter.api.Test;
import utilitys.Collision_2_Tuple;
import utilitys.Vector2D;

import static org.junit.jupiter.api.Assertions.*;



class CollisionModelTest {

    CollisionModel model = new CollisionModel();

    @Test
    void checkForCollisions() {

        Vector2D position1 = new Vector2D(30, 30);
        double radius1 = 1;

        Vector2D position2 = new Vector2D(10, 10);
        double radius2 = 1;

        Vector2D position3 = new Vector2D(30, 32);
        double radius3 = 1;

        Vector2D position4 = new Vector2D(30, 28);
        double radius4 = 1;

        Vector2D position5 = new Vector2D(11, 11);
        double radius5 = 1;

        CelestialObject a = new CelestialObject(position5, new Vector2D(0,0), 0, radius5, "", true, "5", "target");
        CelestialObject b = a.clone();

        CelestialObject[] allObjects = {
                new CelestialObject(position1, new Vector2D(0,0), 0, radius1, "", true, "1", "player"),
                new CelestialObject(position2, new Vector2D(0,0), 0, radius2, "", true, "2", "player"),
                new CelestialObject(position3, new Vector2D(0,0), 0, radius3, "", true, "3", "target"),
                new CelestialObject(position4, new Vector2D(0,0), 0, radius4, "", true, "4", "target"),
                a, b};


        Collision_2_Tuple[] collided = model.checkForCollisions(allObjects);

        assertEquals(6 + 4, collided.length);

        Collision_2_Tuple[] collided2 = model.checkForCollisions(allObjects);

        assertEquals(6 + 4, collided2.length);

    }
}
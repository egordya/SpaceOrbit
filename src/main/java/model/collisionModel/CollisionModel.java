package model.collisionModel;

import utilitys.Collision_2_Tuple;

import java.util.ArrayList;
import java.util.List;

public class CollisionModel{


    /**
     * @param allObjects all objects that will be checked for collisions
     * @return an array of tuples containing objects that has collided
     */
    public Collision_2_Tuple[] checkForCollisions(Collisionable[] allObjects){



        List<Collisionable> copy = new ArrayList<>();
        for (Collisionable x : allObjects){
            copy.add(x);
        }

        ArrayList<Collision_2_Tuple> allCollidedObjects = new ArrayList<>();
        for(Collisionable x : allObjects){
            copy.remove(x);
            allCollidedObjects.addAll(checkForCollisionsWithX(x, copy));
            copy.add(x);
        }

        return allCollidedObjects.toArray(new Collision_2_Tuple[0]);
    }

    /**
     * @param theObject one seperated object that will be checked for collisions with all other objects
     * @param otherObjects  all other objects
     * @return an array of 2 object that has collided
     */
    private ArrayList<Collision_2_Tuple> checkForCollisionsWithX(Collisionable theObject, List<Collisionable> otherObjects) {

        ArrayList<Collision_2_Tuple> collidedObjects = new ArrayList<>();
        for(Collisionable x : otherObjects){
            if (checkIfCollision(theObject,x)){
                collidedObjects.add(new Collision_2_Tuple(theObject, x));
            }
        }
        return collidedObjects;
    }

    /**
     * checks if two objects have collided
     * @param object1 object number 1 that will be checked
     * @param object2 object number 2 that will be checked
     * @return a boolean, true if collision, false otherwise
     */
    private boolean checkIfCollision(Collisionable object1, Collisionable object2){
        return object1.getGeometry().getBoundsInParent().intersects(object2.getGeometry().getBoundsInParent());
    }
}

package model.collisionModel;

import model.modelObjects.CelestialObject;

import java.util.ArrayList;
import java.util.List;

public class CollisionModel{

    /**
     * @param originalAllObjects all the objects to be accounted in the check
     * @return an array that contains arrays of two collided objects
     */
    public Collisionable[][] checkForCollisions(Collisionable[] allObjects){



        List<Collisionable> copy = new ArrayList<>();
        for (Collisionable x : allObjects){
            copy.add(x);
        }

        ArrayList<Collisionable[]> allCollidedObjects = new ArrayList<>();
        for(Collisionable x : allObjects){
            copy.remove(x);
            allCollidedObjects.addAll(checkForCollisionsWithX(x, copy));
            copy.add(x);
        }

        return allCollidedObjects.toArray(new Collisionable[0][0]);
    }

    private ArrayList<Collisionable[]> checkForCollisionsWithX(Collisionable theObject, List<Collisionable> otherObjects) {

        ArrayList<Collisionable[]> collidedObjects = new ArrayList<>();
        for(Collisionable x : otherObjects){
            if (checkIfCollision(theObject,x)){
                Collisionable[] collided = {theObject, x};
                collidedObjects.add(collided);
            }
        }
        return collidedObjects;
    }

    private boolean checkIfCollision(Collisionable object1, Collisionable object2){
        return object1.getHitbox().getBoundsInParent().intersects(object2.getHitbox().getBoundsInParent());
    }
}

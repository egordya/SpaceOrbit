package model.collisionModel;

import utilitys.Collision_2_Tuple;

import java.util.ArrayList;
import java.util.List;

public class CollisionModel{

    /**
     * @param originalAllObjects all the objects to be accounted in the check
     * @return an array that contains arrays of two collided objects
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

    private ArrayList<Collision_2_Tuple> checkForCollisionsWithX(Collisionable theObject, List<Collisionable> otherObjects) {

        ArrayList<Collision_2_Tuple> collidedObjects = new ArrayList<>();
        for(Collisionable x : otherObjects){
            if (checkIfCollision(theObject,x)){
                collidedObjects.add(new Collision_2_Tuple(theObject, x));
            }
        }
        return collidedObjects;
    }

    private boolean checkIfCollision(Collisionable object1, Collisionable object2){
        return object1.getGeometry().getBoundsInParent().intersects(object2.getGeometry().getBoundsInParent());
    }
}

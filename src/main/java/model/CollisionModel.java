package model;

import java.util.ArrayList;
import java.util.List;

public class CollisionModel {
    Collisionable[] allObjects;
    public CollisionModel(Collisionable[] allObjects){
        this.allObjects = allObjects;
    }

    public void updateHitboxesPos(){
        for (Collisionable x : allObjects){
            x.setHitboxPos();
        }
    }

    public boolean checkForCollisions(){
        List<Collisionable> copy = new ArrayList<>();
        for (Collisionable x : allObjects){
            copy.add(x);
        }

        for(Collisionable x : allObjects){
            copy.remove(x);
            if(checkForCollisionsWithX(x, copy))
                return true;
            copy.add(x);
        }
        return false;
    }

    private boolean checkForCollisionsWithX(Collisionable theObject, List<Collisionable> otherObjects) {
        for(Collisionable x : otherObjects){
            if(checkIfCollision(theObject,x))
                theObject.crash();
                return true;
        }
        return false;
    }

    boolean checkIfCollision(Collisionable object1, Collisionable object2){
        if(object1.getHitbox().getBoundsInParent().intersects(object2.getHitbox().getBoundsInParent())){
            return true;
        }
        else
            return false;
    }


}

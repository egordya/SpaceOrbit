package model.collisionModel;

import java.util.ArrayList;
import java.util.List;

public class CollisionModel {
    Collisionable[] allObjects;
    public CollisionModel(Collisionable[] allObjects){
        this.allObjects = allObjects;
    }

    private void updateHitboxesPos(){
        for (Collisionable x : allObjects){
            x.setHitboxPos();
        }
    }

    public boolean checkForCollisions(){
        updateHitboxesPos();
        List<Collisionable> copy = new ArrayList<>();
        for (Collisionable x : allObjects){
            copy.add(x);
        }

        for(Collisionable x : allObjects){
            copy.remove(x);
            checkForCollisionsWithX(x, copy);
            copy.add(x);
        }
        return false;
    }

    private void checkForCollisionsWithX(Collisionable theObject, List<Collisionable> otherObjects) {
        for(Collisionable x : otherObjects){
            if(checkIfCollision(theObject,x)){
                theObject.crash(x.getType());
            }
        }

    }

    boolean checkIfCollision(Collisionable object1, Collisionable object2){
        if(object1.getHitbox().getBoundsInParent().intersects(object2.getHitbox().getBoundsInParent())){
            System.out.println(object1.getHitbox().getBoundsInParent());
            System.out.println(object2.getHitbox().getBoundsInParent());
            return true;
        }
        else
            return false;
    }
}

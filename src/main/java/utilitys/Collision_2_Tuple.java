package utilitys;

import model.collisionModel.Collisionable;

public class Collision_2_Tuple {
    private Collisionable obj1;
    private Collisionable obj2;

    public Collision_2_Tuple(Collisionable obj1, Collisionable obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public Collisionable getFirstCollided(){
        return obj1;
    }

    public Collisionable getSecondCollided(){
        return obj2;
    }
}

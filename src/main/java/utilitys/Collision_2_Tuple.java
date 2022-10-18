package utilitys;

import model.collisionModel.Collisionable;

public class Collision_2_Tuple {
    private Collisionable obj1;
    private Collisionable obj2;

    /**
     * Constructs a tuple containing 2 Collisionable objects
     * @param obj1 Collisionable objects 1
     * @param obj2 Collisionable objects 2
     */
    public Collision_2_Tuple(Collisionable obj1, Collisionable obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    /**
     * @return the first object
     */
    public Collisionable getFirstCollided(){
        return obj1;
    }

    /**
     * @return the second object
     */
    public Collisionable getSecondCollided(){
        return obj2;
    }
}

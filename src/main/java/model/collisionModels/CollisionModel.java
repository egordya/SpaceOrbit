package model.collisionModels;

import javafx.scene.shape.Shape;
import model.modelObjects.Geometry;

import java.util.ArrayList;

public class CollisionModel {

    ArrayList<Collisionable[]> collidedPairs;

    /**
     * Checks for collisions. If to objects collide they will be added to a list containing all collided objects as a couple
     * @param collisionables all objects that will be checked for collisions
     */
    public void checkCollisions(Collisionable[] collisionables) {
        collidedPairs = new ArrayList<>();
        for(Collisionable a : collisionables){
            for(Collisionable b : collisionables){
                Geometry geometryA = a.getGeometry();
                Geometry geometryB = b.getGeometry();
                if(geometryA.intersects(geometryB.getGeometry())){
                    Collisionable[] pair = new Collisionable[2];
                    pair[0] = a;
                    pair[1] = b;
                    collidedPairs.add(pair);
                }
            }
        }
    }

    /**
     * @return a list of all collided pairs
     */
    public Collisionable[][] getCollidedPairs() {
        return collidedPairs.toArray(new Collisionable[0][0]);
    }
}

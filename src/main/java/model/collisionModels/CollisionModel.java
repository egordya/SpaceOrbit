package model.collisionModels;

import javafx.scene.shape.Shape;
import model.modelObjects.Geometry;

import java.util.ArrayList;

public class CollisionModel {

    ArrayList<Collisionable[]> collidedPairs;

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

    public Collisionable[][] getCollidedPairs() {
        return collidedPairs.toArray(new Collisionable[0][0]);
    }
}

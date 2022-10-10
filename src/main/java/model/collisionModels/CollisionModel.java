package model.collisionModels;

import javafx.scene.shape.Shape;
import model.modelObjects.Geometry;

import java.util.ArrayList;

public class CollisionModel {

    ArrayList<String[]> collidedPairs;

    public void checkCollisions(Collisionable[] collisionables) {
        collidedPairs = new ArrayList<>();
        for(Collisionable a : collisionables){
            for(Collisionable b : collisionables){
                Geometry geometryA = a.getGeometry();
                Geometry geometryB = b.getGeometry();
                if(geometryA.intersects(geometryB.getGeometry())){
                    String[] pair = new String[2];
                    pair[0] = a.getType();
                    pair[1] = b.getType();
                    collidedPairs.add(pair);
                }
            }
        }
    }

    public String[][] getCollidedPairs() {
        return collidedPairs.toArray(new String[0][0]);
    }
}

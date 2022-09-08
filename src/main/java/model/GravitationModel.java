package model;


import utilitys.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class GravitationModel {
    ObjectForGravitationModel[] allObjects;
    List<ObjectForGravitationModel> allObjectsWithNextState = new ArrayList<>();
    public GravitationModel(ObjectForGravitationModel[] allObjects){
        this.allObjects = allObjects;
    }



    public void updateOrbitModelObject(ObjectForGravitationModel[] allObjects){
        this.allObjects = allObjects;
    }

    public ObjectForGravitationModel[] doSimulationStep(double time){
        List<ObjectForGravitationModel> copy = new ArrayList<>();
        for(ObjectForGravitationModel x : allObjects){
            copy.add(x);
        }

        for(ObjectForGravitationModel x : allObjects){
            copy.remove(x);
            allObjectsWithNextState.add(nextStateForObject(x, copy));
            copy.add(x);
        }


        return null;
    }

    private ObjectForGravitationModel nextStateForObject (ObjectForGravitationModel x, List<ObjectForGravitationModel> allOtherObjects){
        ObjectForGravitationModel mainObject = x.clone();

        if (!mainObject.getIsEffectedByGravity()){
            return mainObject;
        }

        ArrayList<Vector2D> otherObjectsPos = new ArrayList<>();
        for(ObjectForGravitationModel obj : allOtherObjects){
            otherObjectsPos.add(obj.getPos());
        }

        ArrayList<Vector2D> distanceVectors = getDistanceVectorsToSurroundingObjects(x.getPos(), otherObjectsPos);


    }

    private ArrayList<Vector2D> getDistanceVectorsToSurroundingObjects(Vector2D theObjectPos, ArrayList<Vector2D> otherObjectsPos){

        ArrayList<Vector2D> distanceVectors = new ArrayList<>();
        for (Vector2D x : otherObjectsPos){
            distanceVectors.add()
        }

        return null;
    }
}

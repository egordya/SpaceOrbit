package model.gravitationModel;


import utilitys.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class GravitationModel {

    final double G = 0.0000000000667430;

    ObjectForGravitationModel[] allObjects;
    public GravitationModel(ObjectForGravitationModel[] allObjects){
        this.allObjects = allObjects;
    }



    public void updateOrbitModelObject(ObjectForGravitationModel[] allObjects){
        this.allObjects = allObjects;
    }

    public ArrayList<ObjectForGravitationModel> doSimulationStep(double time){
        List<ObjectForGravitationModel> copy = new ArrayList<>();
        for(ObjectForGravitationModel x : allObjects){
            copy.add(x);
        }

        ArrayList<ObjectForGravitationModel> allObjectsWithNextState = new ArrayList<>();
        for(ObjectForGravitationModel x : allObjects){
            copy.remove(x);
            allObjectsWithNextState.add(nextStateForObject(x, copy, time));
            copy.add(x);
        }

        mutateAllObjectsArray(allObjectsWithNextState);


        return allObjectsWithNextState;
    }

    private void mutateAllObjectsArray(List<ObjectForGravitationModel> allObjectsWithNextState){
        for(int i = 0; i<allObjects.length; i++){
            allObjects[i].setPos(allObjectsWithNextState.get(i).getPos());
            allObjects[i].setVelocityVector(allObjectsWithNextState.get(i).getVelocityVector());
        }
    }

    private ObjectForGravitationModel nextStateForObject (ObjectForGravitationModel x, List<ObjectForGravitationModel> allOtherObjects, double time){
        ObjectForGravitationModel mainObject = x.clone();



        if (!mainObject.getIsEffectedByGravity()){
            return mainObject;
        }

        ArrayList<Vector2D> otherObjectsPos = new ArrayList<>();
        for(ObjectForGravitationModel obj : allOtherObjects){
            otherObjectsPos.add(obj.getPos());
        }

        ArrayList<Double> otherObjectsMass = new ArrayList<>();
        for(ObjectForGravitationModel obj : allOtherObjects){
            otherObjectsMass.add(obj.getMass());
        }
        ArrayList<Vector2D> distanceVectors = getDistanceVectorsToSurroundingObjects(mainObject.getPos(), otherObjectsPos);

        ArrayList<Double> lengthOfDistanceVectors = Vector2D.getLengthOfVectors(distanceVectors);
        ArrayList<Vector2D> unitVectorsOfDistanceVectors = Vector2D.getUnitVectorsOfVectors(distanceVectors);

        Vector2D summedForceVector = getSummedForceVector(unitVectorsOfDistanceVectors, lengthOfDistanceVectors, mainObject.getMass(), otherObjectsMass);

        double lengthOfSummedForceVector = summedForceVector.getVectorLength();
        Vector2D unitVectorOfSummedForceVector = summedForceVector.getUnitVector();

        double acceleration = lengthOfSummedForceVector/mainObject.getMass();

        Vector2D velocityVector = unitVectorOfSummedForceVector.scalarMultiplication(acceleration*time);

        Vector2D previousVelocityVector = mainObject.getVelocityVector();
        Vector2D newVelocityVector = previousVelocityVector.add(velocityVector);

        mainObject.setVelocityVector(newVelocityVector);
        mainObject.moveStep(time);

        return mainObject;

    }

    private Vector2D getSummedForceVector(ArrayList<Vector2D> unitVectorsOfDistanceVectors, ArrayList<Double> lengthOfDistanceVectors, double theObjectMass, ArrayList<Double> otherObjectsMass) {

        ArrayList<Vector2D> forceVectors = new ArrayList<>();
        for (int i = 0; i < otherObjectsMass.size(); i++){
            double f = newtonGravitationalLaw(theObjectMass, otherObjectsMass.get(i), lengthOfDistanceVectors.get(i));
            Vector2D forceVector = unitVectorsOfDistanceVectors.get(i).scalarMultiplication(f);
            forceVectors.add(forceVector);
        }

        Vector2D summedForceVector = new Vector2D(0,0);
        for(Vector2D force : forceVectors){
            summedForceVector = summedForceVector.add(force);
        }


        return summedForceVector;

    }

    private double newtonGravitationalLaw(double m1, double m2, double r){
        return G * ((m1 * m2) / Math.pow(r, 2));
    }


    private ArrayList<Vector2D> getDistanceVectorsToSurroundingObjects(Vector2D theObjectPos, ArrayList<Vector2D> otherObjectsPos){

        ArrayList<Vector2D> distanceVectors = new ArrayList<>();
        for (Vector2D x : otherObjectsPos){
            Vector2D distanceVector = x.sub(theObjectPos);
            distanceVectors.add(distanceVector);
        }

        return distanceVectors;
    }
}

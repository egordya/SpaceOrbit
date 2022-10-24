package model.gravitationModel;


import utilitys.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class GravitationModel {

    final double G = 0.0000000000667430;

    ObjectForGravitationModel[] allObjects;
    public GravitationModel(){
    }


    /**
     * @param Objects all objects that will be affected by gravity and/or affect other objects with gravity
     * @param time  the amount of time that all object will affect eachother.
     * @return all objects with updated positions post calculations
     */
    public ObjectForGravitationModel[] doSimulationStep(ObjectForGravitationModel[] Objects, double time){

        allObjects = Objects;

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

        mutateAllObjectsArray(allObjectsWithNextState.toArray(new ObjectForGravitationModel[0]));


        return allObjectsWithNextState.toArray(new ObjectForGravitationModel[0]);
    }

    /**
     * updates all necessary parameters of the objects
     * @param allObjectsWithNextState all objects that will be updated
     */
    private void mutateAllObjectsArray(ObjectForGravitationModel[] allObjectsWithNextState){
        for (int i = 0; i<allObjects.length; i++){
            allObjects[i].setVelocityVector(allObjectsWithNextState[i].getVelocityVector());
            allObjects[i].setPos(allObjectsWithNextState[i].getPos());

        }
    }

    /**
     * calculates how one object is affected by all other objects
     * @param x the object in question
     * @param allOtherObjects all other objects
     * @param time  the amount of time the object is affected by all other objects in this specific state
     * @return the object with updated parameters
     */
    private ObjectForGravitationModel nextStateForObject (ObjectForGravitationModel x, List<ObjectForGravitationModel> allOtherObjects, double time){
        ObjectForGravitationModel mainObject = x.clone();



        if (!mainObject.getIsEffectedByGravity()){
            mainObject.moveStep(time);
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

    /**
     * calculates a forcevector for one specific object
     * @param unitVectorsOfDistanceVectors an array of unit vector to all other objects. The unitvector of object x describes the direction from the object to x
     * @param lengthOfDistanceVectors an array of lenghts between the object and other objects
     * @param theObjectMass the object mass
     * @param otherObjectsMass an array of the other objects mass
     * @return a forcevector
     */
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

    /**
     * @param m1 mass1
     * @param m2 mass2
     * @param r distance between objects
     * @return newton's gravitional Law
     */
    private double newtonGravitationalLaw(double m1, double m2, double r){
        return G * ((m1 * m2) / Math.pow(r, 2));
    }


    /**
     * @param theObjectPos the object's position
     * @param otherObjectsPos an array of all other objects positions
     * @return an array of vectors describing the distance to all other objects surrounding the object
     */
    private ArrayList<Vector2D> getDistanceVectorsToSurroundingObjects(Vector2D theObjectPos, ArrayList<Vector2D> otherObjectsPos){

        ArrayList<Vector2D> distanceVectors = new ArrayList<>();
        for (Vector2D x : otherObjectsPos){
            Vector2D distanceVector = x.sub(theObjectPos);
            distanceVectors.add(distanceVector);
        }

        return distanceVectors;
    }
}

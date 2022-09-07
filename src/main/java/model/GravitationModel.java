package model;


import utilitys.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class GravitationModel {
    private ObjectForGravitationModel[] allObjects;
    private List<ObjectForGravitationModel> allObjectsWithNextState = new ArrayList<>();
    private List<Vector2D> distanceVectors = new ArrayList<>();
    private List<Double> distancesToObjects = new ArrayList<>();
    private List<Vector2D> unitVectors = new ArrayList<>();
    private List<Double> forces = new ArrayList<>();
    private List<Vector2D> forceVectors = new ArrayList<>();
    private Vector2D finalForceVector;
    private Vector2D finalUnitVector;
    private double finalForce;
    private double acceleration;
    private Vector2D accelerationVector;
    private Vector2D velocityVector;
    private Vector2D finalVelocityVector;
    private Vector2D finalDistanceVector;

    public static double gravitationalConstant = 6.674*Math.pow(10,-11);
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
            allObjectsWithNextState.add(nextStateForObject(x, copy, time));
            copy.add(x);
        }
        // updates allObjects array
        int length = allObjects.length;
        for(int x = 0; x < length; x++){
            allObjects[x] = allObjectsWithNextState.get(x);
        }

        return allObjects;
    }

    private ObjectForGravitationModel nextStateForObject (ObjectForGravitationModel x, List<ObjectForGravitationModel> allOtherObjects,double time){
        ObjectForGravitationModel mainObject = x.clone();
        if (!mainObject.getIsEffectedByGravity()){
            return mainObject;
        }
        // 1. få distansvektor från alla objekt runt omkring
        for (ObjectForGravitationModel a : allOtherObjects){
            Vector2D distanceVector = a.getPos().subtract(mainObject.getPos());
            distanceVectors.add(distanceVector);
        }

        // 2. Gör ovanstående vektorer till enhetsvektorer och sparar distanser (vektorernas storlek)
        for(Vector2D v : distanceVectors){
            double distance = Math.sqrt(Math.abs(v.getX())*Math.abs(v.getX()) + Math.abs(v.getY())*Math.abs(v.getY()));
            distancesToObjects.add(distance);
            Vector2D unitVector = v.scalarDivision(distance);
            unitVectors.add(unitVector);
        }

        // 3. Ta fram Gravitationskraft för varje omringade objekt mga distanser och newtons böglag
        if (allOtherObjects.size() != distancesToObjects.size())
            return null; // TODO Something Wrong!!
        for (int i = 0 ; i != allOtherObjects.size() ; i++){
            double force = ((mainObject.getMass()* allOtherObjects.get(i).getMass())/distancesToObjects.get(i))* gravitationalConstant;
            forces.add(force);
        }

        // 4. Muliplicera varje enhetsvektor med respektive gravitationskraft
        if (unitVectors.size() != forces.size())
            return null; // TODO Something Wrong!!
        for (int i = 0 ; i != forces.size() ; i++){
            Vector2D forceVector = unitVectors.get(i).scalarMultiplication(forces.get(i));
            forceVectors.add(forceVector);
        }

        //5. addera åvanstående vektorer
        Vector2D forceVector = new Vector2D(0,0);
        for(int i=0 ; i != forceVectors.size() ; i++){
            forceVector = forceVector.add(forceVectors.get(i));
        }
        finalForceVector = forceVector;

        //6. gör om kraftvektorn till enhetsvektor och spara kraften
        finalForce = Math.sqrt(Math.abs(finalForceVector.getX())*Math.abs(finalForceVector.getX())
                + Math.abs(finalForceVector.getY())*Math.abs(finalForceVector.getY()));
        finalUnitVector = finalForceVector.scalarDivision(finalForce);

        //7. få fram acceleration genom F=ma
        acceleration = finalForce/mainObject.getMass();

        //8. få fram accelerationsvektorn genom att multiplicera enhetsvektorn med A
        accelerationVector = finalUnitVector.scalarMultiplication(acceleration);

        //9. få fram hastighetsvektorn genom att multiplicera accelerationsvektorn med T
        velocityVector = accelerationVector.scalarMultiplication(time);

        //10. addera hastighetsvektorn med orginala hastighetsvektorn
        finalVelocityVector = velocityVector.add(mainObject.getVelocityVector());

        //11. få fram distansvektorn genom att multiplicera hastighetsvektorn med T
        finalDistanceVector = finalVelocityVector.scalarMultiplication(time);

        //12. flytta object mga distansvektor
        mainObject.setPos(mainObject.getPos().add(finalDistanceVector));

        //13. töm alla listor som behövs tömmas
        distanceVectors.clear();
        distancesToObjects.clear();
        unitVectors.clear();
        forces.clear();
        forceVectors.clear();
        return mainObject;
    }
    private Vector2D getDirectionFromPointToPoint(Vector2D startPosition, Vector2D endPosition){
        return null;
    }
}

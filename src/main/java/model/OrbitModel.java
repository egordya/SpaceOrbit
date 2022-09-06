package model;

public class OrbitModel {
    OrbitModelObject[] allObjects;
    public OrbitModel(OrbitModelObject[] allObjects){
        this.allObjects = allObjects;
    }

    public void updateOrbitModelObject(OrbitModelObject[] allObjects){
        this.allObjects = allObjects;
    }

    public void doSimulationStep(double time){

    }
}

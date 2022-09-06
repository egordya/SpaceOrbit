package model;

public class GravitationModel {
    GravitationModelObject[] allObjects;
    public GravitationModel(GravitationModelObject[] allObjects){
        this.allObjects = allObjects;
    }

    public void updateOrbitModelObject(GravitationModelObject[] allObjects){
        this.allObjects = allObjects;
    }

    public void doSimulationStep(double time){

    }
}

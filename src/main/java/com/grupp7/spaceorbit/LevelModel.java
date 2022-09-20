package com.grupp7.spaceorbit;

import model.CelestialObject;
import model.CollisionModel;
import model.GravitationModel;
import utilitys.Vector2D;

public class LevelModel {

    GravitationModel gravitationModel;
    CollisionModel collisionModel;

    CelestialObject[] CelestialObjects;
    CelestialObject[] targets;

    //planets(new Vector2D(0, 69.8 *Math.pow(10,6) * Math.pow(10,3)), new Vector2D(38.86 *Math.pow(10,3), 0), 0.330 * Math.pow(10,24), 2),


    public LevelModel() {

        this.gravitationModel = gravitationModel;
        this.collisionModel = collisionModel;
        
    }


    public void setGravitationModel(GravitationModel gravitationModel) {
        this.gravitationModel = gravitationModel;
    }

    public void setCollisionModel(CollisionModel collisionModel){
        this.collisionModel = collisionModel;
    }

    public void setTargets(CelestialObject[] targets){
        //targets
    }
}

package model.modelObjects;

import com.grupp7.spaceorbit.controllers.Drawable;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import model.collisionModel.Collisionable;
import model.gravitationModel.ObjectForGravitationModel;
import utilitys.Vector2D;



public class CelestialObject implements ObjectForGravitationModel, Collisionable, Drawable {

    private Vector2D position;
    private Vector2D velocityVector;
    private double mass;
    private Circle Geometry = new Circle(0,0,0);
    private double radius;
    private String imagePath;
    private boolean isAffectedByGravity;
    private String name;
    private String type;


    public CelestialObject(Vector2D position, Vector2D velocityVector, double mass, double radius, String imagePath,
                           boolean isAffectedByGravity, String name, String type) {
        this.position = position;
        this.velocityVector = velocityVector;
        this.mass = mass;
        this.radius = radius;
        this.Geometry.setRadius(this.radius);
        this.imagePath = imagePath;
        this.isAffectedByGravity = isAffectedByGravity;
        this.name = name;
        this.type = type;

        updateHitBoxPos();


        Image test = new Image(getClass().getResource(imagePath).toString());
        Geometry.setFill(new ImagePattern(test));



    }


    public void updateHitBoxPos(){

        this.Geometry.setCenterX(this.getPos().getX());
        this.Geometry.setCenterY(this.getPos().getY());

    }


    @Override
    public void moveStep(double time) {
        Vector2D positionStep = velocityVector.scalarMultiplication(time);
        position = position.add(positionStep);
        updateHitBoxPos();

    }

    @Override
    public Vector2D getPos() {
        return position;
    }

    @Override
    public Vector2D getVelocityVector() {
        return velocityVector;
    }

    @Override
    public double getMass() {
        return mass;
    }

    @Override
    public boolean getIsEffectedByGravity() {
        return isAffectedByGravity;
    }

    @Override
    public void setPos(Vector2D position) {
        this.position = position;
        updateHitBoxPos();
    }

    public void setAffectedByGravity(boolean affectedByGravity) {
        isAffectedByGravity = affectedByGravity;
    }

    @Override
    public void setVelocityVector(Vector2D velocityVector) {
        this.velocityVector = velocityVector;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    @Override
    public CelestialObject clone() {
        return new CelestialObject(position.clone(), velocityVector.clone(), mass, radius, imagePath, isAffectedByGravity, name, type);
    }
    @Override
    public String getType(){
        return this.type;
    }

    @Override
    public double getXPos() {
        return position.getX();
    }

    @Override
    public double getYPos() {
        return position.getY();
    }

    @Override
    public Circle getGeometry() {   //Här är det en dålig ändring JIPPIEE!!!
        return Geometry;
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public String getName() {
        return name;
    }
}

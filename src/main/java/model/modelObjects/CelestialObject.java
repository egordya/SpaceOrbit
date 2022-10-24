package model.modelObjects;

import com.grupp7.spaceorbit.views.Drawable;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import model.collisionModels.Collisionable;
import model.gravitationModel.ObjectForGravitationModel;
import utilitys.Vector2D;



public class CelestialObject implements ObjectForGravitationModel, Drawable, Collisionable {

    private Vector2D position;
    private Vector2D velocityVector;
    private double mass;
    /**
     * the hitbox of the CelestialObject
     */
    private Geometry geometry;
    private String imagePath;
    private boolean isAffectedByGravity;
    private String name;
    private String type;


    /**
     * Constructs a CelestialObject
     * @param position a vector describing the position
     * @param velocityVector a vector describing the velocity
     * @param mass the mass
     * @param geometry the shape of the object
     * @param imagePath a path to the image that will visualise the CelestialObject
     * @param isAffectedByGravity   boolean, if true the CelestiaObject is affected by gravity.
     * @param name the name of the CelestiaObject
     * @param type the type of the CelestiaObject
     */
    public CelestialObject(Vector2D position, Vector2D velocityVector, double mass, Geometry geometry, String imagePath,
                           boolean isAffectedByGravity, String name, String type) {
        this.position = position;
        this.velocityVector = velocityVector;
        this.mass = mass;
        this.geometry = geometry;
        this.imagePath = imagePath;
        this.isAffectedByGravity = isAffectedByGravity;
        this.name = name;
        this.type = type;

        updateHitBoxPos();

    }


    /**
     * updates the position of the CelestiaObject's hitbox
     */
    public void updateHitBoxPos(){
            this.geometry.relocate(this.getXPos(), this.getYPos());
    }


    /**
     * updates the CelestiaObject's position from a to b
     * @param time the time that it takes for the CelestiaObject to move from a to b
     */
    @Override
    public void moveStep(double time) {
        Vector2D positionStep = velocityVector.scalarMultiplication(time);
        position = position.add(positionStep);
        updateHitBoxPos();

    }

    /**
     * @return the position of the CelestiaObject
     */
    @Override
    public Vector2D getPos() {
        return position;
    }

    /**
     * @return a vector that describes the velocity of the CelestiaObject
     */
    @Override
    public Vector2D getVelocityVector() {
        return velocityVector;
    }

    /**
     * @return the mass of the CelestiaObject
     */
    @Override
    public double getMass() {
        return mass;
    }

    /**
     * @return the boolean that describes whether the CelestiaObject is affected by gravity or not
     */
    @Override
    public boolean getIsEffectedByGravity() {
        return isAffectedByGravity;
    }

    /**
     * sets the position of an CelestiaObject
     * @param position the position
     */
    @Override
    public void setPos(Vector2D position) {
        this.position = position;
        updateHitBoxPos();
    }

    /**
     * sets the affectedByGravity boolean
     * @param affectedByGravity the boolean
     */
    public void setAffectedByGravity(boolean affectedByGravity) {
        isAffectedByGravity = affectedByGravity;
    }

    /**
     * sets the velocity of the CelestiaObject
     * @param velocityVector the velocity
     */
    @Override
    public void setVelocityVector(Vector2D velocityVector) {
        this.velocityVector = velocityVector;
    }

    /**
     * sets the mass of the CelestiaObject
     * @param mass the mass
     */
    public void setMass(double mass) {
        this.mass = mass;
    }

    /**
     * constructs a clone of the CelestiaObject
     * @return a cloned version of the CelestiaObject
     */
    @Override
    public CelestialObject clone() {
        return new CelestialObject(position.clone(), velocityVector.clone(), mass, geometry, imagePath, isAffectedByGravity, name, type);
    }

    /**
     * retrieves the type of the CelestiaObject
     * @return the type
     */
    @Override
    public String getType(){
        return this.type;
    }

    /**
     * retrieves the X coordinate of the CelestiaObject
     * @return the X coordinate
     */
    @Override
    public double getXPos() {
        return position.getX();
    }

    /**
     * retrieves the X coordinate of the CelestiaObject
     * @return the X coordinate
     */
    @Override
    public double getYPos() {
        return position.getY();
    }

    /**
     * retrieves the hitbox of an CelestiaObject
     * @return the hitbox
     */
    @Override
    public Geometry getGeometry() {   //Här är det en dålig ändring JIPPIEE!!!
        return geometry;
    }

    /**
     * retrieves the path to the image that visualises the CelestiaObject
     * @return the path
     */
    @Override
    public String getImagePath() {
        return imagePath;
    }

    /**
     * retrieves the name of the CelestiaObject
     * @return  the name
     */
    @Override
    public String getName() {
        return name;
    }
}

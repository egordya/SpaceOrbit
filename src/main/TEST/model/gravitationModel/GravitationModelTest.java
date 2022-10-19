package model.gravitationModel;

import javafx.scene.shape.Circle;
import model.modelObjects.CelestialObject;
import model.modelObjects.JavaFXGeometry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utilitys.Vector2D;

class GravitationModelTest {

    //source: https://nssdc.gsfc.nasa.gov/planetary/factsheet/

    double timeStep = 10000.0; //Seconds

    double error = 0.0309; // 3.09%

    double[] orbitPeriodsForPlanets = {88.0, 224.7, 365.2, 687.0, 4331, 10747, 30589, 59800}; //days
    double[] AphelionForPlanets = {69.8 * Math.pow(10,6) * Math.pow(10,3), 108.9 * Math.pow(10,6) * Math.pow(10,3), 152.1 * Math.pow(10,6) * Math.pow(10,3), 249.261 * Math.pow(10,6) * Math.pow(10,3), 816.4 * Math.pow(10,6) * Math.pow(10,3), 1506.5 * Math.pow(10,6) * Math.pow(10,3), 3001.4 * Math.pow(10,6) * Math.pow(10,3), 4558.9 * Math.pow(10,6) * Math.pow(10,3)}; //m
    double[] PerihelionForPlanets = {-46.000 * Math.pow(10,6) * Math.pow(10,3), -107.5 * Math.pow(10,6) * Math.pow(10,3), -147.1 * Math.pow(10,6) * Math.pow(10,3), -206.7 * Math.pow(10,6) * Math.pow(10,3), -740.6 * Math.pow(10,6) * Math.pow(10,3), -1357.6 * Math.pow(10,6) * Math.pow(10,3), -2732.7 * Math.pow(10,6) * Math.pow(10,3), -4471.1 * Math.pow(10,6) * Math.pow(10,3)}; //m

    @Test
    void doSimulationStep() {
        test(0);
        System.out.println("0");
        test(1);
        System.out.println("1");
        test(2);
        System.out.println("2");
        test(3);
        System.out.println("3");
        test(4);
        System.out.println("4");
        test(5);
        System.out.println("5");
        test(6);
        System.out.println("6");
        test(7);
        System.out.println("7");



    }

    private CelestialObject[] getSolarSystem(){
        JavaFXGeometry geometry = new JavaFXGeometry(new Circle());
        CelestialObject[] solarSystem =  {

                new CelestialObject(new Vector2D(0, 0), new Vector2D(0, 0), 1.989 * Math.pow(10,30), geometry, "", true, "solen","star"),
                new CelestialObject(new Vector2D(0, 69.8 * Math.pow(10,6) * Math.pow(10,3)), new Vector2D(38.86 * Math.pow(10,3), 0), 0.330 * Math.pow(10,24), geometry, "", true, "Mercury","planet"),
                new CelestialObject(new Vector2D(0,108.9 * Math.pow(10,6) * Math.pow(10,3)), new Vector2D(34.79 * Math.pow(10,3), 0), 4.87 * Math.pow(10,24), geometry, "", true, "Venus","planet"),
                new CelestialObject(new Vector2D(0, 152.1 * Math.pow(10,6) * Math.pow(10,3)), new Vector2D(29.29 * Math.pow(10,3), 0), 5.97 * Math.pow(10,24), geometry, "", true, "Earth","planet"),
                new CelestialObject(new Vector2D(0, 249.261 * Math.pow(10,6) * Math.pow(10,3)), new Vector2D(21.97 * Math.pow(10,3), 0), 0.642 * Math.pow(10,24), geometry, "", true, "Mars","planet"),
                new CelestialObject(new Vector2D(0, 816.4 * Math.pow(10,6) * Math.pow(10,3)), new Vector2D(12.44 * Math.pow(10,3), 0), 1898 * Math.pow(10,24), geometry, "", true, "Jupiter","planet"),
                new CelestialObject(new Vector2D(0, 1506.5 * Math.pow(10,6) * Math.pow(10,3)), new Vector2D(9.09 * Math.pow(10,3), 0), 568 * Math.pow(10,24), geometry, "", true, "Saturn","planet"),
                new CelestialObject(new Vector2D(0, 3001.4 * Math.pow(10,6) * Math.pow(10,3)), new Vector2D(6.49 * Math.pow(10,3), 0), 86.8 * Math.pow(10,24), geometry, "", true, "Uranus","planet"),
                new CelestialObject(new Vector2D(0, 4558.9 * Math.pow(10,6) * Math.pow(10,3)), new Vector2D(5.37 * Math.pow(10,3), 0), 102 * Math.pow(10,24),geometry, "", true, "Neptune","planet")
        };

        return solarSystem;
    }

    private void test (int index){

        CelestialObject[] planteter = getSolarSystem();
        GravitationModel theModel = new GravitationModel();
        for (int i = 0; i<orbitPeriodsForPlanets[index]*24*60*60/timeStep; i++){
            theModel.doSimulationStep(planteter, timeStep);
        }
        Assertions.assertEquals(true, (planteter[index + 1].getPos().getY() > (AphelionForPlanets[index]*(1-error))) && planteter[index+1].getPos().getY() < (AphelionForPlanets[index]*(1+error)));

        planteter = getSolarSystem();
        theModel = new GravitationModel();
        for (int i = 0; i<orbitPeriodsForPlanets[index]*24*60*60/timeStep/2; i++){
            theModel.doSimulationStep(planteter, timeStep);
        }
        Assertions.assertEquals(true, (planteter[index + 1].getPos().getY() < (PerihelionForPlanets[index]*(1-error))) && planteter[index+1].getPos().getY() > (PerihelionForPlanets[index]*(1+error)));

    }


}
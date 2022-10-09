package model.gameModel;

import model.modelObjects.CelestialObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class GameModelBuilderTest {



    @Test
    void gameModelBuilderPlanetsTest() throws FileNotFoundException {

        GameModel gameModel = GameModelBuilder.getGameModel("src/main/resources/json/levels/testLevel.json");
        //when this string is used there should be the path to level 2 here
        CelestialObject[] planets = gameModel.planets;

        for (int i = 0; i < 4; i++){
            int j = i + 1;
            Assertions.assertEquals("\"planet"+ j +"\"" ,planets[i].getName());
            Assertions.assertEquals(7000000000000000.0,planets[i].getMass());
            Assertions.assertEquals(6,planets[i].getGeometry().getRadius());
        }
        for (int i = 0; i<3;i++){
            Assertions.assertEquals(false,planets[i].getIsEffectedByGravity());
            Assertions.assertEquals(0,planets[i].getVelocityVector().getY());
            Assertions.assertEquals(0,planets[i].getVelocityVector().getX());
        }
        Assertions.assertTrue(planets[3].getIsEffectedByGravity());
        Assertions.assertEquals(350,planets[0].getXPos());
        Assertions.assertEquals(200,planets[0].getYPos());
        Assertions.assertEquals(350,planets[1].getXPos());
        Assertions.assertEquals(100,planets[1].getYPos());
        Assertions.assertEquals(200,planets[2].getXPos());
        Assertions.assertEquals(100,planets[2].getYPos());
        Assertions.assertEquals(200,planets[3].getXPos());
        Assertions.assertEquals(200,planets[3].getYPos());
        Assertions.assertEquals(20,planets[3].getVelocityVector().getY());
        Assertions.assertEquals(10,planets[3].getVelocityVector().getX());
        Assertions.assertEquals("\"/test/hest/best/yes-t.png\"",planets[0].getImagePath());
        Assertions.assertEquals("\"/planet.gif\"",planets[1].getImagePath());
        Assertions.assertEquals("\"/planet.gif\"",planets[2].getImagePath());
        Assertions.assertEquals("\"/tothemoon.gif\"",planets[3].getImagePath());
    }
    @Test
    void gameModelBuilderPlayersTest() throws FileNotFoundException {
        GameModel gameModel = GameModelBuilder.getGameModel("src/main/resources/json/levels/testLevel.json");
        //when this string is used there should be the path to level 2 here
        CelestialObject[] players = gameModel.players;

        for (int i = 0; i < 2; i++){
            int j = i + 1;
            Assertions.assertEquals("\"playerObj"+ j +"\"" ,players[i].getName());
            Assertions.assertEquals(4,players[i].getMass());
            Assertions.assertEquals(3,players[i].getGeometry().getRadius());
            Assertions.assertTrue(players[i].getIsEffectedByGravity());
            Assertions.assertEquals(-23.5,players[i].getVelocityVector().getY());
            Assertions.assertEquals(-9,players[i].getVelocityVector().getX());
            Assertions.assertEquals("\"\"",players[i].getImagePath());
        }
        Assertions.assertEquals(375,players[0].getXPos());
        Assertions.assertEquals(300,players[0].getYPos());
        Assertions.assertEquals(175,players[1].getXPos());
        Assertions.assertEquals(300,players[1].getYPos());
    }
    @Test
    void gameModelBuilderTargetsTest() throws FileNotFoundException {
        GameModel gameModel = GameModelBuilder.getGameModel("src/main/resources/json/levels/testLevel.json");
        CelestialObject[] targets = gameModel.targets;
        Assertions.assertEquals("\"target1\"",targets[0].getName());
        Assertions.assertEquals(786000000000000.0,targets[0].getMass());
        Assertions.assertEquals(15,targets[0].getGeometry().getRadius());
        Assertions.assertFalse(targets[0].getIsEffectedByGravity());
        Assertions.assertEquals(275,targets[0].getPos().getX());
        Assertions.assertEquals(20,targets[0].getPos().getY());
        Assertions.assertEquals(0,targets[0].getVelocityVector().getX());
        Assertions.assertEquals(0,targets[0].getVelocityVector().getY());
        Assertions.assertEquals("\"\"",targets[0].getImagePath());
    }
}

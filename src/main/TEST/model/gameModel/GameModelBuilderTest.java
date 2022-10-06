package model.gameModel;

import com.grupp7.spaceorbit.controllers.Drawable;
import model.modelObjects.CelestialObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import model.gameModel.GameModelBuilder;

import java.io.FileNotFoundException;

public class GameModelBuilderTest {



    @Test
    void gameModelBuilderTest() throws FileNotFoundException {

        GameModel gameModel = GameModelBuilder.getGameModel("src/main/resources/json/levels/testLevel.json");
        //when this string is used there should be the path to level 2 here
        CelestialObject[] planets = gameModel.planets;

        for (int i = 0; i < 4; i++){
            int j = i + 1;
            Assertions.assertEquals("\"planet"+ j +"\"" ,planets[i].getName());
        }
    }




}

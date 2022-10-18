package model.gameModel;

import java.io.FileNotFoundException;

public interface GameModellBuilder {
    GameModel getGameModel(String jsonPath) throws FileNotFoundException;
}

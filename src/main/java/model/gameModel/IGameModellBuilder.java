package model.gameModel;

import java.io.FileNotFoundException;

public interface IGameModellBuilder {
    GameModel getGameModel(String jsonPath) throws FileNotFoundException;
}

package model.gameModel;

import java.io.FileNotFoundException;

public interface IGameModelBuilder {
    GameModel getGameModel(String jsonPath) throws FileNotFoundException;
}

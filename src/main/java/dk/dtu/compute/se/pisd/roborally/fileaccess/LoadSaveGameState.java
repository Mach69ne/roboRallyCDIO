package dk.dtu.compute.se.pisd.roborally.fileaccess;

import dk.dtu.compute.se.pisd.roborally.controller.GameController;

public class LoadSaveGameState
{

    public static void saveGameState(GameController gameController, String name)
    {
        LoadBoard.saveBoard(gameController.board, name);
    }
}

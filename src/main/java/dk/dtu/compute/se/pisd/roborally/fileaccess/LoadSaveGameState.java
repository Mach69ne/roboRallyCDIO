package dk.dtu.compute.se.pisd.roborally.fileaccess;

import dk.dtu.compute.se.pisd.roborally.controller.GameController;
import dk.dtu.compute.se.pisd.roborally.model.Player;

public class LoadSaveGameState
{

    public static void saveGameState(GameController gameController, String name)
    {
        LoadBoard.saveBoard(gameController.board, name);
        for (int i = 0; i < gameController.board.getPlayersNumber(); i++)
        {
            LoadSavePlayer.savePlayer(gameController.board.getPlayer(i), name + i);
        }
    }

    public static GameController loadGameState(String name)
    {
        GameController gameController = new GameController(LoadBoard.loadBoard(name));
        for (int i = 0; i < 10; i++)
        {
            Player player = LoadSavePlayer.loadPlayer(gameController, name + i);
            if (player != null)
            {
                gameController.board.addPlayer(player);
                System.out.println(gameController.board.getPlayersNumber());
            }

        }
        gameController.board.setCurrentPlayer(gameController.board.getPlayer(0));
        //gameController.board.setCurrentPlayer(gameController.board.getPlayer(0));
        return gameController;
    }
}

package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Board;
import dk.dtu.compute.se.pisd.roborally.model.Player;

public class Checkpoint extends NullBoardElement
{

    public Checkpoint(Board board)
    {
        board.addCheckPoint(this);
    }

    @Override
    public void onWalkOver(Player player)
    {
    }
}

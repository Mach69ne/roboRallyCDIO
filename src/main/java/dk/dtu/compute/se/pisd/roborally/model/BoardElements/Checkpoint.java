package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Board;
import dk.dtu.compute.se.pisd.roborally.model.Space;

public class Checkpoint extends NullBoardElement
{
    public Checkpoint(Board board, Space space)
    {
        super(space);
        board.addCheckPoint(this);
    }


}

package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Board;
import dk.dtu.compute.se.pisd.roborally.model.Space;

public class Antenna extends NullBoardElement
{
    public Antenna(Space space)
    {
        super(space);
        space.board.addBoardElement(Board.ANTENNA_INDEX, this);
    }

    @Override
    public void activate()
    {
        //TODO Implement antenna
    }
}

package dk.dtu.compute.se.pisd.roborally.model.BoardElements.Conveyors;

import dk.dtu.compute.se.pisd.roborally.model.Board;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Space;

/**
 * @author
 */
public class BlueConveyor extends Conveyor
{
    BlueConveyor(Heading heading, Space space)
    {
        super(heading, 3, space);
        space.board.addBoardElement(Board.BLUE_CONVEYOR_INDEX, this);
    }
}

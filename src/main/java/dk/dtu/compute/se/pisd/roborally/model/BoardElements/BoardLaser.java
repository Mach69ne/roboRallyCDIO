package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Board;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Space;

/**
 * @author
 */
public class BoardLaser extends BoardElement
{

    public BoardLaser(Space space, Heading heading)
    {
        super(heading, true, space);
        space.board.addBoardElement(Board.BOARD_LASER_INDEX, this);
    }

    /**
     * @author
     */
    @Override
    public void activate()
    {
        Heading headingToCheck = this.getHeading().next().next();
        Space spaceToCheck = this.getSpace();

        while (spaceToCheck != null)
        {
            if (spaceToCheck.getPlayer() != null)
            {
                //TODO Add functionality for getting hit by laser here
            }
            Space nextSpace = spaceToCheck.board.getNeighbour(this.getSpace(), headingToCheck);
            // We check if we were to hit a board element, and break if we do
            if (!spaceToCheck.getBoardElement().getCanWalkOutOf(headingToCheck) || !nextSpace.getBoardElement().getCanWalkInto(headingToCheck))
            {
                break;
            }


            spaceToCheck = nextSpace;
        }

    }

}
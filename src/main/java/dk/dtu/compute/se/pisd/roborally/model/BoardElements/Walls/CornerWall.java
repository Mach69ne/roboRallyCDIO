package dk.dtu.compute.se.pisd.roborally.model.BoardElements.Walls;

import dk.dtu.compute.se.pisd.roborally.model.Board;
import dk.dtu.compute.se.pisd.roborally.model.BoardElements.BoardElement;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Space;

/**
 * @author
 */
public class CornerWall extends BoardElement
{
    private final Heading heading2;

    CornerWall(Heading heading1, Heading heading2, boolean isWalkable, Space space)
    {
        super(heading1, isWalkable, space);
        this.heading2 = heading2;
        space.board.addBoardElement(Board.NOT_ACTIVATE_ABLE_INDEX, this);
    }

    /**
     * @param heading
     * @return
     * @author
     */
    public boolean getCanWalkOutOf(Heading heading)
    {
        return getIsWalkable() && (getHeading() != heading && this.heading2 != heading);
    }

    /**
     * @param heading
     * @return
     * @author
     */
    public boolean getCanWalkInto(Heading heading)
    {
        Heading headingToCheck = heading.next().next();

        return getIsWalkable() && (getHeading() == headingToCheck || this.heading2 == headingToCheck);
    }
}

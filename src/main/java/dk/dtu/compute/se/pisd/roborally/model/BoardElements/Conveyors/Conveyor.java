package dk.dtu.compute.se.pisd.roborally.model.BoardElements.Conveyors;

import dk.dtu.compute.se.pisd.roborally.model.BoardElements.NullBoardElement;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Player;
import dk.dtu.compute.se.pisd.roborally.model.Space;

/**
 * @author
 */
public class Conveyor extends NullBoardElement
{
    private final int moveAmount;
    private Heading orientation;

    Conveyor(Heading orientation, int moveAmount, Space space)
    {
        super(true, space);
        this.orientation = orientation;
        this.moveAmount = moveAmount;
    }

    /**
     * @author
     */
    @Override
    public void activate()
    {
        Player playerToMove = this.getSpace().getPlayer();
        if (playerToMove.getMovedByConveyorThisTurn())
        {
            playerToMove.moveController.movePlayerAmountOfTimesWithHeading(playerToMove, orientation, moveAmount);
        }
        playerToMove.setMovedByConveyorThisTurn(true);
    }

    /**
     * @param heading
     * @author
     */
    public void setHeading(Heading heading)
    {
        this.orientation = heading;
    }

    /**
     * @return the heading of the player
     * @author
     */
    public Heading getHeading()
    {
        return this.orientation;
    }
}

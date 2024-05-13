package dk.dtu.compute.se.pisd.roborally.model.BoardElements.Conveyors;

import dk.dtu.compute.se.pisd.roborally.model.BoardElements.BoardElement;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Player;
import dk.dtu.compute.se.pisd.roborally.model.Space;

/**
 * @author
 */
public abstract class Conveyor extends BoardElement
{
    private final int moveAmount;

    Conveyor(Heading heading, int moveAmount, Space space)
    {
        super(heading, true, space);
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
            playerToMove.moveController.movePlayerAmountOfTimesWithHeading(playerToMove, this.getHeading(), moveAmount);
        }
        playerToMove.setMovedByConveyorThisTurn(true);
    }
}

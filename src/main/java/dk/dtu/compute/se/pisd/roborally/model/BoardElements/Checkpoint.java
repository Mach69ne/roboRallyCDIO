package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Board;
import dk.dtu.compute.se.pisd.roborally.model.Player;
import dk.dtu.compute.se.pisd.roborally.model.Space;

/**
 * @author
 */
public class Checkpoint extends NullBoardElement
{
    public Checkpoint(Space space)
    {
        super(space);
        space.board.addBoardElement(Board.CHECKPOINTS_INDEX, this);
    }

    /**
     * @author
     */
    @Override
    public void activate()
    {
        // Method should already check if checkpoint is visited in the correct order.
        Player playerOnBoardElement = this.getSpace().getPlayer();
        if (playerOnBoardElement != null)
        {
            playerOnBoardElement.addCheckPointAsVisited(this);
        }
    }
}

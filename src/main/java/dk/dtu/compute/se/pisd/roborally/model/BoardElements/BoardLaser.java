package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.*;
import dk.dtu.compute.se.pisd.roborally.model.BoardElements.Walls.Wall;
import javafx.scene.image.Image;

/**
 * @author
 */
public class BoardLaser extends BoardElement
{

    public BoardLaser(Space space, Heading heading)
    {
        super(heading, true, space);
        space.board.addBoardElement(Board.BOARD_LASER_INDEX, this);
        setImage(new Image("file:src/main/resources/images/laserStart.png"));
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
                spaceToCheck.getPlayer().addCardToDiscardPile(new Card(Command.SPAM));
                spaceToCheck.getPlayer().addCardToDiscardPile(new Card(Command.SPAM));
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
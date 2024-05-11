package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Board;
import dk.dtu.compute.se.pisd.roborally.model.Player;
import dk.dtu.compute.se.pisd.roborally.model.Space;
import javafx.scene.image.Image;

/**
 * @author
 */
public class Checkpoint extends NullBoardElement
{
    public Checkpoint(Space space) {
        super(space);
        space.board.addBoardElement(Board.CHECKPOINTS_INDEX, this);
        int index = space.board.getIndexOfCheckPoint(this);
        if (index == 0) {
            setImage(new Image("file:src/main/resources/images/1.png"));
        }
        if (index == 1) {
            setImage(new Image("file:src/main/resources/images/2.png"));
        }
        if (index == 2) {
            setImage(new Image("file:src/main/resources/images/3.png"));
        }
        if (index == 3) {
            setImage(new Image("file:src/main/resources/images/4.png"));
        }
        if (index == 4) {
            setImage(new Image("file:src/main/resources/images/5.png"));
        }
        if (index == 5) {
            setImage(new Image("file:src/main/resources/images/6.png"));
        }
        if (index == 6) {
            setImage(new Image("file:src/main/resources/images/7.png"));
        }
        if (index == 7) {
            setImage(new Image("file:src/main/resources/images/8.png"));
        }
        if (index == 8) {
            setImage(new Image("file:src/main/resources/images/9.png"));
        }
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

package dk.dtu.compute.se.pisd.roborally.model.BoardElements.Walls;

import dk.dtu.compute.se.pisd.roborally.model.Board;
import dk.dtu.compute.se.pisd.roborally.model.BoardElements.BoardElement;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Space;
import javafx.scene.image.Image;

/**
 * @author
 */
public class Wall extends BoardElement
{
    public Wall(Heading heading, boolean isWalkable, Space space)
    {
        super(heading, isWalkable, space);
        space.board.addBoardElement(Board.NOT_ACTIVATE_ABLE_INDEX, this);
        setImage(new Image("file:src/main/resources/images/wall.png"));
    }
}

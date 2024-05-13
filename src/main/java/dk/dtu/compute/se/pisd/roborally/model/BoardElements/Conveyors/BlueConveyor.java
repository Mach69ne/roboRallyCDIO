package dk.dtu.compute.se.pisd.roborally.model.BoardElements.Conveyors;

import dk.dtu.compute.se.pisd.roborally.model.Board;
import dk.dtu.compute.se.pisd.roborally.model.BoardElements.ElementsEnum;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Space;
import javafx.scene.image.Image;

/**
 * @author Adel & Elias
 */
public class BlueConveyor extends Conveyor
{
    public BlueConveyor(Heading heading, Space space)
    {
        super(heading, 3, space);
        space.board.addBoardElement(Board.BLUE_CONVEYOR_INDEX, this);
        this.setType(ElementsEnum.BLUE_CONVEYOR);
        setImage(new Image("file:src/main/resources/images/blue.png"));
    }
}

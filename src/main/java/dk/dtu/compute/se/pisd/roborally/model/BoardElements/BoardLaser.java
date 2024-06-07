package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Board;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Space;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * @author Elias & Mads
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
     * @author Elias & Mads
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
                spaceToCheck.getPlayer().addSpamToDiscard(1);
            }
            Space nextSpace = spaceToCheck.board.getNeighbour(spaceToCheck, headingToCheck);
            // We check if we were to hit a board element, and break if we do
            if (!spaceToCheck.getBoardElement().getCanWalkOutOf(headingToCheck) || nextSpace == null || !nextSpace.getBoardElement().getCanWalkInto(headingToCheck))
            {
                break;
            }
            spaceToCheck = nextSpace;
        }
        try {
            String laserPath = "file:/src/main/resources/sounds/laser_sound.wav";
            Media laserSound = new Media(laserPath);
            MediaPlayer mp = new MediaPlayer(laserSound);
            mp.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
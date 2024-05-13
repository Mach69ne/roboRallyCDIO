package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Player;
import dk.dtu.compute.se.pisd.roborally.model.Space;
import javafx.scene.image.Image;

/**
 * @author
 */
public class RebootToken extends BoardElement
{

    public RebootToken(Heading heading, Space space)
    {
        super(heading, true, space);
        space.board.setRebootToken(this);
        setImage(new Image("file:src/main/resources/images/respawn.png"));
        this.setType(ElementsEnum.REBOOTTOKEN);
    }

    /**
     * @param player
     * @author
     */
    public void reboot(Player player)
    {
        if (this.getSpace().getPlayer() != null)
        {
            this.getSpace().getPlayer().moveController.movePlayerAmountOfTimesWithHeading(player, this.getHeading(), 1);
        }
        this.getSpace().setPlayer(player);
        player.discardAllCardsUponReboot();
        player.setHeading(this.getHeading());
    }
}

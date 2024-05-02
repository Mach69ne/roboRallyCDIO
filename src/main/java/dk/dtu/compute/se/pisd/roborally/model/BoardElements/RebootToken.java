package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Player;
import dk.dtu.compute.se.pisd.roborally.model.Space;

public class RebootToken extends BoardElement
{

    public RebootToken(Heading heading, Space space)
    {
        super(heading, true, space);
        space.board.setRebootToken(this);
    }

    public void reboot(Player player)
    {
        if (this.getSpace().getPlayer() != null)
        {
            this.getSpace().getPlayer().moveController.movePlayerAmountOfTimesWithHeading(player, this.getHeading(), 1);
        }
        this.getSpace().setPlayer(player);
        player.clearRegisters();
        player.setHeading(this.getHeading());
    }
}

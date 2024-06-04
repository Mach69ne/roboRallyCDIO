package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Player;
import dk.dtu.compute.se.pisd.roborally.model.Space;


/**
 * @Author Emil
 */
public class Pit extends NullBoardElement {
    public Pit(Space space) {
        super(space);
    }

    @Override
    public void activate() {
        Player player = this.getSpace().getPlayer();
        if (player != null) {
            this.getSpace().board.getRebootToken().reboot(player);
        }
    }
}

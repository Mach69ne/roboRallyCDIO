package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Board;
import dk.dtu.compute.se.pisd.roborally.model.Space;

/**
 * This class represents an energy cube on the board.
 * When a player lands on a space with an energy cube, the player picks up the energy cube.
 * The energy cube should then be removed from the space.
 * @author Emil
 */

public class EnergyCube extends NullBoardElement {
    public EnergyCube(Space space) {
        super(space);
        space.board.addBoardElement(Board.ENERGY_SPACE_INDEX, this);

    }

    @Override
    public void activate() {
        this.getSpace().getPlayer().pickUpEnergyCube();
        this.getSpace().setBoardElement(null);

    }
}

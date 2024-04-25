package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Space;

public class Gear extends NullBoardElement {
    private final boolean isClockwise;

    public Gear(boolean isWalkable, Space space, boolean isClockwise) {
        super(isWalkable, space);
        this.isClockwise = isClockwise;
    }

    public void activate() {
        if (isClockwise) {
            getSpace().getPlayer().setHeading(getSpace().getPlayer().getHeading().next());
        } else {
            getSpace().getPlayer().setHeading(getSpace().getPlayer().getHeading().prev());
        }
    }
}

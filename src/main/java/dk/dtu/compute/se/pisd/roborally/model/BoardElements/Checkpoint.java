package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Space;

public class Checkpoint extends BoardElement {
    private Space space;
    private int checkpointNumber;

    public Checkpoint(Heading heading, boolean isWalkable,Space space, int checkpointNumber) {
        super(heading, isWalkable);
        this.space = space;
        this.checkpointNumber = checkpointNumber;
    }

    public Space getSpace() {
        return space;
    }

    public int getCheckpointNumber() {
        return checkpointNumber;
    }
    @Override
    public void activate() {
        // TODO implement this
    }
}

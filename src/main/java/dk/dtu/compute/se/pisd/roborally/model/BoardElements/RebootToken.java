package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Space;

public class RebootToken extends BoardElement{
    private Space space;

    public RebootToken(Heading heading, boolean isWalkable, Space space) {
        super(heading, isWalkable, space);
    }

    Space getRebootToken() {
        return this.space;
    }

    void setRebootToken(Space space) {
        this.space = space;
    }

}

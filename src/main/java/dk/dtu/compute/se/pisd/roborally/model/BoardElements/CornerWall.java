package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Heading;

public class CornerWall extends BoardElement {
        private final Heading heading2;
    CornerWall(Heading heading1, Heading heading2, boolean isWalkable) {
        super(heading1, isWalkable);
        this.heading2 = heading2;
    }

    public boolean getCanWalkInto(Heading heading)
    {
        Heading headingToCheck = heading.next().next();

        return getIsWalkable() && (getHeading() == headingToCheck || this.heading2 == headingToCheck);
    }

    public boolean getCanWalkOutOf(Heading heading)
    {
        return getIsWalkable() && (getHeading() != heading && this.heading2 != heading);
    }
}

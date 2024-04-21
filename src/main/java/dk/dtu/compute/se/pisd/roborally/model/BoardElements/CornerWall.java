package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Space;

public class CornerWall extends BoardElement
{
    private final Heading heading2;

    CornerWall(Heading heading1, Heading heading2, boolean isWalkable, Space space)
    {
        super(heading1, isWalkable, space);
        this.heading2 = heading2;
    }

    public boolean getCanWalkOutOf(Heading heading)
    {
        return getIsWalkable() && (getHeading() != heading && this.heading2 != heading);
    }

    public boolean getCanWalkInto(Heading heading)
    {
        Heading headingToCheck = heading.next().next();

        return getIsWalkable() && (getHeading() == headingToCheck || this.heading2 == headingToCheck);
    }
}

package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Heading;

public abstract class BoardElement
{
    private final boolean isWalkable;
    private final Heading heading;
    BoardElement(Heading heading, boolean isWalkable)
    {
        this.isWalkable = isWalkable;
        this.heading = heading;
    }

    public boolean getCanWalkOutOf(Heading heading)
    {
        return this.isWalkable && this.heading != heading;
    }
    public boolean getCanWalkInto(Heading heading)
    {
        Heading headingToCheck = heading.next().next();
        return this.isWalkable && this.heading == headingToCheck;
    }

    public void activate()
    {
        //
    }
    public boolean getIsWalkable()
    {
        return this.isWalkable;
    }
    public Heading getHeading()
    {
        return this.heading;
    }
}

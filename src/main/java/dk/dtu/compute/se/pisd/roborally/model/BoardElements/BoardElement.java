package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Player;
import dk.dtu.compute.se.pisd.roborally.model.Space;

public abstract class BoardElement
{
    private final boolean isWalkable;
    private final Heading heading;
    private final Space space;

    protected BoardElement(Heading heading, boolean isWalkable, Space space)
    {
        this.isWalkable = isWalkable;
        this.heading = heading;
        this.space = space;
        this.space.setBoardElement(this);
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

    public Space getSpace()
    {
        return this.space;
    }

    public void activate()
    {
        //
    }

    public void onWalkOver(Player player)
    {

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

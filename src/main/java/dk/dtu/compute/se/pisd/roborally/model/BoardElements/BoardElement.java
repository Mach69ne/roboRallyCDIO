package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Heading;

public class BoardElement
{
    private final boolean isWalkable;
    private final Heading heading;
    BoardElement(Heading heading, boolean isWalkable)
    {
        this.isWalkable = isWalkable;
        this.heading = heading;
    }

    public boolean getIsWalkable(Heading heading)
    {
        return this.heading == heading && this.isWalkable;
    }

    public void activate()
    {
        //
    }
}

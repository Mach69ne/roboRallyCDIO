package dk.dtu.compute.se.pisd.roborally.model;

public abstract class BoardElement
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

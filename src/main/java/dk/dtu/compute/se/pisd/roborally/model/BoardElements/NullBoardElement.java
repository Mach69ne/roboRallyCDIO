package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Heading;

public class NullBoardElement extends BoardElement
{
    public NullBoardElement()
    {
        super(Heading.NORTH, true);
    }

    public NullBoardElement(boolean isWalkable)
    {
        super(Heading.NORTH, isWalkable);
    }

    @Override
    public boolean getCanWalkOutOf(Heading heading)
    {
        return this.getIsWalkable();
    }

    @Override
    public boolean getCanWalkInto(Heading heading)
    {
        return this.getIsWalkable();
    }
}

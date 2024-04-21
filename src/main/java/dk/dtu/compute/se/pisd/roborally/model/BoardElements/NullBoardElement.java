package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Space;

public class NullBoardElement extends BoardElement
{
    public NullBoardElement(Space space)
    {
        this(true, space);
    }

    public NullBoardElement(boolean isWalkable, Space space)
    {
        super(Heading.NORTH, isWalkable, space);
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

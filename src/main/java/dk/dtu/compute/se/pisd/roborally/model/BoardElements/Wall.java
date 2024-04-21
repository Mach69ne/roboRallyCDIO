package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Space;

public class Wall extends BoardElement
{
    Wall(Heading heading, boolean isWalkable, Space space)
    {
        super(heading, isWalkable, space);
    }
}

package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Heading;

public class Wall extends BoardElement{
    Wall(Heading heading, boolean isWalkable) {
        super(heading, isWalkable);
    }

}

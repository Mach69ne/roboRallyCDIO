package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Player;
import dk.dtu.compute.se.pisd.roborally.model.Space;

public class Checkpoint extends BoardElement {

    public Checkpoint(Heading heading, boolean isWalkable) {
        super(heading, isWalkable);
    }

    @Override
    public void onWalkOver(Player player)
    {

    }
}

package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Space;

public class PushPanel extends BoardElement
{
    private final Heading orientation;

        public PushPanel(Heading orientation, Space space)
        {
            super(orientation, true, space);
            this.orientation = orientation;
        }

        @Override
        public void activate()
        {
            this.getSpace().getPlayer().moveController.movePlayerAmountOfTimesWithHeading(this.getSpace().getPlayer(), orientation, 1);
        }

}

package dk.dtu.compute.se.pisd.roborally.fileaccess.model;

import dk.dtu.compute.se.pisd.roborally.model.BoardElements.ElementsEnum;
import dk.dtu.compute.se.pisd.roborally.model.Heading;

public class BoardElementTemplate
{
    public ElementsEnum type;
    public Heading heading;
    public boolean isWalkable;
    public Heading heading2;
    public boolean isClockwise;
}

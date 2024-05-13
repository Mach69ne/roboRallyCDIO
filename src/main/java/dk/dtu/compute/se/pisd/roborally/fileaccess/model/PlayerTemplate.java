package dk.dtu.compute.se.pisd.roborally.fileaccess.model;

import dk.dtu.compute.se.pisd.roborally.model.Command;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.UpgradeCards;

import java.util.List;

public class PlayerTemplate
{
    public int NO_REGISTERS;
    public int NO_CARDS;
    public List<UpgradeCards> upgradeCards;
    public List<BoardElementTemplate> visitedCheckpoints;
    //Using commands allows me to not create a CardTemplate class
    public List<Command> activeCards;
    public List<Command> discardedCards;

    public String name;
    public String color;
    public Heading heading;
    public SpaceTemplate spaceTemplate;
    public int tabNumber;
    public boolean movedByConveyorThisTurn;
    public int energyCubes;
}

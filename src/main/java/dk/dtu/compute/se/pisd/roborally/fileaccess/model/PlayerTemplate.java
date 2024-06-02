package dk.dtu.compute.se.pisd.roborally.fileaccess.model;

import dk.dtu.compute.se.pisd.roborally.model.Command;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.UpgradeCards;

import java.util.ArrayList;
import java.util.List;

public class PlayerTemplate
{
    public List<UpgradeCards> upgradeCards = new ArrayList<>();
    public List<Integer> visitedCheckpoints = new ArrayList<>();
    //Using commands allows me to not create a CardTemplate class
    public List<Command> activeCards = new ArrayList<>();
    public List<Command> discardedCards = new ArrayList<>();
    public List<Command> registers = new ArrayList<>();
    public List<Command> cardsOnHand = new ArrayList<>();

    public String name;
    public String color;
    public Heading heading;
    public SpaceTemplate spaceTemplate;
    public int tabNumber;
    public boolean movedByConveyorThisTurn;
    public int energyCubes;
    public boolean playersTurn;
}

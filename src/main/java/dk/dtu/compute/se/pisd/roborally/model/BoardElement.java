package dk.dtu.compute.se.pisd.roborally.model;

public class BoardElement
{
    private boolean isWalkable;
    BoardElement(boolean isWalkable)
    {
        this.isWalkable = isWalkable;
    }

    public boolean getIsWalkable()
    {
        return isWalkable;
    }

    public void activate()
    {
        //
    }
}

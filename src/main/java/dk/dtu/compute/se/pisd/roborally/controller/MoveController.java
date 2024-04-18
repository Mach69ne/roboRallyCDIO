package dk.dtu.compute.se.pisd.roborally.controller;

import dk.dtu.compute.se.pisd.roborally.model.Command;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Player;
import dk.dtu.compute.se.pisd.roborally.model.Space;
import org.jetbrains.annotations.NotNull;

public class MoveController
{
    GameController gameController;
    public MoveController(GameController gameController)
    {
        this.gameController = gameController;
    }

    public void executeCommand(@NotNull Player player, Command command)
    {
        if (player.board == gameController.board && command != null)
        {
            // XXX This is a very simplistic way of dealing with some basic cards and
            //     their execution. This should eventually be done in a more elegant way
            //     (this concerns the way cards are modelled as well as the way they are executed).

            switch (command)
            {
                case FORWARD:
                    this.moveForward(player);
                    break;
                case RIGHT:
                    this.turnRight(player);
                    break;
                case LEFT:
                    this.turnLeft(player);
                    break;
                case FAST_FORWARD:
                    this.fastForward(player);
                    break;
                default:
                    // DO NOTHING (for now)
            }
        }
    }

    /**
     * Moves the player one step forward in the direction of the player's heading.
     * @author Adel
     * @param Player the player to be moved
     */
    public void moveForward(@NotNull Player player) {
        Space currentSpace = player.getSpace();
        Heading heading = player.getHeading();
        Space newSpace = gameController.board.getNeighbour(currentSpace, heading);
        player.setSpace(newSpace);
    }

    public void moveFowardRepeatedly(@NotNull Player player, int amountOfMoves)
    {
        for (int i = 0; i < amountOfMoves; i++)
        {
            moveForward(player);
        }
    }

    /**
     * Turns the player to the right by changing the player's heading to the next heading in the enumeration.
     * @param player the player to be turned
     * @author Mustafa
     */
    public void turnRight(@NotNull Player player)
    {
        Heading heading = player.getHeading();
        player.setHeading(heading.next());

    }
    /**
     * Turns the player to the left by changing the player's heading to the previous heading in the enumeration.
     * @param player the player to be turned
     * @author Mustafa
     */
    public void turnLeft(@NotNull Player player)
    {
        Heading heading = player.getHeading();
        player.setHeading(heading.prev());
    }
    /**
     * Moves the player two steps forward in the direction of the player's heading.
     * @param player the player to be moved
     * @author Adel
     */
    public void fastForward(@NotNull Player player)
    {
        moveFowardRepeatedly(player,2);
    }
}

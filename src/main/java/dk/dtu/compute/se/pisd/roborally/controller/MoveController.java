package dk.dtu.compute.se.pisd.roborally.controller;

import dk.dtu.compute.se.pisd.roborally.model.Command;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Player;
import dk.dtu.compute.se.pisd.roborally.model.Space;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class MoveController
{
    GameController gameController;

    /**
     * @param gameController
     * @author
     */
    public MoveController(GameController gameController)
    {
        this.gameController = gameController;
    }

    /**
     * @param player
     * @param command
     * @author
     */
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
                case OPTION_LEFT_RIGHT:
                    this.optionLeftOrRight(player, command);
                    break;
                case SPAM:
                    this.useSpamCard(player);
                    break;
                case TROJAN_HORSE:
                    this.useTrojanHorse(player);
                    break;
                default:
                    throw new RuntimeException("Something went wrong");
            }
        }
    }

    /**
     * @param player
     * @author Mustafa
     */
    public void useTrojanHorse(@NotNull Player player) {
        useSpamCard(player);
        useSpamCard(player);
    }

    /**
     * @param player
     * @author Mustafa
     */
    public void useSpamCard(@NotNull Player player) {
        Heading[] headings = Heading.values();
        Random random = new Random();
        Heading randomHeading = headings[random.nextInt(headings.length)];

        movePlayerAmountOfTimesWithHeading(player, randomHeading, 1);
    }

    /**
     * Moves the player one step forward in the direction of the player's heading.
     *
     * @param player the player to be moved
     * @author Adel
     */
    public void moveForward(@NotNull Player player)
    {
        movePlayerAmountOfTimesWithHeading(player, player.getHeading(), 1);
    }

    /**
     * Turns the player to the right by changing the player's heading to the next heading in the enumeration.
     *
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
     *
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
     *
     * @param player the player to be moved
     * @author Adel
     */
    public void fastForward(@NotNull Player player)
    {
        movePlayerAmountOfTimesWithHeading(player, player.getHeading(), 2);
    }

    /**
     * Turns the player to the left or right depending on the command.
     *
     * @param player  the player to be turned
     * @param command the command to be executed
     * @author Emil
     */
    public void optionLeftOrRight(Player player, Command command)
    {
        if (command == Command.LEFT)
        {
            turnLeft(player);
        }
        else if (command == Command.RIGHT)
        {
            turnRight(player);
        }
    }

    /**
     * @param player
     * @param heading
     * @param amountOfTimesToMove
     * @author
     */
    public void movePlayerAmountOfTimesWithHeading(Player player, Heading heading, int amountOfTimesToMove)
    {
        for (int i = 0; i < amountOfTimesToMove; i++)
        {
            Space currentSpace = player.getSpace();
            Space newSpace = gameController.board.getNeighbour(currentSpace, heading);
            if (currentSpace.getBoardElement().getCanWalkOutOf(heading) && newSpace.getBoardElement().getCanWalkInto(heading))
            {
                //Logic for moving to a space should be put here:
                player.setSpace(newSpace);
                newSpace.getBoardElement().onWalkOver(player);
            }
        }
    }

    /**
     * @param space
     * @author
     */
    public void moveCurrentPlayerToSpace(Space space)
    {
        Player currentPlayer = gameController.board.getCurrentPlayer();
        currentPlayer.setSpace(space);
    }

    /**
     * @author
     */
    class ImpossibleMoveException extends Exception
    {

        private final Player player;
        private final Space space;
        private final Heading heading;

        /**
         * @param player
         * @param space
         * @param heading
         * @author
         */
        public ImpossibleMoveException(Player player, Space space, Heading heading)
        {
            super("Move impossible");
            this.player = player;
            this.space = space;
            this.heading = heading;
        }
    }


}

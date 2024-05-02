/*
 *  This file is part of the initial project provided for the
 *  course "Project in Software Development (02362)" held at
 *  DTU Compute at the Technical University of Denmark.
 *
 *  Copyright (C) 2019, 2020: Ekkart Kindler, ekki@dtu.dk
 *
 *  This software is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; version 2 of the License.
 *
 *  This project is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this project; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */
package dk.dtu.compute.se.pisd.roborally.model;

import dk.dtu.compute.se.pisd.designpatterns.observer.Subject;
import dk.dtu.compute.se.pisd.roborally.model.BoardElements.Antenna;
import dk.dtu.compute.se.pisd.roborally.model.BoardElements.BoardElement;
import dk.dtu.compute.se.pisd.roborally.model.BoardElements.Checkpoint;
import dk.dtu.compute.se.pisd.roborally.model.BoardElements.RebootToken;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static dk.dtu.compute.se.pisd.roborally.model.Phase.INITIALISATION;

/**
 * ...
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 */
public class Board extends Subject
{
    public final static int GREEN_CONVEYOR_INDEX = 0;
    public final static int BLUE_CONVEYOR_INDEX = 1;
    public final static int PUSH_PANELS_INDEX = 2;
    public final static int GEARS_INDEX = 3;
    public final static int BOARD_LASER_INDEX = 4;
    public final static int ROBOT_LASER_INDEX = 5;
    public final static int ENERGY_SPACE_INDEX = 6;
    public final static int CHECKPOINTS_INDEX = 7;
    public final static int ANTENNA_INDEX = 8;
    public final static int NOT_ACTIVATE_ABLE_INDEX = 9;
    public final int width;
    public final int height;
    public final String boardName;
    private final ArrayList<BoardElement>[] boardElements = new ArrayList[10];
    private final Space[][] spaces;
    private final List<Player> players = new ArrayList<>();
    private final boolean toSaveTabNumber = true;
    private RebootToken rebootToken;
    private Integer gameId;
    private Player current;
    private Phase phase = INITIALISATION;
    private int step = 0;
    private boolean stepMode;


    /**
     * @param width  the width of the board
     * @param height the height of the board
     */
    public Board(int width, int height)
    {
        this(width, height, "defaultboard");
    }

    /**
     * @param width     the width of the board
     * @param height    the height of the board
     * @param boardName the name of the board
     *                  (this is used for the name of the file in which the board is stored)
     */

    //In the files given to us by the teacher spaces are stored in an arraylist instead of an array, maybe we should
    // do that is well.
    public Board(int width, int height, @NotNull String boardName)
    {
        this.boardName = boardName;
        this.width = width;
        this.height = height;
        spaces = new Space[width][height];
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                Space space = new Space(this, x, y);
                spaces[x][y] = space;
            }
        }
        this.stepMode = false;
        for (int i = 0; i < boardElements.length; i++)
        {
            boardElements[i] = new ArrayList<BoardElement>();
        }
        spaces[4][4].setBoardElement(new Antenna(spaces[4][4]));
        this.activateBoardElements();
    }

    public void activateBoardElements()
    {
        for (int i = 0; i < boardElements.length; i++)
        {
            for (int k = 0; k < boardElements[i].size(); k++)
            {
                boardElements[i].get(k).activate();
            }
        }
        for (Player player : players)
        {
            player.setMovedByConveyorThisTurn(false);
        }
    }

    public void activateBoardElementsOfIndex(int indexOfElementsToBeActivated)
    {
        for (BoardElement boardElement : boardElements[indexOfElementsToBeActivated])
        {
            boardElement.activate();
        }
    }

    public void addBoardElement(int index, BoardElement boardElement)
    {
        this.boardElements[index].add(boardElement);
    }

    public Position getIndexOfSpace(Space space)
    {
        for (int i = 0; i < this.width; i++)
        {
            for (int k = 0; k < this.height; k++)
            {
                if (this.spaces[i][k] == space)
                {
                    return new Position(i, k);
                }
            }
        }
        return null;
    }

    /**
     * @return the name of the board
     */
    public Integer getGameId()
    {
        return gameId;
    }

    /**
     * @param gameId the id of the game to which this board belongs
     */
    public void setGameId(int gameId)
    {
        if (this.gameId == null)
        {
            this.gameId = gameId;
        }
        else
        {
            if (!this.gameId.equals(gameId))
            {
                throw new IllegalStateException("A game with a set id may not be assigned a new id!");
            }
        }
    }

    public void setPlayers(Player[] playersArr)
    {
        players.clear();
        Collections.addAll(players, playersArr);
        if (this.players.isEmpty())
        {
            return;
        }
        this.setCurrentPlayer(players.get(0));
    }

    public void setTabNumbersOnPlayers()
    {
        for (int i = 0; i < players.size(); i++)
        {
            players.get(i).setTabNumber(i);
        }
    }

    /**
     * @return the number of players on the board
     */
    public int getPlayersNumber()
    {
        return players.size();
    }

    /**
     * @param player the player to be added to the board
     */
    public void addPlayer(@NotNull Player player)
    {
        if (player.board == this && !players.contains(player))
        {
            players.add(player);
            notifyChange();
        }
    }

    /**
     * @param i the index of the player to be returned
     * @return the player with the given index
     */
    public Player getPlayer(int i)
    {
        i %= players.size();
        if (i >= 0 && i < players.size())
        {
            return players.get(i);
        }
        else
        {
            return null;
        }
    }

    /**
     * @return the list of players on the board
     */
    public boolean isStepMode()
    {
        return stepMode;
    }

    /**
     * @param stepMode the step mode to be set
     */
    public void setStepMode(boolean stepMode)
    {
        if (stepMode != this.stepMode)
        {
            this.stepMode = stepMode;
            notifyChange();
        }
    }

    /**
     * @param player the player for which the number should be returned
     * @return the number of the player on the board; -1 if the player is not on the board
     */
    public int getPlayerNumber(@NotNull Player player)
    {
        if (player.board == this)
        {
            return players.indexOf(player);
        }
        else
        {
            return -1;
        }
    }

    /**
     * Returns the neighbour of the given space of the board in the given heading.
     * The neighbour is returned only, if it can be reached from the given space
     * (no walls or obstacles in either of the involved spaces); otherwise,
     * null will be returned.
     *
     * @param space   the space for which the neighbour should be computed
     * @param heading the heading of the neighbour
     * @return the space in the given direction; null if there is no (reachable) neighbour
     */
    public Space getNeighbour(@NotNull Space space, @NotNull Heading heading)
    {
        // TODO needs to be implemented based on the actual spaces
        //      and obstacles and walls placed there. For now it,
        //      just calculates the next space in the respective
        //      direction in a cyclic way.

        // XXX an other option (not for now) would be that null represents a hole
        //     or the edge of the board in which the players can fall


        int x = space.x;
        int y = space.y;

        switch (heading)
        {
            case SOUTH:
                if (y + 1 < height)
                {
                    y = y + 1;
                }
                break;
            case WEST:
                if (x - 1 >= 0)
                {
                    x = x - 1;
                }
                break;
            case NORTH:
                if (y - 1 >= 0)
                {
                    y = y - 1;
                }
                break;
            case EAST:
                if (x + 1 < width)
                {
                    x = x + 1;
                }
                break;
        }

        return getSpace(x, y);
    }

    /**
     * @param x the x-coordinate of the space
     * @param y the y-coordinate of the space
     * @return the space at the given coordinates; null if the coordinates are out of bounds
     */
    public Space getSpace(int x, int y)
    {
        if (x >= 0 && x < width && y >= 0 && y < height)
        {
            return spaces[x][y];
        }
        else
        {
            return null;
        }
    }

    /**
     * @return the list of players on the board
     */
    public String getStatusMessage()
    {
        // This is actually a view aspect, but for making the first task easy for
        // the students, this method gives a string representation of the current
        // status of the game (specifically, it shows the phase, the player and the step)

        return "Phase: " + getPhase().name() + ", Player = " + getCurrentPlayer().getName() + ", Step: " + getStep();

    }

    /**
     * @return the current phase of the board
     */
    public Phase getPhase()
    {
        return phase;
    }

    /**
     * @return the current player
     */
    public Player getCurrentPlayer()
    {
        return current;
    }

    /**
     * @param player the player to be set as the current player
     */
    public void setCurrentPlayer(Player player)
    {
        if (player != this.current && players.contains(player))
        {
            this.current = player;
            notifyChange();
        }
    }

    /**
     * @return the list of players on the board
     */
    public int getStep()
    {
        return step;
    }

    /**
     * @param step the step to be set
     */
    public void setStep(int step)
    {
        if (step != this.step)
        {
            this.step = step;
            notifyChange();
        }
    }

    /**
     * @param phase the phase to be set
     */
    public void setPhase(Phase phase)
    {
        if (phase != this.phase)
        {
            this.phase = phase;
            notifyChange();
        }
    }

    /**
     * @return the list of players on the board
     */
    public void nextPlayerTurn()
    {
        boolean nextPlayerToBe = false;
        for (Player player : players)
        {
            if (nextPlayerToBe)
            {
                this.setCurrentPlayer(player);
                nextPlayerToBe = false;
                break;
            }
            if (player.equals(current))
            {
                nextPlayerToBe = true;
            }
        }
        if (nextPlayerToBe)
        {
            setCurrentPlayer(players.get(0));
        }

    }

    public int getIndexOfCheckPoint(Checkpoint checkpoint)
    {
        return this.boardElements[CHECKPOINTS_INDEX].indexOf(checkpoint);
    }

    /**
     * @return the list of players on the board
     */
    public boolean playerIsLast()
    {
        return players.get(players.size() - 1).equals(current);
    }

    public RebootToken getRebootToken()
    {
        return rebootToken;
    }

    public void setRebootToken(RebootToken rebootToken)
    {
        this.rebootToken = rebootToken;
    }
}

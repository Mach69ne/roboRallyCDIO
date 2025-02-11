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
import dk.dtu.compute.se.pisd.roborally.model.BoardElements.BoardElement;
import dk.dtu.compute.se.pisd.roborally.model.BoardElements.Checkpoint;
import dk.dtu.compute.se.pisd.roborally.model.BoardElements.RebootToken;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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
    private RebootToken[] rebootToken;
    private Integer gameId;
    private Player current;
    private Phase phase = INITIALISATION;
    private int step = 0;
    private boolean stepMode;
    private ArrayList<UpgradeCard> upgradeCards = new ArrayList<>();


    /**
     * @param width  the width of the board
     * @param height the height of the board
     * @author Elias
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
     * @author Elias, Adel & Mads
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
        /*
        spaces[4][4].setBoardElement(new Antenna(spaces[4][4]));
        spaces[3][3].setBoardElement(new CornerWall(Heading.NORTH, Heading.EAST, spaces[3][3]));
        new Checkpoint(spaces[7][7]);
        */
        this.activateBoardElements();

        this.upgradeCards = UpgradeCardsFactory.createUpgradeCards();

    }

    /**
     * @author Elias
     */
    public void activateBoardElements()
    {
        for (int i = 0; i < boardElements.length; i++)
        {
            if (i == Board.ROBOT_LASER_INDEX)
            {
                for (Player player : players)
                {
                    player.shoot();
                }
                continue;
            }
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

    /**
     * @return the number of players on the board
     * @author Elias
     */
    public int getPlayersNumber()
    {
        return players.size();
    }

    public void buyUpgradeCard(Player player, UpgradeCard upgradeCard)
    {
        if (player.getEnergyCubes() >= upgradeCard.getPrice())
        {
            player.addUpgradeCard(upgradeCard);
            this.upgradeCards.remove(upgradeCard);
        }
    }

    public BoardElement getCheckPointAtIndex(int index)
    {
        return boardElements[CHECKPOINTS_INDEX].get(index);
    }

    /**
     * @param indexOfElementsToBeActivated
     * @author Elias
     */
    public void activateBoardElementsOfIndex(int indexOfElementsToBeActivated)
    {
        for (BoardElement boardElement : boardElements[indexOfElementsToBeActivated])
        {
            boardElement.activate();
        }
    }

    /**
     * @param index
     * @param boardElement
     * @author Elias
     */
    public void addBoardElement(int index, BoardElement boardElement)
    {
        this.boardElements[index].add(boardElement);
    }

    /**
     * @param space
     * @return
     * @author Elias
     */
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
     * @author Elias
     */
    public Integer getGameId()
    {
        return gameId;
    }

    /**
     * @param gameId the id of the game to which this board belongs
     * @author Elias
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

    /**
     * @param playersArr, can contain null elements, these are ignored.
     * @author Elias
     */
    public void setPlayers(Player[] playersArr)
    {
        this.players.clear();
        for (Player player : playersArr)
        {
            if (player != null)
            {
                this.players.add(player);
            }
        }
        if (this.players.isEmpty())
        {
            return;
        }
        this.setCurrentPlayer(players.get(0));
    }

    /**
     * @author Elias
     */
    public void setTabNumbersOnPlayers()
    {
        for (int i = 0; i < players.size(); i++)
        {
            players.get(i).setTabNumber(i);
        }
    }

    /**
     * @param player the player to be added to the board
     * @author Elias
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
     * @author Elias
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
     * @author Elias
     */
    public boolean isStepMode()
    {
        return stepMode;
    }

    /**
     * @param stepMode the step mode to be set
     * @author Elias
     */
    public void setStepMode(boolean stepMode)
    {
        if (stepMode != this.stepMode)
        {
            this.stepMode = stepMode;
            notifyChange();
        }
    }

    public ArrayList<BoardElement> getBoardElementsWithIndex(int index)
    {
        return boardElements[index];
    }

    /**
     * @param player the player for which the number should be returned
     * @return the number of the player on the board; -1 if the player is not on the board
     * @author Elias
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
     * @author Elias & Mads
     */
    public Space getNeighbour(@NotNull Space space, @NotNull Heading heading)
    {
        int x = space.x;
        int y = space.y;

        switch (heading)
        {
            case SOUTH:
                y = y + 1;
                break;
            case WEST:
                x = x - 1;
                break;
            case NORTH:
                y = y - 1;
                break;
            case EAST:
                x = x + 1;
                break;
        }

        return getSpace(x, y);
    }

    /**
     * @param x the x-coordinate of the space
     * @param y the y-coordinate of the space
     * @return the space at the given coordinates; null if the coordinates are out of bounds
     * @author Elias
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
     * @author Elias
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
     * @author Elias
     */
    public Phase getPhase()
    {
        return phase;
    }

    /**
     * @return the current player
     * @author Elias
     */
    public Player getCurrentPlayer()
    {
        return current;
    }

    /**
     * @param player the player to be set as the current player
     * @author Elias
     */
    public void setCurrentPlayer(Player player)
    {
        if (player != this.current && players.contains(player))
        {
            if (this.current != null)
            {
                this.current.setThisPlayerTurn(false);
            }
            this.current = player;
            if (player != null)
            {
                player.setThisPlayerTurn(true);
            }
            notifyChange();
        }
    }

    /**
     * @return the list of players on the board
     * @author Elias
     */
    public int getStep()
    {
        return step;
    }

    /**
     * @param step the step to be set
     * @author Elias
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
     * @author Elias
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
     * @author Elias
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

    /**
     * @param checkpoint
     * @return
     * @author Elias
     */
    public int getIndexOfCheckPoint(Checkpoint checkpoint)
    {
        return this.boardElements[CHECKPOINTS_INDEX].indexOf(checkpoint);
    }

    /**
     * @return
     * @author Elias
     */
    public boolean playerIsLast()
    {
        return players.get(players.size() - 1).equals(current);
    }

    /**
     * @return
     * @author Elias
     */
    public RebootToken getRebootToken()
    {
        return rebootToken[0];
    }

    /**
     * @param rebootToken
     * @author Elias
     */
    public void setRebootToken(RebootToken rebootToken)
    {
        this.rebootToken = new RebootToken[]{rebootToken};
    }

    public void deleteBoardElement(BoardElement boardElement)
    {
        for (int i = 0; i < boardElements.length; i++)
        {
            boardElements[i].remove(boardElement);
        }
    }
}
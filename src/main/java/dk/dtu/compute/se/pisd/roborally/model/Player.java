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
import dk.dtu.compute.se.pisd.roborally.controller.MoveController;
import dk.dtu.compute.se.pisd.roborally.model.BoardElements.Checkpoint;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static dk.dtu.compute.se.pisd.roborally.model.Heading.SOUTH;

/**
 * ...
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 */
public class Player extends Subject
{

    final public static int NO_REGISTERS = 5;
    final public static int NO_CARDS = 8;

    final public Board board;
    public final MoveController moveController;
    private final CardField[] program;
    private final CardField[] cards;
    private final ArrayList<Card> activeCardsPile;
    private final ArrayList<Card> discardedCardsPile;
    private ArrayList<Checkpoint> visitedCheckPoints;
    private String name;
    private String color;
    private Space space;
    private Heading heading = SOUTH;
    private int tabNumber;
    private boolean movedByConveyorThisTurn;

    /**
     * @param board the board to which this player belongs
     * @param color the color of the player
     * @param name  the name of the player
     */
    public Player(@NotNull Board board, String color, @NotNull String name, MoveController moveController)
    {
        this.board = board;
        this.name = name;
        this.color = color;
        this.space = null;
        this.moveController = moveController;

        activeCardsPile = new ArrayList<>();
        discardedCardsPile = new ArrayList<>();
        program = new CardField[NO_REGISTERS];
        for (int i = 0; i < program.length; i++)
        {
            program[i] = new CardField(this);
        }

        cards = new CardField[NO_CARDS];
        for (int i = 0; i < cards.length; i++)
        {
            cards[i] = new CardField(this);
        }
    }


    public void die()
    {
        this.board.getRebootToken().reboot(this);
    }

    /**
     * @return the name of the player
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name of the player
     */
    public void setName(String name)
    {
        if (name != null && !name.equals(this.name))
        {
            this.name = name;
            notifyChange();
            if (space != null)
            {
                space.playerChanged();
            }
        }
    }

    /**
     * @return the color of the player
     */
    public String getColor()
    {
        return color;
    }

    /**
     * @param color the color of the player
     */
    public void setColor(String color)
    {
        this.color = color;
        notifyChange();
        if (space != null)
        {
            space.playerChanged();
        }
    }

    /**
     * @return the space on which the player is located
     */
    public Space getSpace()
    {
        return space;
    }

    /**
     * @param space the space on which the player is located
     */
    public void setSpace(Space space)
    {
        Space oldSpace = this.space;
        if (space != oldSpace && (space == null || space.board == this.board))
        {
            this.space = space;
            if (oldSpace != null)
            {
                oldSpace.setPlayer(null);
            }
            if (space != null)
            {
                space.setPlayer(this);
            }
            notifyChange();
        }
    }

    /**
     * @return the heading of the player
     */
    public Heading getHeading()
    {
        return heading;
    }

    /**
     * @param heading the heading of the player
     */
    public void setHeading(@NotNull Heading heading)
    {
        if (heading != this.heading)
        {
            this.heading = heading;
            notifyChange();
            if (space != null)
            {
                space.playerChanged();
            }
        }
    }

    /**
     * @param i the index of the register to be returned
     * @return the register with the given index
     */
    public CardField getProgramField(int i)
    {
        return program[i];
    }

    /**
     * @param i the index of the card field to be returned
     * @return the card field with the given index
     */
    public CardField getCardField(int i)
    {
        return cards[i];
    }

    public void addCheckPointAsVisited(Checkpoint checkpoint)
    {
        int indexOfCheckPoint = board.getIndexOfCheckPoint(checkpoint);
        int wouldBeIndexOfVisitedCheckPoints = visitedCheckPoints.size() - 1;

        if (indexOfCheckPoint == wouldBeIndexOfVisitedCheckPoints)
        {
            visitedCheckPoints.add(checkpoint);
        }
    }


    public int getTabNumber()
    {
        return tabNumber;
    }

    public void setTabNumber(int tabNumber)
    {
        this.tabNumber = tabNumber;
    }

    public boolean getMovedByConveyorThisTurn()
    {
        return movedByConveyorThisTurn;
    }

    public void setMovedByConveyorThisTurn(boolean movedByConveyorThisTurn)
    {
        this.movedByConveyorThisTurn = movedByConveyorThisTurn;
    }

    public void clearRegisters()
    {
        for (int i = board.getStep(); i < program.length; i++)
        {
            program[i] = null;
        }
    }
}

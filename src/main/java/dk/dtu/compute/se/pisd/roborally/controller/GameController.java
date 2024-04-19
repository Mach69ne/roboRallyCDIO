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
package dk.dtu.compute.se.pisd.roborally.controller;

import dk.dtu.compute.se.pisd.roborally.model.*;
import org.jetbrains.annotations.NotNull;

/**
 * ...
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 */
public class GameController
{
    final public Board board;
    final public MoveController moveController;

    public GameController(@NotNull Board board)
    {
        this.board = board;
        this.moveController = new MoveController(this);
    }

    /**
     * Starts Activation phase. This method should be called when the players have pressed finished programming button.
     */
    // XXX: implemented in the current version
    public void finishProgrammingPhase()
    {

        makeProgramFieldsInvisible();
        makeProgramFieldsVisible(0);
        board.setPhase(Phase.ACTIVATION);
        board.setCurrentPlayer(board.getPlayer(0));
        board.setStep(0);
    }

    // XXX: implemented in the current version
    private void makeProgramFieldsInvisible()
    {
        for (int i = 0; i < board.getPlayersNumber(); i++)
        {
            Player player = board.getPlayer(i);
            for (int j = 0; j < Player.NO_REGISTERS; j++)
            {
                CommandCardField field = player.getProgramField(j);
                field.setVisible(false);
            }
        }
    }

    // XXX: implemented in the current version
    private void makeProgramFieldsVisible(int register)
    {
        if (register >= 0 && register < Player.NO_REGISTERS)
        {
            for (int i = 0; i < board.getPlayersNumber(); i++)
            {
                Player player = board.getPlayer(i);
                CommandCardField field = player.getProgramField(register);
                field.setVisible(true);
            }
        }
    }

    /**
     * Executes the registers of the players. This method should be called when the players have pressed the execute
     * registers button.
     *
     * @return void
     */
    // XXX: implemented in the current version
    public void executePrograms()
    {
        board.setStepMode(false);
        continuePrograms();
    }


    /**
     * Continues the execution of the programs of the players. This method should be called when the
     *
     * @return void
     */
    // XXX: implemented in the current version
    public void continuePrograms()
    {
        do
        {
            executeNextStep();
        }
        while (board.getPhase() == Phase.ACTIVATION && !board.isStepMode());
    }

    // XXX: implemented in the current version
    private void executeNextStep()
    {
        Player currentPlayer = board.getCurrentPlayer();
        if (board.getPhase() == Phase.ACTIVATION && currentPlayer != null)
        {
            int step = board.getStep();
            if (step >= 0 && step < Player.NO_REGISTERS)
            {
                CommandCard card = currentPlayer.getProgramField(step).getCard();
                if (card != null)
                {
                    Command command = card.command;
                    moveController.executeCommand(currentPlayer, command);
                }
                int nextPlayerNumber = board.getPlayerNumber(currentPlayer) + 1;
                if (nextPlayerNumber < board.getPlayersNumber())
                {
                    board.setCurrentPlayer(board.getPlayer(nextPlayerNumber));
                }
                else
                {
                    step++;
                    board.activateBoardElements();
                    if (step < Player.NO_REGISTERS)
                    {
                        makeProgramFieldsVisible(step);
                        board.setStep(step);
                        board.setCurrentPlayer(board.getPlayer(0));
                    }
                    else
                    {
                        startProgrammingPhase();
                    }
                }
            }
            else
            {
                // this should not happen
                assert false;
            }
        }
        else
        {
            // this should not happen
            assert false;
        }
    }


    /**
     * Starts the programming phase of the game. This method should be called when the game
     *
     * @return void
     */
    // XXX: implemented in the current version
    public void startProgrammingPhase()
    {
        board.setPhase(Phase.PROGRAMMING);
        board.getPhase();
        board.setCurrentPlayer(board.getPlayer(0));
        board.setStep(0);

        for (int i = 0; i < board.getPlayersNumber(); i++)
        {
            Player player = board.getPlayer(i);
            if (player != null)
            {
                for (int j = 0; j < Player.NO_REGISTERS; j++)
                {
                    CommandCardField field = player.getProgramField(j);
                    field.setCard(null);
                    field.setVisible(true);
                }
                for (int j = 0; j < Player.NO_CARDS; j++)
                {
                    CommandCardField field = player.getCardField(j);
                    field.setCard(generateRandomCommandCard());
                    field.setVisible(true);
                }
            }
        }
    }

    // XXX: implemented in the current version
    private CommandCard generateRandomCommandCard()
    {
        Command[] commands = Command.values();
        int random = (int) (Math.random() * commands.length);
        return new CommandCard(commands[random]);
    }

    // XXX: implemented in the current version
    public void executeStep()
    {
        board.setStepMode(true);
        continuePrograms();
    }

    public boolean moveCards(@NotNull CommandCardField source, @NotNull CommandCardField target)
    {
        CommandCard sourceCard = source.getCard();
        CommandCard targetCard = target.getCard();
        if (sourceCard != null && targetCard == null)
        {
            target.setCard(sourceCard);
            source.setCard(null);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * A method called when no corresponding controller operation is implemented yet. This
     * should eventually be removed.
     */
    public void notImplemented()
    {
        // XXX just for now to indicate that the actual method is not yet implemented
        assert false;
    }

}

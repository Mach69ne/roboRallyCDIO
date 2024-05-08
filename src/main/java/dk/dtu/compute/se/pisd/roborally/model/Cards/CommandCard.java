package dk.dtu.compute.se.pisd.roborally.model.Cards;

import dk.dtu.compute.se.pisd.roborally.controller.MoveController;
import dk.dtu.compute.se.pisd.roborally.model.Card;
import dk.dtu.compute.se.pisd.roborally.model.Command;
import dk.dtu.compute.se.pisd.roborally.model.Player;
import org.jetbrains.annotations.NotNull;

public abstract class CommandCard extends Card {
    /**
     * @param command the command represented by this card
     * @author
     */
    public CommandCard(@NotNull Command command) {
        super(command);
    }

    public abstract void executeCommand(Player player, MoveController moveController);
}

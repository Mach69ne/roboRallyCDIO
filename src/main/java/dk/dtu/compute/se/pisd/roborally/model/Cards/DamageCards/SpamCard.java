package dk.dtu.compute.se.pisd.roborally.model.Cards.DamageCards;

import dk.dtu.compute.se.pisd.roborally.controller.MoveController;
import dk.dtu.compute.se.pisd.roborally.model.Cards.CommandCard;
import dk.dtu.compute.se.pisd.roborally.model.Command;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class SpamCard extends CommandCard {
    /**
     * @param command the command represented by this card
     * @author Mustafa
     */
    public SpamCard(@NotNull Command command) {
        super(command);
    }

    /**
     * @param player
     * @param moveController
     * @author Mustafa
     */
    @Override
    public void executeCommand(Player player, MoveController moveController) {
        Heading[] headings = Heading.values();
        Random random = new Random();
        Heading randomHeading = headings[random.nextInt(headings.length)];

        moveController.movePlayerAmountOfTimesWithHeading(player, randomHeading, 1);
    }
}

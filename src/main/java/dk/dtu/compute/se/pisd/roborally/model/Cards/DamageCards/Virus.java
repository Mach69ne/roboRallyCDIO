package dk.dtu.compute.se.pisd.roborally.model.Cards.DamageCards;

import dk.dtu.compute.se.pisd.roborally.model.Command;
import dk.dtu.compute.se.pisd.roborally.model.CommandCard;
import org.jetbrains.annotations.NotNull;

public class Virus extends CommandCard {

    /**
     * @param command the command represented by this card
     */
    public Virus(@NotNull Command command) {
        super(command);
    }
}

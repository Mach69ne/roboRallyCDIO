package dk.dtu.compute.se.pisd.roborally.model;

import java.util.ArrayList;

/**
 * @author
 */
public class Deck
{
    public final ArrayList<Card> playerCards = new ArrayList<>();

    /**
     * @author
     */
    public Deck()
    {

    }

    public void initializeAPlayerDeck()
    {
        playerCards.clear();
        int amountOfForwards = 5;
        int amountOfRight = 5;
        int amountOfLeft = 5;
        int amountOfFastForward = 5;
        int amountOfOptionLeftOrRight = 5;
        for (int i = 0; i < amountOfForwards; i++)
        {
            playerCards.add(new Card(Command.FORWARD));
        }
        for (int i = 0; i < amountOfRight; i++)
        {
            playerCards.add(new Card(Command.RIGHT));
        }
        for (int i = 0; i < amountOfLeft; i++)
        {
            playerCards.add(new Card(Command.LEFT));
        }
        for (int i = 0; i < amountOfFastForward; i++)
        {
            playerCards.add(new Card(Command.FAST_FORWARD));
        }
        for (int i = 0; i < amountOfOptionLeftOrRight; i++)
        {
            playerCards.add(new Card(Command.OPTION_LEFT_RIGHT));
        }
    }

    /**
     * @param otherDeck
     * @author
     */
    public void shuffleDeckIntoAnotherDeck(Deck otherDeck)
    {
        this.playerCards.addAll(otherDeck.playerCards);
        this.shuffleDeck();
        otherDeck.playerCards.clear();
    }

    /**
     * @author
     */
    public void shuffleDeck()
    {
        for (int i = 0; i < playerCards.size(); i++)
        {
            int randomIndex = (int) (Math.random() * playerCards.size());
            Card tempCard = playerCards.get(randomIndex);
            playerCards.set(randomIndex, playerCards.get(i));
            playerCards.set(i, tempCard);
        }
    }

    /**
     * @return
     * @author
     */
    public Card drawTopCard()
    {
        if (playerCards.isEmpty())
        {
            return null;
        }
        Card tempCard = playerCards.get(0);
        playerCards.remove(0);
        return tempCard;
    }

}

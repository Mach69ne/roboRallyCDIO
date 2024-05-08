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
        //TODO Add different cards for a player to start with.
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
        Card tempCard = playerCards.get(0);
        playerCards.remove(0);
        return tempCard;
    }

}

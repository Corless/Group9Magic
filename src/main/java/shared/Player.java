package shared;

import java.util.List;

public class Player
{
	private String name;
	private Card[] hand;
	private Deck deck;
	
	public Player(String n, Deck d)
	{
		name = n;
		deck = d;
	}
	
	public void playCard(int c)
	{
		int j = 0;
		Card[] temp = new Card[hand.length - 1];
		
		for (int i = 0; i < hand.length; i++)
		{
			if (i != c)
			{
				temp[i-j] = hand[i];
			}
			else
			{
				j = 1;
			}
		}
		hand = temp;
	}
	
	
	/**
	 * Creates the player's hand based on how many mulligans the player has already taken.
	 */
	public void createHand(int maxCards)
	{
		hand = new Card[maxCards];
		
		for (int i = 0; i < maxCards; i++)
		{
			hand[i] = deck.dealCard();
		}
	}
	
	/**
	 * Sets the players hand. This is used when the game is created using an ongoing game's conditions.
	 */
	public void setHand(Card[] hand)
	{
		this.hand = hand;
	}
	
	/**
	 * Output hand in an string array consisting of each card.
	 */
	public String[] outputHand()
	{
		String[] output = new String[hand.length];
		
		for (int i = 0; i < hand.length; i++)
		{
			output[i] = hand[i].outputCard();
		}
		
		return output;
	}
	
	/**
	 * Get the player's current hand.
	 */
	public Card[] getHand()
	{
		return hand;
	}
}

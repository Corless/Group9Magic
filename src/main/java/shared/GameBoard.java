package shared;

import java.util.List;

public class GameBoard
{
	private List<Card> playerLands;
	private List<Card> opponentLands;
	private List<Card> playerCreatures;
	private List<Card> opponentCreatures;
	private List<Card> playerGraveyard;
	private List<Card> opponentGraveyard;


	public GameBoard() 
	{
		
	}
	
	// Mutator methods. These methods allow us to set the game's conditions
	
	public void setPlayerLands(List<Card> playerLands)
	{
		this.playerLands = playerLands;
	}
	
	public void setOpponentLands(List<Card> opponentLands)
	{
		this.opponentLands = opponentLands;
	}

	public void setPlayerCreatures(List<Card> playerCreatures)
	{
		this.playerCreatures = playerCreatures;
	}
	
	public void setOpponentCreatures(List<Card> opponentCreatures)
	{
		this.opponentCreatures = opponentCreatures;
	}
	
	public void setPlayerGraveyard(List<Card> playerGraveyard)
	{
		this.playerGraveyard = playerGraveyard;
	}
	
	public void setOpponentGraveyard(List<Card> opponentGraveyard)
	{
		this.opponentGraveyard = opponentGraveyard;
	}
	
	// Getter Methods. These methods will output the Card list to a string that the user can read.
	
	public String[] getPlayerLands()
	{
		return this.outputCards(playerLands);
	}
	
	public String[] getOpponentLands()
	{
		return this.outputCards(opponentLands);
	}

	public String[] getPlayerCreatures()
	{
		return this.outputCards(playerCreatures);
	}

	public String[] getOpponentCreatures()
	{
		return this.outputCards(opponentCreatures);
	}

	public String[] getPlayerGraveyard()
	{
		return this.outputCards(playerGraveyard);
	}

	public String[] getOpponentGraveyard()
	{
		return this.outputCards(opponentGraveyard);
	}



	
	/**
	 * Output list of cards to a string the user can read.
	 */
	public String[] outputCards(List<Card> c)
	{
		Card[] cards = new Card[c.size()];
		cards = c.toArray(cards);
		String[] output = new String[cards.length];
		
		for (int i = 0; i < cards.length; i++)
		{
			output[i] = cards[i].outputCard();
		}
			
		return output;
	}
}

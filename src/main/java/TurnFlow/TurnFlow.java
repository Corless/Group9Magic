package TurnFlow;

import shared.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Mulligan.Mulligan;

public class TurnFlow
{
	private static GameBoard game;
	
	private Deck deck = new Deck();
	private static List<Card> playerLands;
	private static List<Card> opponentLands;
	private static List<Card> playerCreatures;
	private static List<Card> opponentCreatures;
	//private List<Card> playerGraveyard; not needed for prototype
	//private List<Card> opponentGraveyard; not needed for prototype
	private static Player user;
	private Player opponent;
	
	private Card akroanJailer;
	private Card yokedOx;
	private static Card plains;
	private Card celestialFlare;
	private Card guideHartebeest;
	private Card maritimeGuard;
	private Card island;
	
	private static int white;
	private static int any;
	
	TurnFlow()
	{
		makeCards();
		makeDeck();
		initializeGameState();
		
		user = new Player("user", deck);
		opponent = new Player("opponent", deck);
		user.setHand(new Card[]{maritimeGuard, akroanJailer, yokedOx, maritimeGuard, plains});
		opponent.setHand(new Card[]{island, plains, akroanJailer, celestialFlare});
	}
	
	private void makeCards()
	{
		akroanJailer = new Card("Akroan Jailer", new int[]{1}, new String[]{"white"}, "Creature", 1, 1);
		yokedOx = new Card("Yoked Ox", new int[]{1}, new String[]{"white"}, "Creature", 0, 4);
		plains = new Card("Plains", "Land");
		celestialFlare = new Card("Celestial Flare", new int[]{2}, new String[]{"white"}, "Instant", "Target Player sacrifices an attacking or blocking creature.");
		guideHartebeest = new Card("Totem-Guide HarteBeest", new int[]{4,1}, new String[]{"any", "white"}, "Creature", 2, 5);
		maritimeGuard = new Card("Maritime Guard", new int[]{1,1}, new String[]{"any", "white"}, "Creature", 1, 3);
		island = new Card("Island", "Land");
	}
	
	private void initializeGameState()
	{
		playerLands = new ArrayList<Card>(Arrays.asList(island, plains));
		white = 1;
		any = 2; 
		opponentLands = new ArrayList<Card>(Arrays.asList(island, plains, plains));
		playerCreatures = new ArrayList<Card>(Arrays.asList(maritimeGuard, yokedOx));
		opponentCreatures = new ArrayList<Card>(Arrays.asList(akroanJailer, yokedOx));
	}
	
	private void makeDeck()
	{
		// add 4 of each land
		for (int i = 0; i < 4; i++)
		{
			deck.addCard(plains);
			deck.addCard(island);	
		}
		
		// add 2 of all other cards
		for (int i = 0; i < 2; i++)
		{
			deck.addCard(akroanJailer);
			deck.addCard(yokedOx);
			deck.addCard(celestialFlare);
			deck.addCard(guideHartebeest);
			deck.addCard(maritimeGuard);
		}

		deck.shuffle();
	}
	
	public static String[] outputCards(List<Card> c)
	{
		Card[] cards = (Card[]) c.toArray();
		String[] output = new String[cards.length];
		
		for (int i = 0; i < cards.length; i++)
		{
			output[i] = cards[i].outputCard();
		}
		
		return output;
	}
	
	public static void main(String arg[])
	{
		Scanner reader = new Scanner(System.in);
		int inputInt;
		boolean done = false;
		new TurnFlow();
		
		game = new GameBoard();
		game.setPlayerLands(playerLands);
		game.setOpponentLands(opponentLands);
		game.setPlayerCreatures(playerCreatures);
		game.setOpponentCreatures(opponentCreatures);
		
		System.out.println("You untap all tapped cards.");
		System.out.println("You draw: Plains(Land)");
		System.out.println();
		
		System.out.println("Your current creatures are: ");
		for (String card : game.getPlayerCreatures())
		{
			System.out.println(card);
		}
		System.out.println();
		
		System.out.println("Your opponent's creatures are: ");
		for (String card : game.getOpponentCreatures())
		{
			System.out.println(card);
		}
		System.out.println();
		
		System.out.println("Your hand is: ");
		for (String card : user.outputHand())
		{
			System.out.println(card);
		}
		System.out.println();
		
		System.out.println("Would you like to play your land card? (Enter '1' for Yes.)");
		inputInt = reader.nextInt();
		if (inputInt == 1)
		{
			playerLands.add(plains);
			System.out.println("You play Plains(Land). Your current untapped lands are now: ");
			for (String card : game.getPlayerLands())
			{
				System.out.println(card);
			}
			
			System.out.println();
			for (String card : user.outputHand())
			{
				System.out.println(card);
			}
			white++;
			System.out.println();
		}		
		user.playCard(4);
		
		System.out.println("Which card would you like to play? ");
		for (int i = 0; i < user.outputHand().length; i++)
		{
			System.out.println("Press '" + i + "' to play: " + user.getHand()[i].name);
		}
		inputInt = reader.nextInt();
		
		while(!done)
		{
			if (inputInt == 0)
			{
				if (white >= 1 && any >= 2)
				{
					white = white - 1;
					any = any - 2;
					System.out.println("Maritime Guard has been played and now has ressurection sickness.");
				}
				else
				{
					System.out.println("Maritime Guard cannot be played because you do not have enough untapped land cards." 
							+ "You need 1 Plains(white) basic land card and 1 of any basic land in order to play this card.");
				}
			}
			if (inputInt == 1)
			{
				if (white >= 1)
				{
					white = white - 1;
					System.out.println("Akroan Jailer has been played and now has ressurection sickness.");
				}
				else
				{
					System.out.println("Akroan Jailer cannot be played because you do not have enough untapped land cards." 
							+ "You need 1 Plains(white) basic land card in order to play this card.");
				}
			}
			if (inputInt == 2)
			{
				if (white >= 1)
				{
					white = white - 1;
					System.out.println("Yoked Ox has been played and now has ressurection sickness.");
				}
				else
				{
					System.out.println("Yoked Ox cannot be played because you do not have enough untapped land cards." 
							+ "You need 1 Plains(white) basic land card in order to play this card.");
				}
			}
			if (inputInt == 3)
			{
				if (white >= 1 && any >= 2)
				{
					white = white - 1;
					any = any - 2;
					System.out.println("Maritime Guard has been played and now has ressurection sickness.");
				}
				else
				{
					System.out.println("Maritime Guard cannot be played because you do not have enough untapped land cards." 
							+ "You need 1 Plains(white) basic land card and 1 of any basic land in order to play this card.");
				}
			}	
			
			System.out.println("Would you like to play another card? (Enter '1' to continue phase.)");
			inputInt = reader.nextInt();
			if (inputInt == 0)
			{
				done = true;
			}
			else
			{
				System.out.println("You have: " + white + " untapped white mana remaining and " + 
						(any-white) + " other mana still untapped this turn.");
			}
			
		}
		
	}
	
}







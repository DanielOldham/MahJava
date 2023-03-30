import	java.util.*;

/**
 * TileDeck.
 * Represents the deck of all tiles located in a standard game of MahJong.
 */
public class TileDeck
{
	/**
	 * ArrayList to hold all tiles.
	 */
	private	ArrayList<Tile>	deck = new ArrayList<>(144);

	/**
	 * Game number.
	 */
	public long gameNum;

	/**
	 * Constructor.
	 * Adds correct number of each type of tile to the array list.
	 */
	public TileDeck()
	{
		for (int i = 0; i < 4; i++)
		{
			// CharacterTiles
			for (char c = '1'; c <= '9'; c++)
				deck.add(new CharacterTile(c));

			deck.add(new CharacterTile('N'));
			deck.add(new CharacterTile('E'));
			deck.add(new CharacterTile('W'));
			deck.add(new CharacterTile('S'));
			deck.add(new CharacterTile('C'));
			deck.add(new CharacterTile('F'));

			// CircleTiles
			for (int j = 1; j < 10; j++)
				deck.add(new CircleTile(j));

			// BambooTiles
			for (int j = 2; j < 10; j++)
				deck.add(new BambooTile(j));

			// Special Tiles
			deck.add(new WhiteDragonTile());
			deck.add(new Bamboo1Tile());
		}

		// FlowerTiles
		deck.add(new FlowerTile("Chrysanthemum"));
		deck.add(new FlowerTile("Orchid"));
		deck.add(new FlowerTile("Plum"));
		deck.add(new FlowerTile("Bamboo"));

		// SeasonTiles
		deck.add(new SeasonTile("Spring"));
		deck.add(new SeasonTile("Summer"));
		deck.add(new SeasonTile("Fall"));
		deck.add(new SeasonTile("Winter"));
	}

	/**
	 * Shuffles the deck array list randomly.
	 */
	public void shuffle()
	{
		gameNum = System.currentTimeMillis() % 1000000;
		Collections.shuffle(deck, new Random(gameNum));
	}

	/**
	 * Shuffles the deck array list using the given seed.
	 * @param gameNumber seed to randomize array list with
	 */
	public void shuffle(long gameNumber)
	{
		gameNum = gameNumber;
		Collections.shuffle(deck, new Random(gameNum));
	}

	/**
	 * Removes the tile at the end of the array list and returns it.
	 * @return next tile in the deck
	 */
	public Tile deal()
	{
		return deck.remove(deck.size() - 1);
	}
}


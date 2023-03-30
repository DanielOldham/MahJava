/**
 * Bamboo1Tile class.
 * Represents the 1st bamboo tile.
 * Extends PictureTile.
 */
public class Bamboo1Tile extends PictureTile
{
	/**
	 * Constructor.
	 * Calls superclass constructor and setToolTipText.
	 */
	public Bamboo1Tile()
	{
		super("Sparrow");
		setToolTipText(toString());
	}

	/**
	 * toString override.
	 * @return the name of the tile as a String
	 */
	public String toString()
	{
		return "Bamboo 1";
	}
}


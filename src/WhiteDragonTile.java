import java.awt.*;

/**
 * WhiteDragonTile class.
 * Represents the MahJong white dragon tile.
 * Extends Tile.
 */
public class WhiteDragonTile extends Tile
{
	/**
	 * Constructor.
	 */
	public WhiteDragonTile(){setToolTipText("White Dragon");}

	/**
	 * toString override.
	 * @return
	 */
	public String toString()
	{
		return "White Dragon";
	}

	/**
	 * paintComponent override.
	 * Paints the blue and white rectangle for the white dragon tile.
	 * @param g
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.BLUE);
		// many rectangles
		// top
		g.fillRect(40, 30, 10, 10);
		g.fillRect(60, 30, 10, 10);
		g.fillRect(80, 30, 10, 10);
		g.fillRect(100, 30, 10, 10);
		// right
		g.fillRect(100, 50, 10, 10);
		g.fillRect(100, 70, 10, 10);
		g.fillRect(100, 90, 10, 10);
		// bottom
		g.fillRect(30, 100, 10, 10);
		g.fillRect(50, 100, 10, 10);
		g.fillRect(70, 100, 10, 10);
		g.fillRect(90, 100, 10, 10);
		// left
		g.fillRect(30, 40, 10, 10);
		g.fillRect(30, 60, 10, 10);
		g.fillRect(30, 80, 10, 10);

		// outer and inner border
		g.setColor(Color.BLACK);
		g.drawRect(30, 30, 80, 80);
		g.drawRect(40, 40, 60, 60);
	}
}


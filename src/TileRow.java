import java.util.ArrayList;

/**
 * TileRow class.
 * Represents one row of tiles in MahJong.
 * Extends ArrayList of Tile.
 */
public class TileRow extends ArrayList<Tile> {
    /**
     * Number of tiles.
     */
    protected int numTiles;

    /**
     * Constructor.
     * @param numTiles
     */
    public TileRow(int numTiles)
    {
        this.numTiles = numTiles;
    }
}

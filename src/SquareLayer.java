/**
 * SquareLayer class.
 * Represents a tile layer with the same length and width of tiles.
 * Extends TileLayer.
 */
public class SquareLayer extends TileLayer {
    /**
     * Constructor.
     * Adds TileRow objects to the current layer.
     * @param numRows number of rows (and columns)
     */
    public SquareLayer(int numRows)
    {
        super(numRows);

        // there are the same number of tiles (columns) in each row as there are rows
        for (int i = 0; i < numRows; i++) {
            add(new TileRow(numRows));
        }
    }
}

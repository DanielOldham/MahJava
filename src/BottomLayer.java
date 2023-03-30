/**
 * BottomLayer class.
 * Represents the bottom layer of the board.
 * Extends TileLayer.
 */
public class BottomLayer extends TileLayer {

    /**
     * Constructor.
     * @param numRows Number of rows
     */
    public BottomLayer(int numRows)
    {
        super(numRows);

        // rows must be hardcoded
        add(new TileRow(12)); // row 0
        add(new TileRow(8)); // row 1
        add(new TileRow(10)); // row 2
        add(new TileRow(13)); // row 3
        add(new TileRow(14)); // row 4
        add(new TileRow(10)); // row 5
        add(new TileRow(8)); // row 6
        add(new TileRow(12)); // row 7

    }
}

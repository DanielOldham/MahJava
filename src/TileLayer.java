import java.util.ArrayList;

/**
 * TileLayer abstract class.
 * Represents one layer of tiles in MahJong.
 * Extends ArrayList of TileRow.
 */
public abstract class TileLayer extends ArrayList<TileRow> {
    /**
     * Number of rows in the layer.
     */
    protected int numRows;

    /**
     * Constructor.
     * @param numRows
     */
    public TileLayer(int numRows)
    {
        this.numRows = numRows;
    }
}
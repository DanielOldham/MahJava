/**
 * SeasonTile class.
 * Represents the season tiles, which can all match with each other.
 * Extends PictureTile.
 */
public class SeasonTile extends PictureTile {
    /**
     * Constructor.
     * @param name
     */
    public SeasonTile(String name){
        super(name);
        setToolTipText(toString());
    }
}

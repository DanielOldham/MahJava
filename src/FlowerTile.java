/**
 * FlowerTile class.
 * Extends PictureTile.
 */
public class FlowerTile extends PictureTile
{
    /**
     * Constructor.
     * @param name
     */
    public FlowerTile(String name)
    {
        super(name);
        setToolTipText(toString());
    }
}

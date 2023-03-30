import java.awt.*;

/**
 * RankTile class.
 * Represents all tiles that have a number rank.
 * Designed to be overridden by other tile classes such as CircleTile.
 * Extends Tile.
 */
public class RankTile extends Tile {
    /**
     * Number rank of tile.
     */
    protected int rank;

    /**
     * Constructor.
     * @param rank
     */
    public RankTile(int rank)
    {
        this.rank = rank;
    }

    /**
     * Overridden matches method.
     * Checks to see if this tile matches other given tile based on the rank.
     * @param other second Tile object
     * @return true if the two tiles match, false otherwise
     */
    public boolean matches(Tile other)
    {
        return super.matches(other) && this.rank == ((RankTile)other).rank;
    }

    /**
     * PaintComponent method override.
     * @param g
     */
    public void paintComponent(Graphics g){super.paintComponent(g);}
}

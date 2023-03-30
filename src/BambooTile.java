import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * BambooTile class.
 * Represents all ranks of bamboo tile except rank 1.
 * Extends RankTile.
 */
public class BambooTile extends RankTile {
    /**
     * Array to hold visual bamboos on the tile.
     */
    private Bamboo[] bamboos;

    /**
     * Constructor.
     * Fills up bamboo array depending on the rank of the tile.
     * @param rank Rank of the tile
     */
    public BambooTile(int rank)
    {
        super(rank);
        bamboos = new Bamboo[rank];

        // fill out bamboo array with bamboo objects
        switch (rank)
        {
            case 2 -> {
                bamboos[0] = new Bamboo(65, 35, Color.BLUE);
                bamboos[1] = new Bamboo(65, 75, Color.GREEN);
            }
            case 3 -> {
                bamboos[0] = new Bamboo(65, 35, Color.BLUE);
                bamboos[1] = new Bamboo(45, 75, Color.GREEN);
                bamboos[2] = new Bamboo(85, 75, Color.GREEN);
            }
            case 4 -> {
                bamboos[0] = new Bamboo(45, 35, Color.BLUE);
                bamboos[1] = new Bamboo(85, 35, Color.GREEN);
                bamboos[2] = new Bamboo(45, 75, Color.GREEN);
                bamboos[3] = new Bamboo(85, 75, Color.BLUE);
            }
            case 5 -> {
                bamboos[0] = new Bamboo(30, 35, Color.BLUE);
                bamboos[1] = new Bamboo(100, 35, Color.GREEN);
                bamboos[2] = new Bamboo(65, 55, Color.RED);
                bamboos[3] = new Bamboo(30, 75, Color.GREEN);
                bamboos[4] = new Bamboo(100, 75, Color.BLUE);
            }
            case 6 -> {
                bamboos[0] = new Bamboo(30, 35, Color.GREEN);
                bamboos[1] = new Bamboo(65, 35, Color.GREEN);
                bamboos[2] = new Bamboo(100, 35, Color.GREEN);
                bamboos[3] = new Bamboo(30, 75, Color.BLUE);
                bamboos[4] = new Bamboo(65, 75, Color.BLUE);
                bamboos[5] = new Bamboo(100, 75, Color.BLUE);
            }
            case 7 -> {
                bamboos[0] = new Bamboo(65, 22, Color.RED);
                bamboos[1] = new Bamboo(30,  55, Color.GREEN);
                bamboos[2] = new Bamboo(65, 55, Color.GREEN);
                bamboos[3] = new Bamboo(100, 55, Color.GREEN);
                bamboos[4] = new Bamboo(30,  88, Color.BLUE);
                bamboos[5] = new Bamboo(65, 88, Color.BLUE);
                bamboos[6] = new Bamboo(100, 88, Color.BLUE);
            }
            case 8 -> {
                bamboos[0] = new Bamboo(30,  22, Color.GREEN);
                bamboos[1] = new Bamboo(65, 22, Color.GREEN);
                bamboos[2] = new Bamboo(100, 22, Color.GREEN);
                bamboos[3] = new Bamboo(45, 55, Color.RED);
                bamboos[4] = new Bamboo(85, 55, Color.RED);
                bamboos[5] = new Bamboo(30,  88, Color.BLUE);
                bamboos[6] = new Bamboo(65, 88, Color.BLUE);
                bamboos[7] = new Bamboo(100, 88, Color.BLUE);
            }
            case 9 -> {
                bamboos[0] = new Bamboo(30,  22, Color.RED);
                bamboos[1] = new Bamboo(65, 22, Color.BLUE);
                bamboos[2] = new Bamboo(100, 22, Color.GREEN);
                bamboos[3] = new Bamboo(30,  55, Color.RED);
                bamboos[4] = new Bamboo(65, 55, Color.BLUE);
                bamboos[5] = new Bamboo(100, 55, Color.GREEN);
                bamboos[6] = new Bamboo(30,  88, Color.RED);
                bamboos[7] = new Bamboo(65, 88, Color.BLUE);
                bamboos[8] = new Bamboo(100, 88, Color.GREEN);
            }
        }
        setToolTipText(toString());
    }

    /**
     * toString override.
     * Will be called using the tool tip.
     * @return the name of the tile as a String
     */
    public String toString(){return "Bamboo " + super.rank;}

    /**
     * paintComponent override.
     * Calls draw function on each bamboo in the array.
     * @param g Graphics object
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        for (Bamboo b : bamboos) {
            if (b != null)
                b.draw(g);
        }
    }

    /**
     * Bamboo inner class.
     * Provides functionality for individual bamboo shapes.
     */
    private class Bamboo
    {
        /**
         * Height of bamboo.
         */
        int height;

        /**
         * Width of bamboo.
         */
        int width;

        /**
         * X coordinate of bamboo.
         */
        private int x;

        /**
         * Y coordinate of bamboo.
         */
        private int y;

        /**
         * Color of bamboo.
         */
        private Color color;

        /**
         * Constructor.
         * @param x
         * @param y
         * @param color
         */
        public Bamboo(int x, int y, Color color)
        {
            this.x = x;
            this.y = y;
            this.color = color;
            this.height = 30;
            this.width = 10;
        }

        /**
         * Draws the individual bamboo.
         * @param g
         */
        public void draw(Graphics g)
        {
            g.setColor(color);
            g.fillRect(x, y, width, height);

            // make bamboo hollow
            g.setColor(Color.WHITE);
            g.fillRect(x + 3, y + 3, width - 6, height - 6);

            g.setColor(color);
            g.fillRoundRect(x - 5, y, 20, 4, 4, 4);
            g.fillRoundRect(x - 5, y + 13, 20, 4, 4, 4);
            g.fillRoundRect(x - 5, y + 26, 20, 4, 4, 4);
        }
    }
}
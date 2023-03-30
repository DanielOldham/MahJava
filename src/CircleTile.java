import java.awt.*;

/**
 * CircleTile class.
 * Represents all ranks of circle tile.
 * Extends RankTile.
 */
public class CircleTile extends RankTile {
    /**
     * Array of all Circle objects to be painted on the tile.
     */
    private Circle[] circles;

    /**
     * Constructor.
     * @param rank Rank of the tile
     */
    public CircleTile(int rank)
    {
        super(rank);
        circles = new Circle[rank];

        // fill out circle array with circles based on the number
        int diameter;

        // hardcode position of circles based on rank
        switch (super.rank) {
            case 1 -> {
                circles[0] = new Pancake();
            }
            case 2 -> {
                diameter = 30;
                circles[0] = new Circle(diameter, 50, 30, Color.GREEN);
                circles[1] = new Circle(diameter, 50, 80, Color.RED);
            }
            case 3 -> {
                diameter = 20;
                circles[0] = new Circle(diameter, 30, 30, Color.BLUE);
                circles[1] = new Circle(diameter, 60, 60, Color.RED);
                circles[2] = new Circle(diameter, 90, 90, Color.GREEN);
            }
            case 4 -> {
                diameter = 30;
                circles[0] = new Circle(diameter, 30, 30, Color.BLUE);
                circles[1] = new Circle(diameter, 80, 30, Color.GREEN);
                circles[2] = new Circle(diameter, 30, 80, Color.GREEN);
                circles[3] = new Circle(diameter, 80, 80, Color.BLUE);
            }
            case 5 -> {
                diameter = 20;
                circles[0] = new Circle(diameter, 30, 30, Color.BLUE);
                circles[1] = new Circle(diameter, 90, 30, Color.GREEN);
                circles[2] = new Circle(diameter, 30, 90, Color.GREEN);
                circles[3] = new Circle(diameter, 90, 90, Color.BLUE);
                circles[4] = new Circle(diameter, 60, 60, Color.RED);
            }
            case 6 -> {
                diameter = 20;
                circles[0] = new Circle(diameter, 40, 30, Color.GREEN);
                circles[1] = new Circle(diameter, 80, 30, Color.GREEN);
                circles[2] = new Circle(diameter, 40, 60, Color.RED);
                circles[3] = new Circle(diameter, 80, 60, Color.RED);
                circles[4] = new Circle(diameter, 40, 90, Color.RED);
                circles[5] = new Circle(diameter, 80, 90, Color.RED);
            }
            case 7 -> {
                diameter = 20;
                circles[0] = new Circle(diameter, 25, 25, Color.GREEN);
                circles[1] = new Circle(diameter, 60, 35, Color.GREEN);
                circles[2] = new Circle(diameter, 95, 45, Color.GREEN);
                circles[3] = new Circle(diameter, 40, 70, Color.RED);
                circles[4] = new Circle(diameter, 80, 70, Color.RED);
                circles[5] = new Circle(diameter, 40, 95, Color.RED);
                circles[6] = new Circle(diameter, 80, 95, Color.RED);
            }
            case 8 -> {
                diameter = 15;
                circles[0] = new Circle(diameter, 45, 25, Color.BLUE);
                circles[1] = new Circle(diameter, 80, 25, Color.BLUE);
                circles[2] = new Circle(diameter, 45, 50, Color.BLUE);
                circles[3] = new Circle(diameter, 80, 50, Color.BLUE);
                circles[4] = new Circle(diameter, 45, 75, Color.BLUE);
                circles[5] = new Circle(diameter, 80, 75, Color.BLUE);
                circles[6] = new Circle(diameter, 45, 100, Color.BLUE);
                circles[7] = new Circle(diameter, 80, 100, Color.BLUE);
            }
            case 9 -> {
                diameter = 20;
                circles[0] = new Circle(diameter, 30, 30, Color.GREEN);
                circles[1] = new Circle(diameter, 60, 30, Color.GREEN);
                circles[2] = new Circle(diameter, 90, 30, Color.GREEN);
                circles[3] = new Circle(diameter, 30, 60, Color.RED);
                circles[4] = new Circle(diameter, 60, 60, Color.RED);
                circles[5] = new Circle(diameter, 90, 60, Color.RED);
                circles[6] = new Circle(diameter, 30, 90, Color.BLUE);
                circles[7] = new Circle(diameter, 60, 90, Color.BLUE);
                circles[8] = new Circle(diameter, 90, 90, Color.BLUE);
            }
        }
        setToolTipText(toString());
    }

    /**
     * toString override.
     * Will be called using the tool tip.
     * @return
     */
    public String toString(){return "Circle " + super.rank;}

    /**
     * paintComponent override.
     * Calls the draw method on all circles for this tile.
     * @param g
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        for (Circle c : circles) {
            if (c != null)
                c.draw(g);
        }
    }

    /**
     * Circle inner class.
     * Provides functionality for individual circle shapes.
     */
    protected class Circle {
        /**
         * Diameter of the circle.
         */
        private int diameter;

        /**
         * X coordinate.
         */
        private int x;

        /**
         * Y coordinate.
         */
        private int y;

        /**
         * Color of circle.
         */
        private Color color;

        /**
         * Constructor.
         * @param diameter
         * @param x
         * @param y
         * @param color
         */
        public Circle(int diameter, int x, int y, Color color)
        {
            this.diameter = diameter;
            this.x = x;
            this.y = y;
            this.color = color;
        }

        /**
         * Draws one individual circle.
         * @param g
         */
        public void draw(Graphics g)
        {
            Stroke stroke = new BasicStroke(2f);
            Graphics2D g2d = (Graphics2D)g;

            // circle
            g2d.setColor(color);
            g2d.fillOval(x, y, diameter, diameter);

            // inner circle
            g2d.setColor(Color.WHITE);
            g2d.setStroke(stroke);
            g2d.drawOval(x + 4, y + 4, diameter - 8, diameter - 8);

            // border
            g2d.setColor(Color.BLACK);
            g2d.drawOval(x, y, diameter, diameter);
        }
    }

    /**
     * Pancake inner class.
     * Provides functionality for the circle that goes on the rank 1 circle tile.
     */
    private class Pancake extends Circle
    {
        /**
         * Constructor.
         */
        public Pancake()
        {
            super(80, 30, 30, Color.GREEN);
        }

        /**
         * Draws the pancake.
         * @param g
         */
        public void draw(Graphics g)
        {
            super.draw(g);

            g.setColor(Color.RED);
            g.fillOval(super.x + 30, super.y + 30, super.diameter - 60, super.diameter - 60);
            g.setColor(Color.WHITE);
            g.drawOval(super.x + 30, super.y + 30, super.diameter - 60, super.diameter - 60);

            // little circles
            g.fillOval(super.x + 37, super.y + 15, 6, 6);
            g.fillOval(super.x + 37, super.y + 59, 6, 6);

            g.fillOval(super.x + 15, super.y + 37, 6, 6);
            g.fillOval(super.x + 59, super.y + 37, 6, 6);
        }
    }
}

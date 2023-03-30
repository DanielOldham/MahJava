import javax.swing.*;
import java.awt.*;

/**
 * Tile Class.
 * Represents one game tile.
 * Designed to be extended by all specific tile types.
 * Extends JPanel.
 */
public class Tile extends JPanel {

    // static graphics components
    /**
     * Size dimension.
     */
    private	static	final	Dimension	SIZE;

    /**
     * First side polygon.
     */
    private	static	final	Polygon		SIDE;

    /**
     * Second side polygon.
     */
    private static  final   Polygon     SIDE2;

    /**
     * Top polygon.
     */
    private	static	final	Polygon		TOP;

    /**
     * Second top polygon.
     */
    private static  final   Polygon     TOP2;

    // static gradient objects
    /**
     * Gradient object.
     */
    private static 	final 	GradientPaint GRADIENT;

    /**
     * Second gradient object.
     */
    private static 	final	GradientPaint GRADIENT2;

    // coordinates
    /**
     * X coordinate.
     */
    public int x;

    /**
     * Y coordinate.
     */
    public int y;

    // locations in data structure
    /**
     * Data structure layer.
     */
    public int layer;

    /**
     * Data structure row location.
     */
    public int row;

    /**
     * Data structure column location.
     */
    public int column;

    // locations in logical 3d array
    /**
     * Logical 3d array row.
     */
    public int logRow;

    /**
     * Logical 3d array column.
     */
    public int logColumn;

    /**
     * Keeps track of the tile's zOrder.
     */
    private int zOrder;

    /**
     * Keeps track of the tile's visibility.
     */
    public boolean isVisable;

    /**
     * Keeps track of the tile's clicked status.
     */
    public boolean isClicked;

    // static initializer block for graphics components
    static
    {
        SIZE = new Dimension(120, 120);

        // first layer
        int[] xs = { 10, 20, 20, 10 };
        int[] ys = { 10, 20, 120, 110 };
        SIDE = new Polygon(xs, ys, 4);

        int[] xt = { 10, 110, 120, 20 };
        int[] yt = { 10, 10, 20, 20 };
        TOP = new Polygon(xt, yt, 4);

        // second layer
        int[] xs2 = {0, 10, 10, 0};
        int[] ys2 = {0, 10, 110, 100};
        SIDE2 = new Polygon(xs2, ys2, 4);

        int[] xt2 = {0, 100, 110, 10};
        int[] yt2 = {0, 0, 10, 10};
        TOP2 = new Polygon(xt2, yt2, 4);

        // gradients
        Color LIGHTER = new Color(214, 113, 24);
        Color DARKER = new Color(115, 60, 11);
        GRADIENT = new GradientPaint(xs2[0], ys2[0], DARKER, xs2[2], ys2[2], LIGHTER);
        GRADIENT2 = new GradientPaint(xt2[0], yt2[0], DARKER, xt2[2], yt2[2], LIGHTER);
    }

    /**
     * Constructor.
     * Sets preferred size.
     */
    public Tile()
    {
        //isFree = false;
        isVisable = true;
        isClicked = false;
        setPreferredSize(SIZE);
        setSize(SIZE);
        setOpaque(false);
    }

    /**
     * PaintComponent method override.
     * @param g
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // rectangle polygon fills
        g.setColor(new Color(255, 252, 224)); // beige
        g.fillRect(20, 20, 100, 100);
        g.fillPolygon(SIDE);
        g.fillPolygon(TOP);

        // rectangle polygon outlines
        g.setColor(Color.BLACK);
        g.drawRect(20, 20, 100, 100);
        g.drawPolygon(SIDE);
        g.drawPolygon(TOP);
        // layer 2 (fill using Graphics2D object)
        Graphics2D g2 = (Graphics2D)g;
        g2.setPaint(GRADIENT);
        g2.fillPolygon(SIDE2);
        g2.setPaint(GRADIENT2);
        g2.fillPolygon(TOP2);
        // layer 2 outline
        g.setColor(Color.BLACK);
        g.drawPolygon(SIDE2);
        g.drawPolygon(TOP2);

        if(isClicked)
        {
            g2.setColor(Color.CYAN);
            g2.setStroke(new BasicStroke(4));
            g2.drawRect(22, 22, 96, 96);
            g2.setStroke(new BasicStroke(1));
        }
    }

    // ZOrder functions to manage ZOrder

    /**
     * Set this tile's zOrder.
     */
    public void setZOrder()
    {
        zOrder = getParent().getComponentZOrder(this);
    }

    /**
     * Get this tile's zOrder.
     * @return
     */
    public int getZOrder()
    {
        return zOrder;
    }

    // matches method : similar to .equals implementation, checks to see if tiles match each other

    /**
     * Checks to see if this tile matches other given tile based on reference and class.
     * Designed to be overridden by specific tile subclasses.
     * @param other second Tile object
     * @return true if the two tiles match, false otherwise
     */
    public boolean matches(Tile other)
    {
        if(other == null)
            return false;
        if(this == other)
            return false;
        return getClass() == other.getClass();
    }
}

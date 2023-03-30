import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Picture tile class.
 * Represents the tiles that have .png pictures on them.
 * Extends Tile.
 */
public class PictureTile extends Tile {
    /**
     * Name of tile.
     */
    private String name;

    /**
     * Tile image.
     */
    private ImageIcon image;

    /**
     * Constructor.
     * Sets the image using the name.
     * @param name the Tile's given name
     */
    public PictureTile(String name)
    {
        this.name = name;

        String path = "/images/" + name + ".png";
        URL url = Bamboo1Tile.class.getResource(path);
        image = new ImageIcon(url);
        image = new ImageIcon(image.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
    }

    /**
     * toString override.
     * @return
     */
    public String toString(){return name;}

    /**
     * paintComponent override.
     * Paints the tile's image.
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        image.paintIcon(this, g, 35, 35);
    }
}

import java.awt.*;
import javax.swing.*;

/**
 * MahJongTest class.
 * Tests the visibility and z-order of game tiles by displaying some in a JFrame.
 */
public class MahJongTest extends JFrame
{
    /**
     * Constructor.
     */
    public MahJongTest()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new TestPanel());

        setSize(500, 500);
        setVisible(true);
    }

    /**
     * Inner TestPanel class, extends JPanel.
     */
    public class TestPanel extends JPanel
    {
        public TestPanel()
        {
            setLayout(null); // requires setPreferredSize & setSize in Tile

            Tile t = new SeasonTile("Spring");
            t.setLocation(200, 200);
            add(t);

            t = new SeasonTile("Summer");
            t.setLocation(180, 180);
            add(t);
        }
    }

    /**
     * Main method.
     * @param args
     */
    public static void main(String[] args) {new MahJongTest();}
}

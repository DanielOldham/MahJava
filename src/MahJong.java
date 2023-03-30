import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

/**
 * MahJong game class.
 * Extends JFrame.
 */
public class MahJong extends JFrame
{
    /**
     * Game board.
     */
    private MahJongBoard board;

    /**
     * Keeps track of game type (random or not).
     */
    public boolean randomGame;

    /**
     * Keeps track of game number.
     */
    public long gameNum;

    /**
     * Constructor.
     */
    public MahJong()
    {
        randomGame = true;
        board = new MahJongBoard(this);
        add(board);
        setSize(1920, 1080);
        setVisible(true);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e){ close(); }
        });

        setTitle("MahJong game number " + gameNum);
    }

    /**
     * Resets the game by creating a new board and adding it in place of the old one.
     * The garbage collector will take care of the old board.
     */
    public void reset()
    {
        remove(board);
        board = new MahJongBoard(this);
        add(board);

        setTitle("MahJong game number " + gameNum);
    }

    /**
     * Handles closing of the application.
     */
    public void close()
    {
        if(JOptionPane.showConfirmDialog(this, "Exit Program?\nProgress will be lost.", "Confirm Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            System.exit(0);
    }

    /**
     * Main method.
     * @param args
     */
    public static void main(String[] args) {new MahJong();}
}

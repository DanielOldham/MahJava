import javax.swing.*;
import java.awt.*;

/**
 * Help class.
 * Provides functionality for displaying the rules and operation of the MahJong game.
 */
public class Help {
    /**
     * Displays the rules in a JOptionPane.
     * @param parent Parent component
     */
    public static void rules(Component parent)
    {
        String message =
                """
                    Rules for MahJong Solitaire

                    - The goal of the game is to remove all tiles from the board
                    - Remove tiles by matching one open tile with another open tile
                    - Open tiles must have no tile to the immediate right or no tile to the immediate left
                    - Open tiles also must have no tile directly on top
                    - Most tiles match with other tiles that have the same face
                    - The Plum, Chrysanthemum, Orchid, and Sparrow tiles may all match with each other
                    - The Spring, Summer, Fall, and Winter tiles may all match with each other\s""";
        JOptionPane.showMessageDialog(parent, message, "Rules", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Displays directions for operation in a JOptionPane.
     * @param parent
     */
    public static void operation(Component parent)
    {
        String message =
                """
                    Operation for MahJong Solitaire

                    - Click on one tile to highlight and a matching tile to match
                    - Click on a tile a second time to unhighlight
                    - Click on the Game menu to access game options
                        * Play (ctrl P) : plays a new random game
                        * Restart (ctrl R) : restarts the current game
                        * Numbered (ctrl N) : requests a game number and starts it
                        * Exit (ctrl E) : exits the game
                    - Click on the Sound menu to access sound options
                        * Sound on (ctrl S) : turns the sound effects on
                        * Sound off (ctrl F) : turns the sound effects off
                    - Click on the Move menu to access move options
                        * Undo (ctrl Z) : undoes the previous move
                        * Removed tiles (ctrl T) : displays all previously removed tiles
                    - Click on the Help menu to access help options
                        * Operation (ctrl O) : displays directions for game operation
                        * Rules (ctrl L) : displays the rules for MahJong Solitaire\s""";
        JOptionPane.showMessageDialog(parent, message, "Operation", JOptionPane.INFORMATION_MESSAGE);
    }
}

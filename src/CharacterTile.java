import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * CharacterTile class.
 * Represents all ranks of character tile.
 * Extends Tile.
 */
public class CharacterTile extends Tile {
    /**
     * Symbol located on the tile.
     */
    protected char symbol;

    /**
     * Map of key-value pairs to match up each character with its Chinese character.
     */
    public static Map<Character, Character> charLookup;
    static
    {
            charLookup = new HashMap<>();
            charLookup.put('1', '\u4E00');
            charLookup.put('2', '\u4E8C');
            charLookup.put('3', '\u4E09');
            charLookup.put('4', '\u56DB');
            charLookup.put('5', '\u4E94');
            charLookup.put('6', '\u516D');
            charLookup.put('7', '\u4E03');
            charLookup.put('8', '\u516B');
            charLookup.put('9', '\u4E5D');
            charLookup.put('N', '\u5317');
            charLookup.put('E', '\u6771');
            charLookup.put('W', '\u897F');
            charLookup.put('S', '\u5357');
            charLookup.put('C', '\u4E2D');
            charLookup.put('F', '\u767C');
    }

    /**
     * Constructor.
     * @param symbol
     */
    public CharacterTile(char symbol)
    {
        this.symbol = symbol;
        setToolTipText(toString());
    }

    /**
     * Overridden matches function.
     * Checks if this symbol matches the symbol from the other Tile.
     * @param other Tile to compare
     * @return
     */
    public boolean matches(Tile other)
    {
        return super.matches(other) && this.symbol == ((CharacterTile)other).symbol;
    }

    /**
     * toString override.
     * Will be called using the tool tip.
     * @return
     */
    public String toString()
    {
        return switch (symbol)
        {
            case 'N' -> "North Wind";
            case 'E' -> "East Wind";
            case 'W' -> "West Wind";
            case 'S' -> "South Wind";
            case 'C' -> "Red Dragon";
            case 'F' -> "Green Dragon";
            default -> "Character " + symbol;
        };
    }

    /**
     * Gets the corresponding Chinese character for this Tile.
     * @return the Chinese char as a String
     */
    public String toChinese()
    {
        return charLookup.get(symbol) + "";
    }

    /**
     * paintComponent override.
     * Paints the tile and draws the symbol.
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // font to change later
        Font f = new Font("GungSeo", Font.PLAIN, 15);
        g.setFont(f);

        // paint symbol in corner
        g.setColor(Color.RED);
        g.drawString(symbol + "", 100, 40);

        // check to see what tile to do
        if(Character.isDigit(symbol))
        {
            // set font
            f = f.deriveFont(f.getSize2D() * 3F);
            g.setFont(f);

            // paint wan char
            g.drawString('\u842C' + "", 45, 105);

            // paint other char
            g.setColor(Color.BLACK);
            g.drawString(toChinese(), 45, 60);
        }else
        {
            f = f.deriveFont(f.getSize2D() * 5F);
            g.setFont(f);
            if(symbol == 'C')
            {
                // color is already red
            }else if(symbol == 'F')
            {
                g.setColor(Color.GREEN);
            }else
            {
                g.setColor(Color.BLACK);
            }

            g.drawString(toChinese(), 33, 93);
        }
    }
}

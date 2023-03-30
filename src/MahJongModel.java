import java.util.ArrayList;

/**
 * MahJongModel class.
 * Extends ArrayList of TileLayer.
 */
public class MahJongModel extends ArrayList<TileLayer>
{
    /**
     * Board object reference.
     */
    private MahJongBoard board;

    /**
     * Constructor.
     * Adds layers to the board and fills them up with shuffled tiles.
     * @param board
     */
    public MahJongModel(MahJongBoard board)
    {
        this.board = board;

        TileDeck deck = new TileDeck();
        if(board.game.randomGame)
        {
            deck.shuffle();
            board.game.gameNum = deck.gameNum;
        }else
        {
            deck.shuffle(board.game.gameNum);
        }


        // layer zero (top layer)
        add(new SquareLayer(1));
        // layer one
        add(new SquareLayer(2));
        // layer two
        add(new SquareLayer(4));
        // layer three
        add(new SquareLayer(6));
        // bottom layer
        add(new BottomLayer(8));

        // loop through layers
        for(int i = 0; i < 5; i++){
            // for each layer, loop through each row
            for(int j = 0; j < this.get(i).numRows; j++){
                // for each row, loop through each tile
                for (int k = 0; k < this.get(i).get(j).numTiles; k++) {
                    Tile next = deck.deal();
                    // set tile x and y
                    next.x = ((k+(14-get(i).get(j).numTiles)/2) * 100) + ((5-i) * 20);
                    next.y = ((j+(8-get(i).numRows)/2) * 100) + ((5-i) * 20);

                    // add tile's theoretical location in logical 3D array
                    next.layer = i;
                    next.row = j;
                    next.column = k;

                    next.logRow = j + ((8-get(i).numRows)/2);
                    next.logColumn = k + ((14-get(i).get(j).numTiles)/2);

                    // add tile
                    this.get(i).get(j).add(next);
                }
            }
        }
        // must shift logical row 5 over by 1
        for(int i = 0; i < this.get(4).get(4).size(); i++)
        {
            this.get(4).get(4).get(i).logColumn += 1;
        }
    }

    /**
     * Checks to see if the given tile is open.
     * @param t Tile to check
     * @return True if the given tile is open, false otherwise
     */
    boolean isTileOpen(Tile t)
    {
        // tile edge cases
        if(t.layer == 1)
        {
            if(this.get(0).get(0).get(0).isVisable) // if very top tile is on board
                return false;
        }else if(t.layer == 4 && t.row == 4 && t.column == 0)
        {
            if (this.get(4).get(3).get(0).isVisable)
                return false;
        }else if(t.layer == 4 && t.row == 3 && t.column == 12)
        {
            if (this.get(4).get(4).get(12).isVisable)
                return false;
        }

        // if tile is on edge
        if(t.column == 0 || t.column == this.get(t.layer).get(t.row).numTiles - 1 || t.layer == 0)
        {
            return true;
        }

        // are tiles to left or right still on board
        if(!this.get(t.layer).get(t.row).get(t.column + 1).isVisable || !this.get(t.layer).get(t.row).get(t.column - 1).isVisable)
        {
            // loop through rows in layer above
            for(int i = 0; i < this.get(t.layer - 1).numRows; i++)
            {
                // loop through all tiles in rows
                for(int j = 0; j < this.get(t.layer - 1).get(i).numTiles; j++)
                {
                    Tile comp = this.get(t.layer - 1).get(i).get(j);
                    // check to see if the tile is logically above this tile
                    if(comp.logRow != t.logRow)
                        break;
                    if(comp.logColumn == t.logColumn)
                    {
                        return !comp.isVisable;
                    }
                }
            }
            return true;
        }
        // both tiles to left and right are still on board
        return false;
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Objects;
import java.util.Stack;

/**
 * Class representing the MahJong board.
 */
public class MahJongBoard extends JPanel implements MouseListener
{
    /**
     * Game reference.
     */
    public MahJong game;

    /**
     * Model reference.
     */
    private MahJongModel model;

    /**
     * Stack to keep track of removed tiles.
     */
    Stack<Tile> removed = new Stack<>();

    // audio, visual
    private PlayClip clip;
    private boolean sound;
    private Fireworks reward;
    private ImageIcon backgroundImage;

    /**
     * Tile object to keep track of previously clicked tile.
     */
    private Tile clicked;

    /**
     * Constructor.
     * Sets pixel location of tiles and adds mouse listeners to them.
     * Sets background image.
     * Sets audio.
     * Calls makeMenu helper function to make the game menu.
     * @param game
     */
    public MahJongBoard(MahJong game)
    {
        this.game = game;
        model = new MahJongModel(this);
        clicked = null;
        setLayout(null);
        sound = true;

        // set location of all tiles
        for (int i = 0; i < model.size(); i++) {
            for (int j = 0; j < model.get(i).size(); j++) {
                for (int k = 0; k < model.get(i).get(j).size(); k++) {
                    Tile t = model.get(i).get(j).get(k);
                    t.setLocation(t.x, t.y);
                    add(t);
                    t.addMouseListener(this);
                }
            }
        }
        // set location of weird tiles
        Tile target = model.get(0).get(0).get(0);
        target.x = target.x + 50;
        target.y = target.y + 50;
        target.setLocation(target.x, target.y);

        target = model.get(4).get(3).get(0);
        target.y = target.y + 50;
        target.setLocation(target.x, target.y);

        // must shift over row 5 for z order to work
        for (int k = 0; k < 14; k++) {
            target = model.get(4).get(4).get(k);
            target.x = target.x + 100;
            target.setLocation(target.x, target.y);
        }
        target = model.get(4).get(4).get(12);
        target.y = target.y - 50;
        target.setLocation(target.x, target.y);
        target = model.get(4).get(4).get(13);
        target.y = target.y - 50;
        target.setLocation(target.x, target.y);

        // get background image
        URL url = MahJongBoard.class.getResource("/images/dragon_bg.png");
        backgroundImage = new ImageIcon(url);
        backgroundImage = new ImageIcon(backgroundImage.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH));

        // set audio
        clip = new PlayClip("audio/stone-scraping.wav", true);

        // create menu using helper function
        makeMenu();
    }

    // ACTION LISTENER FUNCTIONS

    /**
     * Displays removed tiles in a JOptionPane.
     */
    private void displayRemoved()
    {
        // create new JPanel and set layout to grid
        JPanel removedTilePanel = new JPanel();
        removedTilePanel.setLayout(new GridLayout(1, removed.size() / 2));

        // grab tiles from stack in reverse order
        // we want the latest tile to show up on the right
        for(int i = removed.size() - 1; i >= 0; i-=2)
        {
            removedTilePanel.add(removed.get(i));
        }

        // set up JScrollPane
        JScrollPane pane = new JScrollPane(removedTilePanel);
        pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        pane.setPreferredSize(new Dimension(1200, 140));

        // display scroll pane in a JOptionPane
        JOptionPane.showMessageDialog(this, pane, "Removed Tiles", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Undoes previous match.
     */
    private void undo()
    {
        if(removed.empty()) { return; }

        if(clicked != null)
        {
            clicked.isClicked = false;
            clicked = null;
        }

        Tile t2 = removed.pop();
        Tile t1 = removed.pop();

        this.add(t1, t1.getZOrder());
        this.add(t2, t2.getZOrder());
        t1.setLocation(t1.x, t1.y);
        t2.setLocation(t2.x, t2.y);
        t1.isVisable = true;
        t2.isVisable = true;

        this.repaint();
    }

    /**
     * Resets the game with a random seed.
     * Calls MahJong reset function after setting randomGame variable to true (so the deck will know it's random).
     */
    private void resetRandom()
    {
        if(removed.size() == 144)
        {
            reward.stop();
        }
        if(JOptionPane.showConfirmDialog(this, "Start a new game?\nProgress will be lost.", "Confirm new game", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
            game.randomGame = true;
            game.reset();
        }
    }

    // resetNonRandom function : resets the game with a game number given by the user
    private void resetNonRandom()
    {
        String gameNumString = JOptionPane.showInputDialog(this, "Enter a 6 digit game number");
        if(Objects.equals(gameNumString, "") || gameNumString == null)
        {
            return;
        }else
        {
            try
            {
                game.gameNum = Integer.parseInt(gameNumString);
            }catch (NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(this, "Invalid game number");
                return;
            }
        }

        if(removed.size() == 144)
        {
            reward.stop();
        }
        game.randomGame = false;
        game.reset();
    }

    /**
     * Restarts the game by making a new game with the same game number.
     */
    private void restart()
    {
        if(removed.size() == 144)
        {
            reward.stop();
        }
        if(JOptionPane.showConfirmDialog(this, "Restart the game?\nProgress will be lost.", "Confirm restarts", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
            game.randomGame = false;
            game.reset();
        }
    }

    // HELPER FUNCTIONS

    /**
     * Sets up JMenuBar, adds the menu items, and adds action listeners to them.
     */
    private void makeMenu()
    {
        JMenuBar bar = new JMenuBar();
        game.setJMenuBar(bar);

        // game menu
        JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic('G');
        bar.add(gameMenu);

        JMenuItem playItem = new JMenuItem("Play", 'P');
        playItem.setToolTipText("Start a new game");
        playItem.setAccelerator(KeyStroke.getKeyStroke("ctrl P"));
        gameMenu.add(playItem);

        JMenuItem restartItem = new JMenuItem("Restart", 'R');
        restartItem.setToolTipText("Restart the current game");
        restartItem.setAccelerator(KeyStroke.getKeyStroke("ctrl R"));
        gameMenu.add(restartItem);

        JMenuItem numberedItem = new JMenuItem("Numbered", 'N');
        numberedItem.setToolTipText("Start a new numbered game");
        numberedItem.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
        gameMenu.add(numberedItem);

        JMenuItem exitItem = new JMenuItem("Exit", 'E');
        exitItem.setToolTipText("Exit the game");
        exitItem.setAccelerator(KeyStroke.getKeyStroke("ctrl E"));
        gameMenu.add(exitItem);

        // game menu action listeners
        playItem.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent event)
        { resetRandom(); }
        });

        restartItem.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent event)
        { restart(); }
        });

        numberedItem.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent event)
        { resetNonRandom(); }
        });

        exitItem.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent event)
        { game.close(); }
        });

        // sound menu
        JMenu soundMenu = new JMenu("Sound");
        soundMenu.setMnemonic('S');
        bar.add(soundMenu);

        JMenuItem onItem = new JMenuItem("Sound on", 'O');
        onItem.setToolTipText("Turn the sound on");
        onItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        soundMenu.add(onItem);

        JMenuItem offItem = new JMenuItem("Sound off", 'F');
        offItem.setToolTipText("Turn the sound off");
        offItem.setAccelerator(KeyStroke.getKeyStroke("ctrl F"));
        soundMenu.add(offItem);

        // sound menu action listeners
        onItem.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent event)
        { sound = true; }
        });

        offItem.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent event)
        { sound = false; }
        });

        // move menu
        JMenu moveMenu = new JMenu("Move");
        gameMenu.setMnemonic('M');
        bar.add(moveMenu);

        JMenuItem undoItem = new JMenuItem("Undo", 'Z');
        undoItem.setToolTipText("Undo the last move");
        undoItem.setAccelerator(KeyStroke.getKeyStroke("ctrl Z"));
        moveMenu.add(undoItem);

        JMenuItem displayItem = new JMenuItem("Removed tiles", 'T');
        displayItem.setToolTipText("Display all removed tiles");
        displayItem.setAccelerator(KeyStroke.getKeyStroke("ctrl T"));
        moveMenu.add(displayItem);

        // move menu action listeners
        undoItem.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent event)
        { undo(); }
        });

        displayItem.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent event)
        { displayRemoved(); }
        });

        // help menu
        JMenu helpMenu = new JMenu("Help");
        gameMenu.setMnemonic('H');
        bar.add(helpMenu);

        JMenuItem operationItem = new JMenuItem("Operation", 'P');
        operationItem.setToolTipText("Directions for game operation");
        operationItem.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        helpMenu.add(operationItem);

        JMenuItem rulesItem = new JMenuItem("Rules", 'L');
        rulesItem.setToolTipText("MahJong rules");
        rulesItem.setAccelerator(KeyStroke.getKeyStroke("ctrl L"));
        helpMenu.add(rulesItem);

        // help menu action listeners
        operationItem.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent event)
        { Help.operation(game); }
        });

        rulesItem.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent event)
        { Help.rules(game); }
        });
    }

    /**
     * Begins the reward screen using the Fireworks class.
     */
    private void startReward()
    {
        reward = new Fireworks(this);
        reward.setSound(false);
        reward.fire();
    }

    // OVERRIDDEN FUNCTIONS

    /**
     * Paints background image.
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        backgroundImage.paintIcon(this, g, 160, 90);
    }

    /**
     * Handles game logic for clicking on tiles.
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e)
    {
        Tile t = (Tile)e.getSource();

        if(!t.isVisable)
        {
            return;
        }

        // if tile is open
        if(this.model.isTileOpen(t)) {
            // if tile has already been clicked, unclick it
            if(t == clicked)
            {
                t.isClicked = false;
                clicked = null;
                this.repaint();
            }else if(clicked == null) // if this is first tile clicked
            {
                t.isClicked = true;
                clicked = t;
                this.repaint();
            }else // this is not the first tile clicked
            {
                if(t.matches(clicked))
                {
                    t.isVisable = false;
                    clicked.isVisable = false;
                    clicked.isClicked = false;

                    // set z order of tiles
                    clicked.setZOrder();
                    remove(clicked);
                    t.setZOrder();
                    remove(t);

                    this.repaint();

                    // add tiles to removed stack
                    removed.push(t);
                    removed.push(clicked);

                    clicked = null;

                    // play sound if sound is on
                    if(sound) { clip.play(); }

                    // if removed all tiles, play fireworks
                    if(removed.size() == 144)
                    {
                        game.setTitle("You Win!");
                        startReward();
                    }
                }else // if the tiles don't match, unclick the first one
                {
                    clicked.isClicked = false;
                    clicked = null;
                    repaint();
                }
            }
        }
    }

    // MouseListener dummy method implementations
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}

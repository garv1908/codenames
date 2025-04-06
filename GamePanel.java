import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements MouseListener {

    private Board board;
    private int BOARD_SIZE = 5;

    public GamePanel() {
        this.setBackground(Color.BLACK);
        setFocusable(true);
        this.board = new Board(BOARD_SIZE); // 5x5 board
        this.addMouseListener(this);
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawBoard(g);
    }

    private void drawBoard(Graphics g)
    {
        int cellSize = 100;
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                String word = board.getWord(i, j);
                String state = board.getRevealedWordState(i, j);
                g.setColor(getColor(state));
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.setColor(Color.WHITE);
                g.drawString(word, j * cellSize + 10, i * cellSize + 50);
            }
        }
    }
    
    private Color getColor(String state) {
        switch (state) {
            case "red": return Color.RED;
            case "blue": return Color.BLUE;
            case "neutral": return Color.GRAY;
            case "assassin": return Color.BLACK;
            default: return new Color(0, 0, 0, 0); // transparent for unrevealed words
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int cellSize = 100;
        int row = e.getY() / cellSize;
        int col = e.getX() / cellSize;
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) return;
        board.setRevealedWordState(row, col, board.getSecretWordState(row, col));
        repaint();

        if (board.isGameOver()) {
            JOptionPane.showMessageDialog(this, "Game over!");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}

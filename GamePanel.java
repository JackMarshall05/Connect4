package connect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {
    private final Game game;

    public GamePanel(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(700, 600));
        setBackground(Color.GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        byte[][] board = game.board();

        int cols = board[0].length;
        int rows = board.length;
        int panelWidth = getWidth();
        int slotSize = panelWidth / cols;
        int panelHeight = slotSize * rows;

        // Resize panel height to fit board
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        revalidate();

        // Draw blue board grid
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, panelWidth, panelHeight);

        // Draw circles
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = col * slotSize;
                int y = row * slotSize;

                g.setColor(Color.WHITE); // default empty
                if (board[row][col] == 1) g.setColor(Color.BLUE);     // O
                else if (board[row][col] == -1) g.setColor(Color.RED); // X

                g.fillOval(x + 5, y + 5, slotSize - 10, slotSize - 10);
            }
        }
    }

    public void refresh() {
        repaint();
    }
}

package game;

import java.util.Arrays;

public class GameState {

    private final Cell[] cells;
    private final String currentPlayer;    // "X" | "O"
    private final String winner;           // "X" | "O" | "DRAW" | null
    private final int historySize;

    private GameState(Cell[] cells, String currentPlayer, String winner, int historySize) {
        this.cells = cells;
        this.currentPlayer = currentPlayer;
        this.winner = winner;
        this.historySize = historySize;
    }

    public static GameState forGame(Game game) {
        Cell[] cells = getCells(game);
        String current = game.getPlayer() == Player.PLAYER0 ? "X" : "O";
        String win = null;
        Player p = game.getWinner();
        if (p != null) {
            win = (p == Player.PLAYER0) ? "X" : "O";
        } else if (game.isDraw()) {
            win = "DRAW";
        }
        return new GameState(cells, current, win, game.getHistorySize());
    }


    public Cell[] getCells() {
        return this.cells;
    }

    /**
     * toString() of GameState will return the string representing
     * the GameState in JSON format.
     */
    @Override
    public String toString() {
        String cellsJson = Arrays.toString(this.cells);

        String currentPlayerJson = (currentPlayer == null)
                ? "null"
                : "\"" + currentPlayer + "\"";

        String winnerJson = (winner == null)
                ? "null"
                : "\"" + winner + "\"";

        return "{"
                + "\"cells\": " + cellsJson + ","
                + "\"currentPlayer\": " + currentPlayerJson + ","
                + "\"winner\": " + winnerJson + ","
                + "\"historySize\": " + historySize
                + "}";
    }

    private static Cell[] getCells(Game game) {
        Cell cells[] = new Cell[9];
        Board board = game.getBoard();
        for (int x = 0; x <= 2; x++) {
            for (int y = 0; y <= 2; y++) {
                String text = "";
                boolean playable = false;
                Player player = board.getCell(x, y);
                if (player == Player.PLAYER0)
                    text = "X";
                else if (player == Player.PLAYER1)
                    text = "O";
                else if (player == null) {
                    playable = true;
                }
                cells[3 * y + x] = new Cell(x, y, text, playable);
            }
        }
        return cells;
    }
}

class Cell {
    private final int x;
    private final int y;
    private final String text;
    private final boolean playable;

    Cell(int x, int y, String text, boolean playable) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.playable = playable;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getText() {
        return this.text;
    }

    public boolean isPlayable() {
        return this.playable;
    }

    @Override
    public String toString() {
        return "{"
                + "\"text\": \"" + this.text + "\","
                + "\"playable\": " + this.playable + ","
                + "\"x\": " + this.x + ","
                + "\"y\": " + this.y
                + "}";
    }
}
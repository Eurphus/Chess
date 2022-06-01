package me.mac.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import static me.mac.chess.Main.*;

public final class Horse {
    // Return all possible moves for a specific Horse
    public static List<String> moveHorse(int column, int row, String player, String[]... board) {
        // Define initial moveList
        List<String> moveList = new ArrayList<>();

        List<String> playerArray = (player.equals("Black")) ? blackPiecesList : whitePiecesList;

        // All possible moves. Formatted as {row difference, column difference}
        int[][] combinations = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
        int horizontal, vertical;

        // For each possible move (max 8 moves, so a loop that runs 8 times ), goes through each one to see if it is possible
        for (int i = 0; i < 8; i++) {
            horizontal = row + combinations[i][0];
            vertical = column + combinations[i][1];

            // Checks if this specific row/column combination is possible - Not outside board or blocked by piece.
            if (horizontal >= 1 && horizontal <= 8 && vertical >= 1 && vertical <= 8) {
                if (!playerArray.contains(board[vertical][horizontal]))
                    moveList.add(rowList.get(horizontal) + vertical);
            }
        }
        return moveList;
    }
}
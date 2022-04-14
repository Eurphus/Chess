package me.mac.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import static me.mac.chess.Main.*;

public class Horse {
    public static List<String> moveHorse(int column, int row, String player, String[][] board) {
        List<String> moveList = new ArrayList<>();

        List<String> playerArray = whitePiecesList;
        if (player.equals("Black")) playerArray = blackPiecesList;

        int[][] combinations = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
        int horizontal, vertical;
        for (int i = 0; i < 8; i++) {
            horizontal = row + combinations[i][0];
            vertical = column + combinations[i][1];
            if (horizontal >= 1 && horizontal <= 8 && vertical >= 1 && vertical <= 8) {
                if (!playerArray.contains(board[vertical][horizontal]))
                    moveList.add(rowList.get(horizontal) + vertical);
            }
        }

        return moveList;
    }
}

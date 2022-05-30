package me.mac.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import static me.mac.chess.Main.*;

public class Bishop {
    // Return all possible moves for a specific Bishop
    public static List<String> moveBishop(int column, int row, String player, String[]... board) {
        // Define initial moveList
        List<String> moveList = new ArrayList<>();

        // Define player & enemy piece arrays
        List<String> playerArray = player.equals("Black") ? blackPiecesList : whitePiecesList;
        List<String> enemyArray = player.equals("Black") ? whitePiecesList : blackPiecesList;

        // Bishop Diagonal Up Right
        for (int i = column + 1, q = row + 1; (i <= 8 && q <= 8); i++, q++) {
            if (playerArray.contains(board[i][q])) {
                break;
            } else if (enemyArray.contains(board[i][q])) {
                moveList.add(rowList.get(q) + i);
                break;
            }
            moveList.add(rowList.get(q) + i);
        }

        // Bishop Diagonal Down Left
        for (int i = column - 1, q = row - 1; (i >= 1 && q >= 1); i--, q--) {
            if (playerArray.contains(board[i][q])) {
                break;
            } else if (enemyArray.contains(board[i][q])) {
                moveList.add(rowList.get(q) + i);
                break;
            }
            moveList.add(rowList.get(q) + i);
        }

        // Bishop Diagonal Down Right
        for (int i = column - 1, q = row + 1; (i >= 1 && q >= 8); i--, q++) {
            if (playerArray.contains(board[i][q])) {
                break;
            } else if (enemyArray.contains(board[i][q])) {
                moveList.add(rowList.get(q) + i);
                break;
            }
            moveList.add(rowList.get(q) + i);
        }

        // Bishop Diagonal Up Left
        for (int i = column + 1, q = row - 1; (i <= 8 && q >= 1); i++, q--) {
            if (playerArray.contains(board[i][q])) {
                break;
            } else if (enemyArray.contains(board[i][q])) {
                moveList.add(rowList.get(q) + i);
                break;
            }
            moveList.add(rowList.get(q) + i);
        }

        return moveList;
    }
}
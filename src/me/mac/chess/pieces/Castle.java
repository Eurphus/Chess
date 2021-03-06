package me.mac.chess.pieces;

// Java Imports
import java.util.ArrayList;
import java.util.List;

// Project Imports
import static me.mac.chess.Main.*;

public final class Castle {
    // Return all possible moves for a specific Castle
    public static List<String> moveCastle(int column, int row, String player, String[]... board) {
        // Define initial moveList
        List<String> moveList = new ArrayList<>();

        // Define player & enemy piece arrays
        List<String> playerArray = (player.equals("Black")) ? blackPiecesList : whitePiecesList;
        List<String> enemyArray = (player.equals("Black")) ? whitePiecesList : blackPiecesList;

        // Castle Up
        for (int i = column + 1; i <= 8; i++) {
            if (playerArray.contains(board[i][row])) {
                break;
            } else if (enemyArray.contains(board[i][row])) {
                moveList.add(rowList.get(row) + i);
                break;
            }
            moveList.add(rowList.get(row) + i);
        }

        // Castle Down
        for (int i = column - 1; i >= 1; i--) {
            if (playerArray.contains(board[i][row])) {
                break;
            } else if (enemyArray.contains(board[i][row])) {
                moveList.add(rowList.get(row) + i);
                break;
            }
            moveList.add(rowList.get(row) + i);
        }

        // Castle Right
        for (int i = row + 1; i <= 8; i++) {
            if (playerArray.contains(board[column][i])) {
                break;
            } else if (enemyArray.contains(board[column][i])) {
                moveList.add(rowList.get(i) + column);
                break;
            }
            moveList.add(rowList.get(i) + column);
        }

        // Castle Left
        for (int i = row - 1; i >= 1; i--) {
            if (playerArray.contains(board[column][i])) {
                break;
            } else if (enemyArray.contains(board[column][i])) {
                moveList.add(rowList.get(i) + column);
                break;
            }
            moveList.add(rowList.get(i) + column);
        }
        return moveList;
    }
}
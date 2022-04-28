package me.mac.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import static me.mac.chess.Main.*;

public class Queen {
    public static List<String> moveQueen(int column, int row, String player, String[][] board) {
        ArrayList<String> moveList = new ArrayList<>();

        List<String> playerArray = (player.equals("Black")) ? blackPiecesList : whitePiecesList;
        List<String> enemyArray = (player.equals("Black")) ? whitePiecesList : blackPiecesList;

        // Queen Up
        for (int i = column + 1; i <= 8; i++) {
            if (playerArray.contains(board[i][row])) {
                break;
            } else if (enemyArray.contains(board[i][row])) {
                moveList.add(rowList.get(row) + i);
                break;
            }
            moveList.add(rowList.get(row) + i);
        }

        // Queen Down
        for (int i = column - 1; i >= 1; i--) {
            if (playerArray.contains(board[i][row])) {
                break;
            } else if (enemyArray.contains(board[i][row])) {
                moveList.add(rowList.get(row) + i);
                break;
            }
            moveList.add(rowList.get(row) + i);
        }

        // Queen Right
        for (int i = row + 1; i <= 8; i++) {
            if (playerArray.contains(board[column][i])) {
                break;
            } else if (enemyArray.contains(board[column][i])) {
                moveList.add(rowList.get(i) + column);
                break;
            }
            moveList.add(rowList.get(i) + column);
        }

        // Queen Left
        for (int i = row - 1; i >= 1; i--) {
            if (playerArray.contains(board[column][i])) {
                break;
            } else if (enemyArray.contains(board[column][i])) {
                moveList.add(rowList.get(i) + column);
                break;
            }
            moveList.add(rowList.get(i) + column);
        }
        // Queen Diagonal Up Right
        for (int i = column + 1, q = row + 1; (i <= 8 && q <= 8); i++, q++) {
            if (playerArray.contains(board[i][q])) {
                break;
            } else if (enemyArray.contains(board[i][q])) {
                moveList.add(rowList.get(q) + i);
                break;
            }
            moveList.add(rowList.get(q) + i);
        }

        // Queen Diagonal Down Left
        for (int i = column - 1, q = row - 1; (i >= 1 && q >= 1); i--, q--) {
            if (playerArray.contains(board[i][q])) {
                break;
            } else if (enemyArray.contains(board[i][q])) {
                moveList.add(rowList.get(q) + i);
                break;
            }
            moveList.add(rowList.get(q) + i);
        }

        // Queen Diagonal Down Right
        for (int i = column - 1, q = row + 1; (i >= 1 && q <= 8); i--, q++) {
            if (playerArray.contains(board[i][q])) {
                break;
            } else if (enemyArray.contains(board[i][q])) {
                moveList.add(rowList.get(q) + i);
                break;
            }
            moveList.add(rowList.get(q) + i);
        }
        // Queen Diagonal Up Left
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

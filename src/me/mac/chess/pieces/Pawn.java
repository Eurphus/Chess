package me.mac.chess.pieces;

// Java Imports
import java.util.ArrayList;
import java.util.List;

// Project Imports
import static me.mac.chess.Main.*;

public final class Pawn {
    // Return all possible moves for a specific Pawn
    public static List<String> movePawn(int column, int row, String player, String[]... board) {
        // Define initial moveList
        List<String> moveList = new ArrayList<>();

        // White Moves
        if (player.equals("White")) {
            if (board[column + 1][row].equals("#")) {
                moveList.add(rowList.get(row) + (column + 1));
            }
            // Allowed to move forward 2 when on column 2
            if (column == 2 && board[column + 2][row].equals("#")) {
                moveList.add(rowList.get(row) + (4));
            }
            // Go up and take a piece diagonally
            if (blackPiecesList.contains(board[column + 1][row + 1])) {
                moveList.add(rowList.get(row + 1) + (column + 1));
            }
            // Go up and take a piece diagonally
            if (blackPiecesList.contains(board[column + 1][row - 1])) {
                moveList.add(rowList.get(row - 1) + (column + 1));
            }
            // Black Moves
        } else {
            if (board[column - 1][row].equals("#")) {
                moveList.add(rowList.get(row) + (column - 1));
            }
            // Allowed to move forward 2 when on column 7
            if (column == 7 && board[column - 2][row].equals("#")) {
                moveList.add(rowList.get(row) + (5));
            }
            // Go up and take a piece diagonally
            if (whitePiecesList.contains(board[column - 1][row - 1])) {
                moveList.add(rowList.get(row - 1) + (column - 1));
            }
            // Go up and take a piece diagonally
            if (whitePiecesList.contains(board[column - 1][row + 1])) {
                moveList.add(rowList.get(row + 1) + (column - 1));
            }
        }
        return moveList;
    }
}
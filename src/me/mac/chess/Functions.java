package me.mac.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.mac.chess.Main.*;
import static me.mac.chess.pieces.Bishop.moveBishop;
import static me.mac.chess.pieces.Castle.moveCastle;
import static me.mac.chess.pieces.Horse.moveHorse;
import static me.mac.chess.pieces.King.moveKing;
import static me.mac.chess.pieces.Pawn.movePawn;
import static me.mac.chess.pieces.Queen.moveQueen;

// Extra class for additional important functions
public class Functions {

    // Find all possible moves from all pieces within a given chessBoard & player
    public static List<String> allMoves(String[][] board, String player) {
        List<String> moveList = new ArrayList<>();

        String piece;
        // Find all white moves
        if (player.equals("White")) {
            for (int i = 0; i < board.length; i++) {
                for (int q = 0; q < board[i].length; q++) {
                    piece = board[i][q];
                    switch (piece) {
                        case "♟" -> moveList.addAll(movePawn(i, q, player, board));
                        case "♜" -> moveList.addAll(moveCastle(i, q, player, board));
                        case "♞" -> moveList.addAll(moveHorse(i, q, player, board));
                        case "♝" -> moveList.addAll(moveBishop(i, q, player, board));
                        case "♛" -> moveList.addAll(moveQueen(i, q, player, board));
                        case "♚" -> moveList.addAll(moveKing(i, q, player, board));
                    }
                }
            }
        } else { // Find all black moves
            for (int i = 0; board.length > i; i++) {
                for (int q = 0; q < board[i].length; q++) {
                    piece = board[i][q];
                    switch (piece) {
                        case "♙" -> moveList.addAll(movePawn(i, q, player, board));
                        case "♖" -> moveList.addAll(moveCastle(i, q, player, board));
                        case "♘" -> moveList.addAll(moveHorse(i, q, player, board));
                        case "♗" -> moveList.addAll(moveBishop(i, q, player, board));
                        case "♕" -> moveList.addAll(moveQueen(i, q, player, board));
                        case "♔" -> moveList.addAll(moveKing(i, q, player, board));
                    }
                }
            }
        }
        return moveList;
    }

    // Detect if a player is in checkMate given a board, player and the kings position
    public static boolean checkMateDetector(String[][] board, String player, String kingPosition, Integer kingRow, Integer kingColumn) {
        String[][] boardTemp;
        String enemy = "Black";

        // Define king piece & player
        String kingPiece = "♚";
        if (player.equals("Black")) {
            enemy = "White";
            kingPiece = "♔";
        }

        List<String> moveList;
        String tempPiece;

        // Go through every piece moveList, detect if any moves would get the player out of check
        for (int i = 0; i < board.length; i++) {
            for (int q = 0; q < board[i].length; q++) {
                tempPiece = board[i][q];
                if (tempPiece.equals("#")) continue;
                switch (tempPiece) {
                    case "♟", "♙" -> moveList = movePawn(i, q, player, board);
                    case "♜", "♖" -> moveList = moveCastle(i, q, player, board);
                    case "♞", "♘" -> moveList = moveHorse(i, q, player, board);
                    case "♝", "♗" -> moveList = moveBishop(i, q, player, board);
                    case "♛", "♕" -> moveList = moveQueen(i, q, player, board);
                    case "♚", "♔" -> moveList = moveKing(i, q, player, board);
                    default -> throw new IllegalStateException("Unexpected value: " + tempPiece);
                }
                // Make a temporary copy of the current given board
                boardTemp = Arrays.stream(board).map(String[]::clone).toArray(String[][]::new);

                // Check if each move in the given movelist of the piece would change the check
                for (String s : moveList) {
                    boardTemp[Integer.parseInt(s.substring(1, 2))][rowList.indexOf(s.substring(0, 1))] = tempPiece;
                    boardTemp[i][q] = "#";
                    if (tempPiece.equals(kingPiece)) {
                        kingPosition = s;
                    } else if (rowList.indexOf(s.substring(0, 1)) != kingRow || Integer.parseInt(kingPosition.substring(1, 2)) != kingRow) {
                        kingPosition = rowList.get(kingRow) + kingColumn;
                    }
                    // If the move successfully prevents chess, return that checkMake has not been detected, and reset the temporary board.
                    if (!allMoves(boardTemp, enemy).contains(kingPosition)) {
                        return false;
                    } else {
                        boardTemp = Arrays.stream(board).map(String[]::clone).toArray(String[][]::new);
                    }
                }
            }
        }
        // Return that checkMate has been detected unless proven otherwise with return false
        return true;
    }
}
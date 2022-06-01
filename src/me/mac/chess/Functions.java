package me.mac.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static me.mac.chess.Main.*;
import static me.mac.chess.pieces.Bishop.moveBishop;
import static me.mac.chess.pieces.Castle.moveCastle;
import static me.mac.chess.pieces.Horse.moveHorse;
import static me.mac.chess.pieces.King.moveKing;
import static me.mac.chess.pieces.Pawn.movePawn;
import static me.mac.chess.pieces.Queen.moveQueen;

// Extra class for additional important functions
public final class Functions {

    // Find all possible moves from all pieces within a given chessBoard & player
    public static List<String> allMoves(String[][] board, String player) {
        List<String> moveList = new ArrayList<>();

        String piece;
        // Find all white moves
        if (player.equals("White")) {
            // Goes through every single position on the board finding white pieces, then adding their available moves to the total moveList
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
            // Goes through every single position on the board finding black pieces, then adding their available moves to the total moveList
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
        List<String> playerPieceList = whitePiecesList;

        // Define king piece & player
        String kingPiece = "♚";
        if (player.equals("Black")) {
            enemy = "White";
            kingPiece = "♔";
            playerPieceList = blackPiecesList;
        }

        List<String> moveList;

        // String to temporary store the given piece
        String tempPiece;

        // Go through every piece moveList, detect if any moves would get the player out of check
        for (int i = 0; i < board.length; i++) {
            for (int q = 0; q < board[i].length; q++) {
                tempPiece = board[i][q];
                if (!playerPieceList.contains(tempPiece)) continue;
                switch (tempPiece) {
                    case "♟", "♙" -> moveList = movePawn(i, q, player, board);
                    case "♜", "♖" -> moveList = moveCastle(i, q, player, board);
                    case "♞", "♘" -> moveList = moveHorse(i, q, player, board);
                    case "♝", "♗" -> moveList = moveBishop(i, q, player, board);
                    case "♛", "♕" -> moveList = moveQueen(i, q, player, board);
                    case "♚", "♔" -> moveList = moveKing(i, q, player, board);
                    default -> {
                        continue;
                    }
                }
                // Make a temporary copy of the current given board
                boardTemp = Arrays.stream(board).map(String[]::clone).toArray(String[][]::new);

                // Check if each move in the given moveList of the piece would change the check
                for (String s : moveList) {
                    boardTemp[Integer.parseInt(s.substring(1, 2))][rowList.indexOf(s.substring(0, 1))] = tempPiece;
                    boardTemp[i][q] = "#";

                    // If the tempPiece is the king, make the kingPosition update with every possible move for the king
                    if (tempPiece.equals(kingPiece)) {
                        kingPosition = s;
                        // If the piece is not the king, ensure that the kingPosition is the original kingPosition
                    } else if (rowList.indexOf(s.substring(0, 1)) != kingRow || Integer.parseInt(kingPosition.substring(1, 2)) != kingRow) {
                        kingPosition = rowList.get(kingRow) + kingColumn;
                    }
                    // If the move successfully prevents chess, return that checkMake has not been detected, and reset the temporary board.
                    if (!allMoves(boardTemp, enemy).contains(kingPosition)) {
                        return false;
                    } else {
                        // If the move does not successfully prevent chess, reset the board and retry.
                        boardTemp = Arrays.stream(board).map(String[]::clone).toArray(String[][]::new);
                    }
                }
            }
        }
        // Return that checkMate has been detected unless proven otherwise with return false
        return true;
    }
    // Method for clearing console via spamming
    public static void clearConsole() {
        System.out.println("\n".repeat(500));
    }

    // Prints the help menu for chess
    public static void printHelp() {
        System.out.println("""
                Help Menu
                  — This game plays almost the same to how actual chess works. For the proper rules of chess see https://www.chess.com/learn-how-to-play-chess. In this version En Passant & Castling do not work
                  — In order to move you will be asked to provide the coordinates of the piece you'd like to move in the [Row][Column] format. See the sides of the chessboard to find which positions correlates to what row & column. If the input is invalid, you will be asked to move again.
                  — You will be given two different phases when it is your turn; a pick phase, and a move phase. In the pick phase, you must provide the coordinates of the piece you want to move. In the move phase, you must provide the coordinates to the place you wish you move the previously selected piece to.
                    — If you are on the move phase but wish to revert back to the picking phase, input q, quit or stop.
                  — Input HELP if you ever wish to bring this menu up during a game
                  — Input resign or forfeit if you ever with to forfeit the game
                  — Do not spam incorrect inputs, you will trigger error catching and cause delay in processing

                Please input 1 in order to exit this menu""");
    }

    // Method for error handling for things such as incorrect inputs or inability to move to a specified spot. Includes a delay and a warning saying the game will resume in two seconds
    public static void sendError(String message) {
        // Print initial error message
        System.out.println("ERROR: " + message);
        try {
            System.out.println("Resuming in 2 seconds...");

            // Sleep the program for two seconds
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            System.out.println("Please wait...");
        }
    }
}
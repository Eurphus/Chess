package me.mac.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static me.mac.chess.Functions.allMoves;
import static me.mac.chess.Functions.checkMateDetector;
import static me.mac.chess.pieces.Bishop.moveBishop;
import static me.mac.chess.pieces.Castle.moveCastle;
import static me.mac.chess.pieces.Horse.moveHorse;
import static me.mac.chess.pieces.King.moveKing;
import static me.mac.chess.pieces.Pawn.movePawn;
import static me.mac.chess.pieces.Queen.moveQueen;

public class Main {
    // Declare Main Board
    static String[][] chessBoard = {
            {" ", "A", "B", "C", "D", "E", "F", "G", "H", " "},
            {"1", "♜", "♞", "♝", "♛", "♚", "♝", "♞", "♜", "1"},
            {"2", "♟", "♟", "♟", "♟", "♟", "♟", "♟", "♟", "2"},
            {"3", "#", "#", "#", "#", "#", "#", "#", "#", "3"},
            {"4", "#", "#", "#", "#", "#", "#", "#", "#", "4"},
            {"5", "#", "#", "#", "#", "#", "#", "#", "#", "5"},
            {"6", "#", "#", "#", "#", "#", "#", "#", "#", "6"},
            {"7", "♙", "♙", "♙", "♙", "♙", "♙", "♙", "♙", "7"},
            {"8", "♖", "♘", "♗", "♔", "♕", "♗", "♘", "♖", "8"},
            {" ", "A", "B", "C", "D", "E", "F", "G", "H", " "}
    };

    // Public Static Variables
    public static List<String> whitePiecesList = Arrays.asList("♟", "♜", "♞", "♝", "♛", "♚");
    public static List<String> blackPiecesList = Arrays.asList("♙", "♖", "♘", "♗", "♕", "♔");
    public static List<String> rowList = Arrays.asList("0", "A", "B", "C", "D", "E", "F", "G", "H");

    // Variables
    static String action = "Pick";
    static String input = "null";
    static int row = -1, column = -1, oldRow = -1, oldColumn = -1;
    static String piece = "", player = "White", enemy = "Black";
    static Boolean allowed = false;

    public static void main(String[] args) {
        // Define Scanner
        Scanner scan = new Scanner(System.in);

        // Introduction
        System.out.println("Hello! Welcome to chess, if you would like to play, please enter YES");
        if (!scan.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("Game Cancelled");
            System.exit(1);
        }

        System.out.println(
                "All moves must be put in this format:\n  -Type the piece position\n  -Return and the next line must be where you want to move it\nGo!\n\n");

        // Infinite loop until broken via chess or resignation
        while (true) {
            // Convert array to string and format it correctly
            String chessBoardString = Arrays.deepToString(chessBoard)
                    .replace("[[", "\n")
                    .replace("[", "")
                    .replace("], ", "\n")
                    .replace("]", "")
                    .replace(",", "");

            // Print out formatted string array
            System.out.println(chessBoardString);

            // Print based on current action
            if (action.equals("Pick")) {
                System.out.println("Please choose a piece you wish to use");
                System.out.println("It is " + player + "'s turn!");
            } else if (action.equals("Move")) {
                System.out.println("Please choose a position you wish to move the piece to");
            }

            // Define current input, into upperCase
            input = scan.nextLine().toUpperCase();

            // Cancel Moving, reset action and continue loop.
            if (input.equals("Q") && action.equals("Move")) {
                System.out.println("\nMoving resvet...");
                action = "Pick";
                continue;
            } else if (input.length() != 2) {
                System.out.println("Wrong Input");
                continue;
            }
            // Define column if matches valid int
            if (input.substring(1, 2).matches("-?\\d+(\\.\\d+)?")) {
                column = Integer.parseInt(input.substring(1, 2));

                // Check if column is valid & within bounds of board
                if (column <= 0 || column >= 9) {
                    System.out.println("Invalid Column");
                    scan.nextLine();
                    continue;
                }
            } else {
                System.out.println("Invalid Column");
                continue;
            }

            // Define row if matches valid Row
            if (rowList.contains(input.substring(0, 1))) {
                row = rowList.indexOf(input.substring(0, 1));
            } else {
                System.out.println("Invalid Row");
                scan.nextLine();
                continue;
            }

            if (action.equals("Pick")) {
                piece = chessBoard[column][row];

                if (piece.equals("#")) {
                    System.out.println("There is no piece here!");
                    continue;
                } else if (player.equals("White") && !whitePiecesList.contains(piece)) {
                    System.out.println("This is not your piece!");
                    continue;
                } else if (player.equals("Black") && !blackPiecesList.contains(piece)) {
                    System.out.println("This is not your piece!");
                    continue;
                }

                System.out.println(
                        "You selected " + player + " " + piece + " at " + rowList.get(row) + column + "\n");

                // Save the position of the selected piece for the move phase
                oldRow = row;
                oldColumn = column;

                // Change action to move after picking is done
                action = "Move";
                continue;
            } else if (action.equals("Move")) allowed = false;
            // See which piece is used, and if the given input is within that pieces valid, allowed movelist
            switch (piece) {
                case "♟", "♙":
                    if (movePawn(oldColumn, oldRow, player, chessBoard).contains((rowList.get(row) + column)))
                        allowed = true;
                    break;
                case "♜", "♖":
                    if (moveCastle(oldColumn, oldRow, player, chessBoard).contains((rowList.get(row) + column)))
                        allowed = true;
                    break;
                case "♞", "♘":
                    if (moveHorse(oldColumn, oldRow, player, chessBoard).contains(rowList.get(row) + column))
                        allowed = true;
                    break;
                case "♝", "♗":
                    if (moveBishop(oldColumn, oldRow, player, chessBoard).contains(rowList.get(row) + column))
                        allowed = true;
                    break;
                case "♛", "♕":
                    if (moveQueen(oldColumn, oldRow, player, chessBoard).contains(rowList.get(row) + column))
                        allowed = true;
                    break;
                case "♚", "♔":
                    if (moveKing(oldColumn, oldRow, player, chessBoard).contains(rowList.get(row) + column))
                        allowed = true;
                    break;
            }

            // If the previously selected piece & input is valid, continue.
            if (allowed) {

                // Define and find where the king piece is located based on which player
                String kingPiece = "♚";
                if (player.equals("Black")) kingPiece = "♔";
                String kingPosition = "";
                int kingRow = 0;
                int kingColumn = 0;

                // if the current piece is the king, use the given inputs to define its position
                if (piece.equals(kingPiece)) {
                    kingRow = row;
                    kingColumn = column;
                    kingPosition = rowList.get(kingRow) + kingColumn;
                }

                // If kingPosition is  still undefined, search through the entire board to find it
                if (kingPosition.equals("")) {
                    for (int i = 0; i < chessBoard.length; i++) {
                        for (int q = 0; q < chessBoard[i].length; q++) {
                            if (chessBoard[q][i].equals(kingPiece)) {
                                kingPosition = rowList.get(i) + q;
                                kingRow = i;
                                kingColumn = q;
                                break;
                            }
                        }
                    }
                }

                // define a temporary chessBoard that's a copy of the current with the users input
                String[][] chessBoardTemp = Arrays.stream(chessBoard).map(String[]::clone).toArray(String[][]::new);
                chessBoardTemp[oldColumn][oldRow] = "#";
                chessBoardTemp[column][row] = piece;

                // Check if the temporary chessboard leads the player into going into check, if so rerun the
                if (allMoves(chessBoardTemp, enemy).contains(kingPosition)) {
                    System.out.println("This move puts you in check, you may not make this move!");
                    continue;
                }

                // Check if the player is currently in check and alert them
                if (allMoves(chessBoard, enemy).contains(kingPosition)) {
                    System.out.println("You are in check!");
                }

                // If there are no problems and the users input is valid, add it to the main permanent board
                chessBoard[oldColumn][oldRow] = "#";
                chessBoard[column][row] = piece;
                System.out.println("You moved your " + player + " " + piece + " from " + rowList.get(oldRow) + oldColumn + " to " + rowList.get(row) + column + "\n");

                // After the move is over, change who is the current player and enemy of the current player
                if (player.equals("White")) {
                    player = "Black";
                    enemy = "White";
                } else {
                    player = "White";
                    enemy = "Black";
                }

                //
                action = "Pick";

                //
                // Redefining king after move
                //
                kingPiece = "♚";
                if (player.equals("Black")) kingPiece = "♔";
                for (int i = 0; i < chessBoard.length; i++) {
                    for (int q = 0; q < chessBoard[i].length; q++) {
                        if (chessBoardTemp[q][i].equals(kingPiece)) {
                            kingRow = i;
                            kingColumn = q;
                            kingPosition = rowList.get(kingRow) + kingColumn;
                            break;
                        }
                    }
                }


                //
                // Stalemate detector
                //
                List<String> allPlayerMoves = allMoves(chessBoard, player);
                List<String> allPlayerKingMoves = moveKing(kingColumn, kingRow, player, chessBoard);
                List<String> toRemove = new ArrayList<>();

                // See if any of playerKingMoves are nullified due to check, and add it to toRemove
                for (String value : allPlayerMoves) {
                    if (allPlayerKingMoves.contains(value)) toRemove.add(value);
                }

                // Remove any kingMoves that were in allPlayerMoves
                allPlayerMoves.removeAll(toRemove);

                // If no pieces can move, declare stalemate and end the game.
                if(allPlayerMoves.size() == 0) {
                    System.out.println("Stalemate! Game over");
                    break;
                }

                //
                // Checkmate Detector
                //

                List<String> moveList = allMoves(chessBoard, enemy);

                // Only run if the King is in check
                if (moveList.contains(kingPosition)) {
                    // Check if king is unable to move due to check
                    int count = allPlayerKingMoves.size() - 1;
                    for (String value : moveList) {
                        if (allPlayerKingMoves.contains(value)) count--;
                    }
                    // If the King is unable to move, or another piece is not able to stop check, declare checkmate and end the game,
                    if (count == 0 && checkMateDetector(chessBoard, player, kingPosition, kingRow, kingColumn)) {
                        System.out.println("\n\n\n\n\nGame is over! " + player + " has won!\n Good Game!");
                        break;
                    }
                }
            } else {
                System.out.println("Invalid Move! Please try again");
            }
        }
    }
}
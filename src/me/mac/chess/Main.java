package me.mac.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static me.mac.chess.Functions.*;
import static me.mac.chess.pieces.Bishop.moveBishop;
import static me.mac.chess.pieces.Castle.moveCastle;
import static me.mac.chess.pieces.Horse.moveHorse;
import static me.mac.chess.pieces.King.moveKing;
import static me.mac.chess.pieces.Pawn.movePawn;
import static me.mac.chess.pieces.Queen.moveQueen;

public final class Main {
    // Declare Main Board
     static final String[][] chessBoard = {
            {" ", "A", "B", "C", "D", "E", "F", "G", "H", " "},
            {"1", "♜", "♞", "♝", "♛", "♚", "♝", "♞", "♜", "1"},
            {"2", "♟", "♟", "#", "♟", "♟", "♟", "♟", "♟", "2"},
            {"3", "#", "#", "#", "#", "#", "#", "#", "#", "3"},
            {"4", "#", "#", "#", "#", "#", "#", "#", "#", "4"},
            {"5", "#", "#", "#", "#", "#", "#", "#", "#", "5"},
            {"6", "#", "#", "#", "#", "#", "#", "#", "#", "6"},
            {"7", "♙", "#", "#", "♙", "♙", "♙", "♙", "♙", "7"},
            {"8", "♖", "♘", "♗", "♔", "♕", "♗", "♘", "♖", "8"},
            {" ", "A", "B", "C", "D", "E", "F", "G", "H", " "}
    };

    // Public Static Variables
    public static final List<String> whitePiecesList = Arrays.asList("♟", "♜", "♞", "♝", "♛", "♚");
    public static final List<String> blackPiecesList = Arrays.asList("♙", "♖", "♘", "♗", "♕", "♔");
    public static final List<String> rowList = Arrays.asList("0", "A", "B", "C", "D", "E", "F", "G", "H");

    // Variables
    static String action = "Pick";
    static String input = "null";
    static int row = -1, column = -1, oldRow = -1, oldColumn = -1;
    static String piece = "", player = "White", enemy = "Black";
    static Boolean allowed = false;

    public static void main(String... args) {
        // Define Scanner
        Scanner scan = new Scanner(System.in);
        // Clear Console & define variables
        int initial;
        boolean active = false;
        boolean helpMenu = false;
        clearConsole();

        // Infinite loop until broken via an end rule or exiting
        while (true) {
            if(helpMenu) {
                clearConsole();
                printHelp();
                try {
                    initial = scan.nextInt();
                } catch (Exception e) {
                    scan.nextLine();
                    clearConsole();
                    printHelp();
                    continue;
                }
                if(initial == 1) {
                    helpMenu=false;
                    clearConsole();
                    scan.nextLine();
                }
                continue;
            }
            // If the game is not active, run the menu
            if(!active) {
                clearConsole();
                System.out.println("Welcome to Chess!\nPlease select an option:\n  1: Play\n  2: Help\n  3: Exit");
                try {
                    initial = scan.nextInt();
                } catch (Exception e) {
                    scan.nextLine();
                    clearConsole();
                    continue;
                }
                if(initial == 1) {
                    active=true;
                    clearConsole();
                    scan.nextLine();
                    continue;
                } else if (initial == 2) {
                    helpMenu = true;
                    continue;
                } else if (initial == 3) {
                    System.out.println("Exiting...");
                    System.exit(1);
                    break;
                }
                continue;
            }


            // Convert array to string and format it correctly
            String chessBoardString = Arrays.deepToString(chessBoard)
                    .replace("[[", "\n")
                    .replace("[", "")
                    .replace("], ", "\n")
                    .replace("]", "")
                    .replace(",", "");

            // Print out formatted string array
            clearConsole();
            System.out.println(chessBoardString);

            // Print based on current action
            if (action.equals("Pick")) {
                System.out.println("Please choose a piece you wish to use");
                System.out.println("It is " + player + "'s turn!");
            } else if (action.equals("Move")) {
                System.out.println(piece + " " + (rowList.get(oldRow) + oldColumn) + " Selected\nPlease choose a position you wish to move the piece to");
            }

            // Define current input in uppercase, check if input is valid and catch if not
            try {
                input = scan.nextLine().toUpperCase();
            }
            catch (Exception e) {
                scan.nextLine();
                sendError("Invalid Input");
                continue;
            }

            // Cancel Moving, reset action and continue loop.
            if ((input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("stop")) && action.equals("Move")) {
                System.out.println("\nMoving reset...");
                action = "Pick";
                continue;
            } else if(input.equalsIgnoreCase("help")) {
                helpMenu = true;
                continue;
            } else if(input.equalsIgnoreCase("resign") || input.equalsIgnoreCase("forfeit")) {
                clearConsole();
                System.out.println(player + " has resigned! " + enemy + " wins!");
                break;
            } else if (input.length() != 2) {
                sendError("Invalid Input");
                continue;
            }
            // Define column if matches valid int
            if (input.substring(1, 2).matches("-?\\d+(\\.\\d+)?")) {
                column = Integer.parseInt(input.substring(1, 2));

                // Check if column is less than 0 or greater than 9 are denied
                if (column <= 0 || column >= 9) {
                    sendError("Invalid Column");
                    continue;
                }
            } else {
                sendError("Invalid Column");
                continue;
            }

            // Define row if matches valid Row
            if (rowList.contains(input.substring(0, 1))) {
                row = rowList.indexOf(input.substring(0, 1));
            } else {
                sendError("Invalid Row");
                continue;
            }

            if (action.equals("Pick")) {
                piece = chessBoard[column][row];

                if (piece.equals("#")) {
                    sendError("There is no piece there!");
                    continue;
                } else if (player.equals("White") && !whitePiecesList.contains(piece)) {
                    sendError("This is not your piece!");
                    continue;
                } else if (player.equals("Black") && !blackPiecesList.contains(piece)) {
                    sendError("This is not your piece!");
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
            // See which piece is used, and if the given input is within that pieces valid, allowed moveList
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
                if (kingPosition.isEmpty()) {
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
                    sendError("This move puts you in check, you may not make this move!");
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
                // Go through every position on the board looking for the defined kingPiece
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
                    int count = allPlayerKingMoves.size();
                    for (String value : moveList) {
                        if (allPlayerKingMoves.contains(value)) count--;
                    }
                    // If the King is unable to move, or another piece is not able to stop check, declare checkmate and end the game,
                    if (count == 0 && checkMateDetector(chessBoard, player, kingPosition, kingRow, kingColumn)) {
                        chessBoardString = Arrays.deepToString(chessBoard)
                                .replace("[[", "\n")
                                .replace("[", "")
                                .replace("], ", "\n")
                                .replace("]", "")
                                .replace(",", "");
                        System.out.println("Game is over! " + player + " has won!\n Good Game!\n\nFinal Board\n" + chessBoardString);
                        break;
                    }
                }
            } else {
                sendError("Invalid Move! Please try again");
            }
        }
    }
}
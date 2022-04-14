package me.mac.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static me.mac.chess.Functions.checkMateDetector;
import static me.mac.chess.pieces.Bishop.moveBishop;
import static me.mac.chess.pieces.Castle.moveCastle;
import static me.mac.chess.pieces.Horse.moveHorse;
import static me.mac.chess.pieces.King.moveKing;
import static me.mac.chess.pieces.Pawn.movePawn;
import static me.mac.chess.pieces.Queen.moveQueen;

import static me.mac.chess.Functions.allMoves;

public class Main {
    //
    // Declare Variables
    //

    static String[][] chessBoard = {
            {" ", "A", "B", "C", "D", "E", "F", "G", "H", " "},
            {"1", "♜", "♞", "♝", "♛", "♚", "♝", "♞", "♜", "1"},
            {"2", "♟", "♟", "♟", "♟", "♟", "♟", "♟", "♟", "2"},
            {"3", "#", "#", "#", "#", "#", "#", "#", "#", "3"},
            {"4", "#", "#", "#", "#", "#", "#", "#", "#", "4"},
            {"5", "#", "#", "#", "♕", "#", "#", "#", "#", "5"},
            {"6", "#", "#", "#", "♔", "#", "#", "#", "#", "6"},
            {"7", "♙", "♙", "♙", "♙", "♙", "♙", "♙", "♙", "7"},
            {"8", "♖", "♘", "♗", "♔", "♕", "♗", "♘", "♖", "8"},
            {" ", "A", "B", "C", "D", "E", "F", "G", "H", " "}
    };

    // Lists/arrays of important items
    public static List<String> whitePiecesList = Arrays.asList("♟", "♜", "♞", "♝", "♛", "♚");
    public static List<String> blackPiecesList = Arrays.asList("♙", "♖", "♘", "♗", "♕", "♔");
    public static List<String> rowList = Arrays.asList("0", "A", "B", "C", "D", "E", "F", "G", "H");

    // Declaring Variables
    static String action = "Pick";
    static String input = "null";
    static int row = -1, column = -1, oldRow = -1, oldColumn = -1;
    static String piece = "", player = "White", enemy = "Black", tempRow = "";
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

        while (true) {
            // Convert array to string and format it correctly
            String chessBoardString = Arrays.deepToString(chessBoard)
                    .replace("[[", "\n")
                    .replace("[", "")
                    .replace("], ", "\n")
                    .replace("]", "")
                    .replace(",", "");

            System.out.println(chessBoardString);

            if (action.equals("Pick")) {
                System.out.println("Please choose a piece you wish to use");
                System.out.println("It is " + player + "'s turn!");
            } else if (action.equals("Move")) {
                System.out.println("Please choose a position you wish to move the piece to");
            }

            input = scan.nextLine().toUpperCase();
            // Allow to change piece in use by pressing Q
            if (input.equals("Q") && action.equals("Move")) {
                System.out.println("\nMoving reset...");
                action = "Pick";
                continue;
            } else if (input.length() != 2) {
                System.out.println("Wrong Input");
                continue;
            }
            // Isolate inputs

            tempRow = input.substring(0, 1);
            if (input.substring(1, 2).matches("-?\\d+(\\.\\d+)?")) {
                column = Integer.parseInt(input.substring(1, 2));
            } else {
                System.out.println("Invalid Column");
                continue;
            }

            // Check if valid row
            if (rowList.contains(tempRow)) {
                row = rowList.indexOf(tempRow);
            } else {
                System.out.println("Invalid Row");
                scan.nextLine();
                continue;
            }

            // Check if valid column
            if (column <= 0 || column >= 9) {
                System.out.println("Invalid Column");
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

                oldRow = row;
                oldColumn = column;
                action = "Move";
                continue;
            } else if (action.equals("Move")) allowed = false;

            // White Pawn
            switch (piece) {
                case "♟":
                case "♙":
                    if (movePawn(oldColumn, oldRow, player, chessBoard).contains((rowList.get(row) + column)))
                        allowed = true;
                    break;
                case "♜":
                case "♖":
                    if (moveCastle(oldColumn, oldRow, player, chessBoard).contains((rowList.get(row) + column)))
                        allowed = true;
                    break;
                case "♞":
                case "♘":
                    if (moveHorse(oldColumn, oldRow, player, chessBoard).contains(rowList.get(row) + column))
                        allowed = true;
                    break;
                case "♝":
                case "♗":
                    if (moveBishop(oldColumn, oldRow, player, chessBoard).contains(rowList.get(row) + column))
                        allowed = true;
                    break;
                case "♛":
                case "♕":
                    if (moveQueen(oldColumn, oldRow, player, chessBoard).contains(rowList.get(row) + column))
                        allowed = true;
                    break;
                case "♚":
                case "♔":
                    if (moveKing(oldColumn, oldRow, player, chessBoard).contains(rowList.get(row) + column))
                        allowed = true;
                    break;
            }
            if (allowed) {
                String kingPiece = "♚";
                if (player.equals("Black")) kingPiece = "♔";
                String kingPosition = "";
                int kingRow = 0;
                int kingColumn = 0;

                if (piece.equals(kingPiece)) {
                    kingRow = row;
                    kingColumn = column;
                    kingPosition = rowList.get(kingRow) + kingColumn;
                }

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
                if (kingPosition.equals("")) {
                    System.out.println("Couldn't find king..?");
                }

                String[][] chessBoardTemp = Arrays.stream(chessBoard).map(String[]::clone).toArray(String[][]::new);
                chessBoardTemp[oldColumn][oldRow] = "#";
                chessBoardTemp[column][row] = piece;

                if (allMoves(chessBoardTemp, enemy).contains(kingPosition)) {
                    System.out.println("This move puts you in check, you may not make this move!");
                    continue;
                }
                System.out.println(kingPosition);
                if (allMoves(chessBoard, enemy).contains(kingPosition)) {
                    System.out.println("You are in check!");
                }

                System.out.println("This execute?");
                chessBoard[oldColumn][oldRow] = "#";
                chessBoard[column][row] = piece;
                System.out.println("You moved your " + player + " " + piece + " from " + rowList.get(oldRow) + oldColumn + " to " + rowList.get(row) + column + "\n");

                if (player.equals("White")) {
                    player = "Black";
                    enemy = "White";
                } else {
                    player = "White";
                    enemy = "Black";
                }
                action = "Pick";

                //
                // Finding King
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

                // See if any of playerKingMoves are nullified due to check
                for (String value : allPlayerMoves) {
                    if (allPlayerKingMoves.contains(value)) toRemove.add(value);
                }
                allPlayerMoves.removeAll(toRemove);

                // If no pieces can move, declare stalemate.
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
                    if (count == 0 && checkMateDetector(chessBoard, player, kingPosition)) {
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
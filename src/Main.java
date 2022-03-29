import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    //
    // Declare Variables
    //
    static String[][] chessBoard = {
            {" ", "A", "B", "C", "D", "E", "F", "G", "H"},
            {"1", "♜", "♞", "♝", "♛", "♚", "♝", "♞", "♜"},
            {"2", "#", "#", "♟", "#", "♟", "♟", "♟", "#"},
            {"3", "#", "#", "#", "#", "#", "#", "#", "#"},
            {"4", "#", "#", "#", "#", "#", "#", "#", "#"},
            {"5", "#", "#", "#", "#", "#", "#", "#", "#"},
            {"6", "#", "#", "#", "#", "#", "#", "#", "#"},
            {"7", "#", "#", "♙", "♙", "♙", "♙", "#", "#"},
            {"8", "♖", "♘", "♗", "♕", "♔", "♗", "♘", "♖"}
    };

    // Lists/arrays of important items
    static List<String> whitePiecesList = Arrays.asList("♟", "♜", "♞", "♝", "♛", "♚");
    static List<String> blackPiecesList = Arrays.asList("♙", "♖", "♘", "♗", "♕", "♔");
    static String[] piecesList = {"Empty", "Pawn", "Castle", "Horse", "Bishop", "Queen", "King"};
    static List<String> rowList = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H");
    static List<String> moves = Arrays.asList();

    // Declaring Variables
    static String action = "Pick";
    static String input = "null";
    static int row = -1, column = -1, oldRow = -1, oldColumn = -1;
    static String piece = "", player = "White", tempRow = "";
    static Boolean allowed = false;

    //
    // End of Variables
    //

    //
    // Functions
    //
    static List<String> moveWhitePawn(int column, int row) {
        List<String> moveList = new ArrayList<>();
        if (chessBoard[row + 1][column].equals("#")) {
            moveList.add(rowList.get(row + 1) + (column));
        }
        if (row == 1 && chessBoard[row + 2][column].equals("#")) {
            moveList.add(rowList.get(row + 2) + (column));
        }
        if (blackPiecesList.contains(chessBoard[row + 1][column + 1])) {
            moveList.add(rowList.get(row + 1) + (column + 1));
        }
        if (blackPiecesList.contains(chessBoard[row + 1][column - 1])) {
            moveList.add(rowList.get(row + 1) + (column - 1));
        }
        System.out.println(moveList);
        return moveList;
    }
    static List<String> moveBlackPawn(int column, int row) {
        List<String> moveList = new ArrayList<>();
        if(chessBoard[row-1][column].equals("#")) {
            moveList.add(rowList.get(row-1) + (column));
        }
        if(row == 6 && chessBoard[row-2][column].equals("#")) {
            moveList.add(rowList.get(row-2) + (column));
        }
        if(whitePiecesList.contains(chessBoard[row-1][column-1])) {
            moveList.add(rowList.get(row-1) + (column-1));
        }
        if(whitePiecesList.contains(chessBoard[row-1][column+1])) {
            moveList.add(rowList.get(row-1) + (column+1));
        }
        System.out.println(moveList);
        return moveList;
    }

    static List<String> moveCastle(int column, int row, String player) {
        List<String> moveList = new ArrayList<>();

        List<String> playerArray = whitePiecesList;
        if(player.equals("Black")) playerArray = blackPiecesList;
        List<String> enemyArray = blackPiecesList;
        if(player.equals("Black"))enemyArray = whitePiecesList;

        // Check Right of castle
        for(int i=column+1; i < 8; i++) {
            if(playerArray.contains(chessBoard[row][i])) {
                break;
            } else if(enemyArray.contains(chessBoard[row][i])) {
                moveList.add(rowList.get(row) + i);
                break;
            }
            moveList.add(rowList.get(row) + i);
        }

        // Check Left of Castle
        for(int i=column-1; i >= 0 ; i--) {
            if(playerArray.contains(chessBoard[row][i])) {
                break;
            } else if(enemyArray.contains(chessBoard[row][i])) {
                moveList.add(rowList.get(row) + i);
                break;
            }
            moveList.add(rowList.get(row) + (column+1));
        }

        // Check Up
        for(int i=row+1; i < 8; i++) {
            if(playerArray.contains(chessBoard[i][column])) {
                break;
            } else if(enemyArray.contains(chessBoard[i][column])) {
                moveList.add(rowList.get(i) + column);
                break;
            }
            moveList.add(rowList.get(i) + column);
        }

        // Check Down
        for(int i=row-1; i >= 0 ; i--) {
            if(playerArray.contains(chessBoard[i][column])) {
                break;
            } else if(enemyArray.contains(chessBoard[i][column])) {
                moveList.add(rowList.get(i) + column);
                break;
            }
            moveList.add(rowList.get(i) + column);
        }
        System.out.println(moveList);
        return moveList;
    }

    // Horse
    static List<String> moveHorse(int column, int row, String player) {
        List<String> moveList = new ArrayList<>();

        List<String> playerArray = whitePiecesList;
        if(player.equals("Black")) playerArray = blackPiecesList;

        int[][] combinations = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
        int horizontal = 0;
        int vertical = 0;
        for(int i = 0; i < 8; i++) {
            horizontal = row+combinations[i][0];
            vertical = column+combinations[i][1];
            if(horizontal >= 1 && horizontal <= 7 && vertical >= 1 && vertical <= 8) {
                System.out.println("\n" + vertical + "\n" + horizontal);
                if(!playerArray.contains(chessBoard[horizontal][vertical])) moveList.add(rowList.get(horizontal) + vertical);
            }
        }
        System.out.println(moveList);
        return moveList;
    }

    // Bishop Right Up
    static List<String> moveBishop(int column, int row, String player) {
        List<String> moveList = new ArrayList<>();

        List<String> playerArray = whitePiecesList;
        if(player.equals("Black")) playerArray = blackPiecesList;
        List<String> enemyArray = blackPiecesList;
        if(player.equals("Black"))enemyArray = whitePiecesList;

        // Check Right Up of Bishop
        for(int i=column+1, q=row+1; (i <= 8 && q <= 7); i++, q++) {
            if(playerArray.contains(chessBoard[q][i])) {
                break;
            } else if(enemyArray.contains(chessBoard[q][i])) {
                moveList.add(rowList.get(q) + i);
                break;
            }
            moveList.add(rowList.get(q) + i);
        }

        // Check Left Down Bishop
        for(int i=column-1, q=row-1; (i > 0 && q >= 0); i--, q--) {
            if(playerArray.contains(chessBoard[q][i])) {
                break;
            } else if(enemyArray.contains(chessBoard[q][i])) {
                moveList.add(rowList.get(q) + i);
                break;
            }
            moveList.add(rowList.get(q) + i);
        }

        // Check Down Right
        for(int i=column-1, q=row+1; (i > 0 && q <= 7); i--, q++) {
            if(playerArray.contains(chessBoard[q][i])) {
                break;
            } else if(enemyArray.contains(chessBoard[q][i])) {
                moveList.add(rowList.get(q) + i);
                break;
            }
            moveList.add(rowList.get(q) + i);
        }

        // Check Left Up Bishop
        for(int i=column+1, q=row-1; (i <= 8 && q >= 0); i++, q--) {
            System.out.println(i + " " + q);
            if(playerArray.contains(chessBoard[q][i])) {
                break;
            } else if(enemyArray.contains(chessBoard[q][i])) {
                moveList.add(rowList.get(q) + i);
                break;
            }
            moveList.add(rowList.get(q) + i);
        }
        System.out.println(moveList);
        return moveList;
    }

    //
    // End of Functions
    //
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
            if(input == null || input == "") {
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
            if (column != 1 && column > 8) {
                System.out.println("Invalid Column");
                scan.nextLine();
                continue;
            }

            if (action.equals("Pick")) {
                piece = chessBoard[row][column];

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
            } else if (action.equals("Move")) {
                allowed = false;
            }

            // White Pawn
            if (piece.equals("♟")) {
                if(moveWhitePawn(oldColumn, oldRow).contains((rowList.get(row) + column))) allowed=true;
                // Black Pawn
            } else if (piece.equals("♙")) {
                if(moveBlackPawn(oldColumn, oldRow).contains((rowList.get(row) + column))) allowed=true;
                // Castle
            } else if (piece.equals("♜") || piece.equals("♖")) {
                if(moveCastle(oldColumn, oldRow, player).contains((rowList.get(row) + column))) allowed=true;
            } else if (piece.equals("♞") || piece.equals("♘")) {
                if(moveHorse(oldColumn, oldRow, player).contains(rowList.get(row) + column)) allowed=true;
            } else if (piece.equals("♝") || piece.equals("♗")) {
                if(moveBishop(oldColumn, oldRow, player).contains(rowList.get(row) + column)) allowed=true;
            }

            if (allowed) {
                chessBoard[oldRow][oldColumn] = "#";
                chessBoard[row][column] = piece;
                System.out.println("You moved your " + player + " " + piece + " from " + rowList.get(oldRow)
                        + oldColumn + " to " + rowList.get(row) + column + "\n");

                if (player.equals("White")) {
                    player = "Black";
                } else {
                    player = "White";
                }
                action = "Pick";

            } else {
                System.out.println("Invalid Move! Please try again");
                continue;
            }
        }
    }
}
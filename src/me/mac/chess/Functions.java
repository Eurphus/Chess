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

public class Functions {
    public static List<String> allMoves(String[][] board, String player) {
        List<String> moveList = new ArrayList<>();

        String piece;
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
        } else {
            for (int i = 0; i < board.length; i++) {
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
    public static boolean checkMateDetector(String[][] board, String player, String kingPosition) {
        String[][] boardTemp = Arrays.stream(board).map(String[]::clone).toArray(String[][]::new);
        String enemy = "Black";
        String kingPiece = "♚";
        if(player.equals("Black")) {
            enemy="White";
            kingPiece = "♔";
        }

        List<String> moveList = allMoves(board, enemy);
        String tempPiece;

        for (int i = 0; i < board.length; i++) {
            for (int q = 0; q < board[i].length; q++) {
                tempPiece = board[i][q];
                if (tempPiece.equals("#")) continue;
                if (player.equals("White")) {
                    if (!whitePiecesList.contains(tempPiece)) continue;
                    switch (tempPiece) {
                        case "♟" -> moveList = movePawn(i, q, player, board);
                        case "♜" -> moveList = moveCastle(i, q, player, board);
                        case "♞" -> moveList = moveHorse(i, q, player, board);
                        case "♝" -> moveList = moveBishop(i, q, player, board);
                        case "♛" -> moveList = moveQueen(i, q, player, board);
                        case "♚" -> moveList = moveKing(i, q, player, board);
                    }
                } else if (player.equals("Black")) {
                    if (!blackPiecesList.contains(tempPiece)) continue;
                    switch (tempPiece) {
                        case "♙" -> moveList = movePawn(i, q, player, board);
                        case "♖" -> moveList = moveCastle(i, q, player, board);
                        case "♘" -> moveList = moveHorse(i, q, player, board);
                        case "♗" -> moveList = moveBishop(i, q, player, board);
                        case "♕" -> moveList = moveQueen(i, q, player, board);
                        case "♔" -> moveList = moveKing(i, q, player, board);
                    }
                }
                boardTemp = Arrays.stream(board).map(String[]::clone).toArray(String[][]::new);
                for (String s : moveList) {
                    boardTemp[Integer.parseInt(s.substring(1, 2))][rowList.indexOf(s.substring(0, 1))] = tempPiece;
                    boardTemp[i][q] = "#";
                    if(tempPiece.equals(kingPiece)) {
                        kingPosition = s;
                    } else if(rowList.indexOf(s.substring(0, 1)) != kingRow || Integer.parseInt(kingPosition.substring(1, 2)) != kingRow) {
                        kingPosition = rowList.get(kingRow) + kingColumn;
                    }
                    if (!allMoves(boardTemp, enemy).contains(kingPosition)) {
                        return false;
                        break;
                    } else {
                        boardTemp = Arrays.stream(board).map(String[]::clone).toArray(String[][]::new);
                    }
                }
            }
        }
        return true;
    }
}

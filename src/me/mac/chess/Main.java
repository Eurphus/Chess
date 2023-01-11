package me.mac.chess;

// Java Imports

import me.mac.chess.pieces.king;

public class Main {
    public static void main(String... args) {
        chessPiece[][] chessBoard = {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, new king(1, 5, true), new king(1, 6, true), null, null, null},
                {null, null, null, null, null, new king(2, 5, false), null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
        };
        // launch(args);
        for(int x = 0; x < chessBoard.length; x++) {
            for(int y = 0; y < chessBoard[x].length; y++) {
                if(chessBoard[x][y] != null) {
                    System.out.println(chessBoard[x][y].row);
                    System.out.println(chessBoard[x][y].column);
                    System.out.println(chessBoard[x][y].moveList(chessBoard));
                }
            }
        }
    }
}
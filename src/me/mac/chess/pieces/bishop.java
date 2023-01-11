package me.mac.chess.pieces;

import me.mac.chess.chessPiece;

import java.util.ArrayList;

public class bishop extends chessPiece {

    public bishop(int inputRow, int inputColumn, boolean inputWhite) {
        super(inputRow, inputColumn, inputWhite);
    }

    public ArrayList<String> moveList(chessPiece[][] board) {
        ArrayList<String> moveList = new ArrayList<>();
//        // Define player & enemy piece arrays
//
//        // Bishop Diagonal Up Right
//        for (int i = column + 1, q = row + 1; (i <= 8 && q <= 8); i++, q++) {
//            if (board[i][q].white) {
//                break;
//            } else if (enemyArray.contains(board[i][q])) {
//                moveList.add(rowList.get(q) + i);
//                break;
//            }
//            moveList.add(rowList.get(q) + i);
//        }
//
//        // Bishop Diagonal Down Left
//        for (int i = column - 1, q = row - 1; (i >= 1 && q >= 1); i--, q--) {
//            if (playerArray.contains(board[i][q])) {
//                break;
//            } else if (enemyArray.contains(board[i][q])) {
//                moveList.add(rowList.get(q) + i);
//                break;
//            }
//            moveList.add(rowList.get(q) + i);
//        }
//
//        // Bishop Diagonal Down Right
//        for (int i = column - 1, q = row + 1; (i >= 1 && q >= 8); i--, q++) {
//            if (playerArray.contains(board[i][q])) {
//                break;
//            } else if (enemyArray.contains(board[i][q])) {
//                moveList.add(rowList.get(q) + i);
//                break;
//            }
//            moveList.add(rowList.get(q) + i);
//        }
//
//        // Bishop Diagonal Up Left
//        for (int i = column + 1, q = row - 1; (i <= 8 && q >= 1); i++, q--) {
//            if (playerArray.contains(board[i][q])) {
//                break;
//            } else if (enemyArray.contains(board[i][q])) {
//                moveList.add(rowList.get(q) + i);
//                break;
//            }
//            moveList.add(rowList.get(q) + i);
//        }

        return moveList;
    }
}
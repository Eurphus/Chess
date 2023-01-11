package me.mac.chess;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class chessPiece {
    String type;
    Image icon;
    public int row;
    public int column;
    public boolean white;
    public chessPiece(int inputRow, int inputColumn, boolean inputWhite) {
        row = inputRow;
        column = inputColumn;
        white = inputWhite;
    }
    public void updatePosition(int inputRow, int inputColumn) {
        row = inputRow;
        column = inputColumn;
    }

    public ArrayList<String> moveList(chessPiece[][] chessBoard) {
        return new ArrayList<>();
    }
}

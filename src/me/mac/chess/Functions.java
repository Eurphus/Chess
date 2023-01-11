package me.mac.chess;

public class Functions {
    public boolean isNullOrEnemy(chessPiece currentPiece, chessPiece tempPiece) {
        if(tempPiece == null) {
            return true;
        } if(tempPiece.white != currentPiece.white) {
            return true;
        }
        return false;
    }
}

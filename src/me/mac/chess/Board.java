package me.mac.chess;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Board extends Application {
    private String[][] chessArray;
    GridPane root = new GridPane();
    final int size = 8;
    ImageView pawn = new ImageView("file:pawn.png");

    /*public Board(String[][] array) {
        chessArray = array;
    }*/

    // Initalizing the menu
    public void start(Stage primaryStage) throws Exception {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Rectangle square = new Rectangle();
                Color color;
                if ((row + col) % 2 == 0) {
                    color = Color.CHOCOLATE;
                }
                else {
                    color = Color.ANTIQUEWHITE;
                }
                square.setFill(color);

                root.add(square, col, row);
                square.widthProperty().bind(root.widthProperty().divide(size));
                square.heightProperty().bind(root.heightProperty().divide(size));

            }
        }
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }
}

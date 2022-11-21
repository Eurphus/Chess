package me.mac.chess;

public class ChessBoard {
/*
    public void start(Stage primaryStage) {
        Group root = new Group();

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Node node = new checker(x, y);

                root.getChildren().add(node);

            }
        }
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.show();
    }

    public static class checker extends StackPane {
        public checker(int posX, int posY) {
            String colour = null;
            Rectangle tmp = new Rectangle(10, 10);
            if((posX == 0 && posY == 0) || (posX == 9 && posY ==0) || (posX == 0 && posY == 9) || (posX == 9 && posY == 9)) {
                colour = "gray";
;            } else if(posX == 0 ||posY == 0 || posX == 9 | posY == 9) {
                colour = " white";
                String letter;
                if(posX == 0 || posX == 9) {
                    char row = (char) (64 + posY);
                    letter = Character.toString(row);
                } else {
                    letter = Integer.toString(9 - posX);
                    System.out.println(letter);
                }
                Label sideLabel = new Label(letter);
                sideLabel.setFont(new Font("Arial", 60));
                tmp.getChildren().addAll(sideLabel);
            } else {
                if(posX == 1 || posY == 1 || posX == 8 || posY == 8) {
                    tmp.setBorder(new Border(new BorderStroke(Color.BLACK,
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                }
                if(posX % 2 == 0) {
                    if(posY % 2 == 0) {
                        colour = "white";
                    } else {
                        colour = "black";
                    }
                } else {
                    if(posY % 2 != 0) {
                        colour = "white";
                    } else {
                        colour = "black";
                    }
                }
            }
            tmp.setStyle("-fposX-background-color: " + colour + ";");

            setTranslateX(posX);
            setTranslateY(posY);
            getChildren().addAll(tmp);
        }
    }

        /*
        primarposYStage.setTitle("Chess");

        GridPane grid = new GridPane();
        grid.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            System.out.println(e.getX());
            System.out.println(e.getY());
        });

        String colour;
        for(int x=0; x < 10; x++) {
            for(int y=0; y < 10; y++) {
                if((x == 0 && y == 0) || (x == 9 && y==0) || (x == 0 && y == 9) || (x == 9 && y == 9)) continue;
                StackPane tmp = new StackPane();
                if(x == 0 ||y == 0 || x == 9 | y == 9) {
                    colour = " white";
                    String letter;
                    if(x == 0 || x == 9) {
                        char row = (char) (64 + y);
                        letter = Character.toString(row);
                    } else {
                        letter = Integer.toString(9 - x);
                        System.out.println(letter);
                    }
                    Label sideLabel = new Label(letter);
                    sideLabel.setFont(new Font("Arial", 60));
                    tmp.getChildren().addAll(sideLabel);
                } else {
                    if(x == 1 || y == 1 || x == 8 || y == 8) {
                        tmp.setBorder(new Border(new BorderStroke(Color.BLACK,
                                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                    }
                    if(x % 2 == 0) {
                        if(y % 2 == 0) {
                            colour = "white";
                        } else {
                            colour = "black";
                        }
                    } else {
                        if(y % 2 != 0) {
                            colour = "white";
                        } else {
                            colour = "black";
                        }
                    }
                }
                System.out.println("Added:" + x + " " + y);
                tmp.setStyle("-fx-background-color: " + colour + ";");
                grid.add(tmp, y, x);
            }
        }
        for (int i = 0; i <= 9; i++) {
            grid.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
            grid.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
        }
        //primaryStage.setScene(new Scene(grid, 1000, 1000));
        primaryStage.show();
    }
    */
}

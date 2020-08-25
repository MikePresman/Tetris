package View;

import GameController.Block;
import GameController.PieceAbstraction;
import Pieces.SquarePiece;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;

public class MainBoard {
    private Pane canvas;
    private int BOARD_HEIGHT = 500;
    private int BOARD_WIDTH = 300;
    public final int TILE_WIDTH = BOARD_WIDTH / 10;
    public final int TILE_HEIGHT = BOARD_HEIGHT / 16;

    public final int LEFT_MARGIN = 0;
    public final int RIGHT_MARGIN = BOARD_WIDTH - TILE_WIDTH;
    public final int BOTTOM_MARGIN = BOARD_HEIGHT - TILE_HEIGHT;

    public PieceAbstraction livePiece = null;
    public final ArrayList<Character> VALID_KEYS = new ArrayList<>(){{ add('W'); add('A'); add('S'); add('D'); } };



    public MainBoard(Pane canvas) {
        this.canvas = canvas;
    }


    public void handleKeyPress(String keyPressed){
        keyPressed = keyPressed.toUpperCase();
        if (this.livePiece != null && this.VALID_KEYS.contains(keyPressed.charAt(0))){
            System.out.println(keyPressed.charAt(0));
            this.livePiece.movementHandler(keyPressed);
        }
    }

    public void drawBoard(){
        Platform.runLater(() -> {
            this.canvas.getChildren().clear();

            int yPosition = 0;
            for (int i = 0; i < 16; i++) {
                int xPosition = 0;
                for (int j = 0; j < 10; j++) {
                    Rectangle r = new Rectangle(xPosition, yPosition, TILE_WIDTH, TILE_HEIGHT);
                    r.setStroke(Color.BLACK);
                    r.setFill(Color.TRANSPARENT);
                    this.canvas.getChildren().add(r);
                    if (xPosition < BOARD_WIDTH) {
                        xPosition += TILE_WIDTH;
                    } else {
                        break;
                    }
                }
                yPosition += TILE_HEIGHT;
            }
            
            drawPiece();
        });
    }

    public void drawPiece(){
        for (Block block : this.livePiece.pieceConstructed.blockContainer){
            int yPosition = (int) block.Y_POSITION;
            int xPosition = (int) block.X_POSITION;
            int width = (int) block.WIDTH;
            int height = (int) block.HEIGHT;
            Color color = this.livePiece.color;
            Rectangle r = new Rectangle(xPosition, yPosition, width, height);
            r.setFill(color);
            r.setStroke(Color.WHITE);
            this.canvas.getChildren().add(r);

        }
    }


    public void update(){
        if (this.livePiece == null){
            this.livePiece = new SquarePiece(this.TILE_WIDTH, this.TILE_HEIGHT, this, this.canvas);
        }

        /*add later for random pieces
          if (!this.firstPieceSelected) {
        piece = this.getPiece();
      } else {
        piece = this.nextPiece;
      }
         */

        if (!this.livePiece.hitBottom)
            drawBoard();
        //TODO : BOARDMATRIX
        if (this.livePiece.hitBottom){
            this.livePiece = null;
        }



        this.livePiece.updateBoardPosition();
    }











}

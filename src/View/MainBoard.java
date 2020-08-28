package View;

import GameController.Block;
import GameController.BoardLogic;
import GameController.PieceAbstraction;
import GameController.TetrisPiece;
import Pieces.*;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class MainBoard {
    public Pane canvas;
    private int BOARD_HEIGHT = 400; //keep this divisible by 16 nicely
    private int BOARD_WIDTH = 250; //keep this divisble by 10 nicely
    public final int TILE_WIDTH = BOARD_WIDTH / 10;
    public final int TILE_HEIGHT = BOARD_HEIGHT / 16;

    
    public final int LEFT_MARGIN = 0;
    public final int RIGHT_MARGIN = BOARD_WIDTH - TILE_WIDTH;
    public final int BOTTOM_MARGIN = BOARD_HEIGHT - TILE_HEIGHT;

    public PieceAbstraction livePiece = null;
    public final ArrayList<Character> VALID_KEYS = new ArrayList<>(){{ add('W'); add('A'); add('S'); add('D'); } };

    public BoardLogic boardLogic;


    public MainBoard(Pane canvas) {
        this.canvas = canvas;
        this.boardLogic = new BoardLogic(this.TILE_HEIGHT, this.TILE_WIDTH);
    }


    public void handleKeyPress(String keyPressed){
        keyPressed = keyPressed.toUpperCase();
        if (this.livePiece != null && this.VALID_KEYS.contains(keyPressed.charAt(0))){
            this.livePiece.movementHandler(keyPressed);
            if (this.livePiece.hitBottom){
                this.boardLogic.addPieceToBoard(this.livePiece);
                this.boardLogic.handleRowCleanup();
                this.livePiece = null;
                return;
            }
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
            drawBlocksSet();

        });
    }

    public void drawBlocksSet(){
        for (int row = 0; row < this.boardLogic.boardMatrix.length; row++){
            for (int tile = 0; tile < this.boardLogic.boardMatrix[row].length; tile++){
                if (this.boardLogic.boardMatrix[row][tile] != null){
                    Block b = this.boardLogic.boardMatrix[row][tile];
                    Rectangle r = new Rectangle(b.X_POSITION,b.Y_POSITION,b.WIDTH,b.HEIGHT);
                    r.setStroke(Color.WHITE);
                    r.setFill(b.COLOR);
                    this.canvas.getChildren().add(r);
                }
            }
        }
    }

    public void drawPiece(){
        for (Block block : this.livePiece.pieceConstructed.blockContainer){
            Rectangle r = new Rectangle(block.X_POSITION, block.Y_POSITION, block.WIDTH, block.HEIGHT);
            r.setFill(block.COLOR);
            r.setStroke(Color.WHITE);
            this.canvas.getChildren().add(r);

        }
    }


    public void update(){
        if (this.livePiece == null){
            this.livePiece = new TPiece(this);
            drawBoard();
            return;
        }


        //TODO upcoming pieces
        //TODO random

        //TODO
        /*add later for random pieces
          if (!this.firstPieceSelected) {
        piece = this.getPiece();
      } else {
        piece = this.nextPiece;
      }
         */


        if (!this.livePiece.hitBottom)
            this.livePiece.updateBoardPosition();


        if (this.livePiece.hitBottom){
            this.boardLogic.addPieceToBoard(this.livePiece);
            this.boardLogic.handleRowCleanup();
            this.livePiece = null;
            return;
        }

    }
}

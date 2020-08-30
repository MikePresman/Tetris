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
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainBoard {
    public Pane canvas;
    private int BOARD_HEIGHT = 400; //keep this divisible by 16 nicely
    private int BOARD_WIDTH = 250; //keep this divisble by 10 nicely
    public final int TILE_WIDTH = BOARD_WIDTH / 10;
    public final int TILE_HEIGHT = BOARD_HEIGHT / 16;
    public final int LEFT_MARGIN = 0;
    public final int RIGHT_MARGIN = BOARD_WIDTH - TILE_WIDTH;
    public final int BOTTOM_MARGIN = BOARD_HEIGHT - TILE_HEIGHT;
    public BoardLogic boardLogic;


    public PieceAbstraction livePiece = null;
    public final ArrayList<Character> VALID_KEYS = new ArrayList<>(){{ add('W'); add('A'); add('S'); add('D'); } };
    private boolean firstSelectedPiece = false;
    private PieceAbstraction nextPiece;

    private MainBoard thisBoard = this;
    private int[] lastTwoPieces = {-1, -1};


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

            //nextPieceArena
            nextPieceArena();

            drawPiece();
            drawBlocksSet();

        });
    }

    public void nextPieceArena(){
          //Drawing arena space
          Rectangle r = new Rectangle(BOARD_WIDTH, 0, 400 - BOARD_WIDTH, BOARD_HEIGHT);
          r.setFill(Color.BLACK);
          r.setStroke(Color.WHITE);
          this.canvas.getChildren().add(r);

          //nextPiecePosition
            for (Block block : this.nextPiece.pieceConstructed.blockContainer){
                double xCenter = r.getX() + r.getX() / 4;
                double yCenter = r.getY() + r.getY() / 4;
                Rectangle nextPiece = new Rectangle(r.getX() / 1.2 + block.X_POSITION, block.Y_POSITION + 15,block.WIDTH,block.HEIGHT);
                nextPiece.setFill(block.COLOR);
                nextPiece.setStroke(Color.WHITE);
                this.canvas.getChildren().add(nextPiece);
        }



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
            PieceAbstraction piece;
            if (!this.firstSelectedPiece){
                piece = this.getPiece();
            }else{
                piece = this.nextPiece;
            }

            this.livePiece = piece;
            this.firstSelectedPiece = true;
            this.nextPiece = this.getPiece();

            drawBoard();
            return;
        }else {

            if (!this.livePiece.hitBottom)
                this.livePiece.updateBoardPosition();


            if (this.livePiece.hitBottom) {
                this.boardLogic.addPieceToBoard(this.livePiece);
                this.boardLogic.handleRowCleanup();
                this.livePiece = null;
                return;
            }
        }
    }

    public PieceAbstraction getPiece(){
        HashMap<Integer, PieceAbstraction> pieces = new HashMap<>(){
            {
                put(1, new LeftLPiece(thisBoard));
                put(2, new RightLPiece(thisBoard));
                put(3, new SquarePiece(thisBoard));
                put(4, new RightZPiece(thisBoard));
                put(5, new LeftZPiece(thisBoard));
                put(6, new TPiece(thisBoard));
                put(7, new StickPiece(thisBoard));
            }
        };

        //lastTwoPieces is empty
        if (this.lastTwoPieces[1] == -1){
            Random ran = new Random();
            int RANDOM_NUM =  ran.nextInt((7-1)+1)+1;
            this.lastTwoPieces[1] = RANDOM_NUM;// [-1, x]
            return pieces.get(RANDOM_NUM);
        }

        Random ran = new Random();
        int RANDOM_NUM = 0;
        do{
            RANDOM_NUM = ran.nextInt((7-1)+1)+1;
        }while (this.lastTwoPieces[0] == RANDOM_NUM || this.lastTwoPieces[1] == RANDOM_NUM);


        //lastTwoPieces only has one piece
        if (this.lastTwoPieces[0] == -1){
            this.lastTwoPieces[0] = RANDOM_NUM;
            return pieces.get(RANDOM_NUM);
        }else{ //if both spaces are full
            this.lastTwoPieces[1] = this.lastTwoPieces[0];
            this.lastTwoPieces[0] = RANDOM_NUM;
            return pieces.get(RANDOM_NUM);
        }
    }
}

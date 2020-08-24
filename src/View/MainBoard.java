package View;

import GameController.Block;
import GameController.PieceAbstraction;
import Pieces.SquarePiece;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;

public class MainBoard {
    private Pane canvas;
    private int BOARD_HEIGHT = 500;
    private int BOARD_WIDTH = 300;
    public PieceAbstraction livePiece = null;

    public final int TILE_WIDTH = BOARD_WIDTH / 10;
    public final int TILE_HEIGHT = BOARD_HEIGHT / 16;


    public MainBoard(Pane canvas) {
        this.canvas = canvas;
        this.drawBoard();
    }

    public void drawBoard(){
        int yPosition = 0;
        for (int i = 0; i < 16; i++) {
            int xPosition = 0;
            for (int j = 0; j < 10; j++) {
                Rectangle r = new Rectangle(xPosition, yPosition, TILE_WIDTH, TILE_HEIGHT);
                r.setStroke(Color.BLACK);
                r.setFill(Color.TRANSPARENT);
                this.canvas.getChildren().add(r);
                if (xPosition < BOARD_WIDTH){
                    xPosition+=TILE_WIDTH;
                }else{
                    break;
                }
            }
            yPosition+=TILE_HEIGHT;
        }
    }

    public void drawPiece(){

    //bug because we are removing last elementwhich is square
        Platform.runLater(() -> {
            this.canvas.getChildren().remove(canvas.getChildren().get(this.canvas.getChildren().size() - 1));
        for (HashMap<Block, Object> block : this.livePiece.pieceConstructed.blockContainer){
            int yPosition = (int) block.get(Block.Y_POSITION);
            int xPosition = (int) block.get(Block.X_POSITION);
            int width = (int) block.get(Block.WIDTH);
            int height = (int) block.get(Block.HEIGHT);
            Color color = this.livePiece.color;

            Rectangle r = new Rectangle(xPosition, yPosition, width, height);
            r.setFill(color);
            this.canvas.getChildren().add(r);
            }
        });
    }


    public void update(){
        if (this.livePiece == null){
            this.livePiece = new SquarePiece(this.TILE_WIDTH, this.TILE_HEIGHT, this.canvas);
        }

        drawPiece();



        this.livePiece.updateBoardPosition();
    }











}

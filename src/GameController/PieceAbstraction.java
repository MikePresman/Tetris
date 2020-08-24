package GameController;

import View.MainBoard;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;

class RotationProperties{
    boolean isRotatable;
    Block axisOfRotation;
}

public abstract class PieceAbstraction {
    public TetrisPiece pieceConstructed;
    public boolean pieceInitalized = false;
    public boolean hitBottom = false;
    public Color color;
    public Pane canvas;
    public int TILE_WIDTH;
    public int TILE_HEIGHT;
    public MainBoard board;

    public RotationProperties pieceRotationProperties;

    public TetrisPiece getPiece(){return this.pieceConstructed;}

    public abstract void updateBoardPosition();
    public abstract ArrayList<HashMap<Block, Object>> initializePieces();


    public void movementHandler(String key){
        switch (key) {
            case "A":
                leftMovement();
                break;
            case "S":
                downMovement();
                break;
            case "D":
                rightMovement();
                break;
            case "W":
                rotatePiece();
                break;
            default:
                break;
        }
    }

    //TODO
    private void leftMovement(){
        //Bounds check
        for (HashMap<Block, Object> block : this.pieceConstructed.blockContainer){
            if ((int) block.get(Block.X_POSITION) - this.TILE_WIDTH < this.board.LEFT_MARGIN){
                return;
            }
        }


        //Collision with board check


        //movement
        genericMovement(Block.X_POSITION, - this.TILE_WIDTH);
    }


    //TODO
    private void rightMovement(){
        //Bounds check
        for (HashMap<Block, Object> block : this.pieceConstructed.blockContainer){
            if ((int) block.get(Block.X_POSITION) + this.TILE_WIDTH > this.board.RIGHT_MARGIN){
                return;
            }
        }

        //Collision with board check


        //movement
        genericMovement(Block.X_POSITION, this.TILE_WIDTH);


    }

    public void downMovement(){
        //Bounds check
        for (HashMap<Block, Object> block : this.pieceConstructed.blockContainer){
            if ((int) block.get(Block.Y_POSITION) + this.TILE_HEIGHT > this.board.BOTTOM_MARGIN){
                return;
            }
        }

        //collision with board check

        genericMovement(Block.Y_POSITION, this.TILE_HEIGHT);

    }

    public void genericMovement(Block blockPos, int movementAddition){
        for (int i = 0; i < this.pieceConstructed.amountOfBlocks; i++){
            int updatedX = (int) this.pieceConstructed.blockContainer.get(i).getOrDefault(blockPos, 0) + movementAddition;
            this.pieceConstructed.blockContainer.get(i).put(blockPos, updatedX);
        }
        this.board.drawBoard();
    }


    private void rotatePiece(){

    }



}

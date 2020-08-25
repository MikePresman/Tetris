package GameController;

import View.MainBoard;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

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
    public abstract ArrayList<Block> initializePieces();


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
        for (Block block : this.pieceConstructed.blockContainer){
            if (block.X_POSITION - this.TILE_WIDTH < this.board.LEFT_MARGIN){
                return;
            }
        }

        //Collision with board check
        for (Block b : this.pieceConstructed.blockContainer){
            int blockRowIndex = board.boardLogic.getMatrixY(b.Y_POSITION);
            int blockTileIndex = board.boardLogic.getMatrixX(b.X_POSITION);

            if (board.boardLogic.boardMatrix[blockRowIndex][blockTileIndex - 1] != null){
                return;
            }
        }






        //MOVEMENT
        for (int i = 0; i < this.pieceConstructed.amountOfBlocks; i++){
            int updatedX =  this.pieceConstructed.blockContainer.get(i).X_POSITION - this.TILE_WIDTH;
            this.pieceConstructed.blockContainer.get(i).X_POSITION = updatedX;
        }
        this.board.drawBoard();



    }


    //TODO
    private void rightMovement(){
        //Bounds check
        for (Block block : this.pieceConstructed.blockContainer){
            if (block.X_POSITION + this.TILE_WIDTH > this.board.RIGHT_MARGIN){
                return;
            }
        }

        //Collision with board check
        for (Block b : this.pieceConstructed.blockContainer){
            int blockRowIndex = board.boardLogic.getMatrixY(b.Y_POSITION);
            int blockTileIndex = board.boardLogic.getMatrixX(b.X_POSITION);

            if (board.boardLogic.boardMatrix[blockRowIndex][blockTileIndex + 1] != null){
                return;
            }
        }



        //movement
        for (int i = 0; i < this.pieceConstructed.amountOfBlocks; i++){
            int updatedX = this.pieceConstructed.blockContainer.get(i).X_POSITION + this.TILE_WIDTH;
            this.pieceConstructed.blockContainer.get(i).X_POSITION = updatedX;
        }
        this.board.drawBoard();


    }

    public void downMovement(){
        //Bounds check
        for (Block block : this.pieceConstructed.blockContainer){
            if (block.Y_POSITION + this.TILE_HEIGHT > this.board.BOTTOM_MARGIN){
                this.hitBottom = true;
                return;
            }
        }

        //Collision with board check
        for (Block b : this.pieceConstructed.blockContainer){
            int blockRowIndex = board.boardLogic.getMatrixY(b.Y_POSITION);
            int blockTileIndex = board.boardLogic.getMatrixX(b.X_POSITION);

            if (board.boardLogic.boardMatrix[blockRowIndex+1][blockTileIndex] != null){
                this.hitBottom = true;
                return;
            }
        }


        //collision with board check
        for (int i = 0; i < this.pieceConstructed.amountOfBlocks; i++){
            int updatedX = this.pieceConstructed.blockContainer.get(i).Y_POSITION + this.TILE_HEIGHT;
            this.pieceConstructed.blockContainer.get(i).Y_POSITION = updatedX;
        }



        this.board.drawBoard();

    }




    private void rotatePiece(){

    }



}

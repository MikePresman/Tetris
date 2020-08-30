package GameController;

import View.MainBoard;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class PieceAbstraction {
    public TetrisPiece pieceConstructed;
    public boolean pieceInitalized = false;
    public boolean hitBottom = false;
    public Color color;
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

    private void leftMovement(){
        //Bounds check
        for (Block block : this.pieceConstructed.blockContainer){
            if (block.X_POSITION - this.board.TILE_WIDTH < this.board.LEFT_MARGIN){
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
            int updatedX =  this.pieceConstructed.blockContainer.get(i).X_POSITION - this.board.TILE_WIDTH;
            this.pieceConstructed.blockContainer.get(i).X_POSITION = updatedX;
        }
        this.board.drawBoard();



    }

    private void rightMovement(){
        //Bounds check
        for (Block block : this.pieceConstructed.blockContainer){
            if (block.X_POSITION + this.board.TILE_WIDTH > this.board.RIGHT_MARGIN){
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
            int updatedX = this.pieceConstructed.blockContainer.get(i).X_POSITION + this.board.TILE_WIDTH;
            this.pieceConstructed.blockContainer.get(i).X_POSITION = updatedX;
        }
        this.board.drawBoard();


    }

    public void downMovement(){


        //Bounds check
        for (Block block : this.pieceConstructed.blockContainer){
            if (block.Y_POSITION + this.board.TILE_HEIGHT > this.board.BOTTOM_MARGIN){
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
            int updatedX = this.pieceConstructed.blockContainer.get(i).Y_POSITION + this.board.TILE_HEIGHT;
            this.pieceConstructed.blockContainer.get(i).Y_POSITION = updatedX;
        }



        this.board.drawBoard();

    }


    private void rotatePiece(){
        if (!this.pieceRotationProperties.isRotatable) return;
        if (this.hitBottom) return;

        //Making a copy incase rotation doesnt work out somewhere
        //implicilty this is the type for which we copy although we abstractly define it as PieceAbstraction type

        //check left and right bounds

        //TODO : fix this it doesn't work exactly as intended

        for (Block block : this.pieceConstructed.blockContainer){
            if (block.X_POSITION + this.board.TILE_WIDTH > this.board.RIGHT_MARGIN){
                return;
            }
            if (block.X_POSITION - this.board.TILE_WIDTH < this.board.LEFT_MARGIN){
                System.out.println(this.board.LEFT_MARGIN);
                System.out.println(block.X_POSITION);
                return;
            }
        }




        final TetrisPiece originalPiece = new TetrisPiece(this.pieceConstructed.amountOfBlocks, this.pieceConstructed.blockContainer, this.pieceConstructed.color);

        for (Block block : this.pieceConstructed.blockContainer){
            final int[] blockVector = this.board.boardLogic.vectorizePoint(block);
            final int[] axisOfRotationBlockVector = this.board.boardLogic.vectorizePoint(this.pieceRotationProperties.axisOfRotation);

            final int newVectorY = blockVector[0]  - axisOfRotationBlockVector[0];
            final int newVectorX = blockVector[1]  - axisOfRotationBlockVector[1];
            final int[] newVector = {newVectorY, newVectorX};

            final int[] dotProduct = this.rotateBy90(newVector);
            final int[] finalVector = {axisOfRotationBlockVector[0] + dotProduct[0], axisOfRotationBlockVector[1] + dotProduct[1]};

            if (!this.matrixSpaceAvailable(finalVector)){
                this.pieceConstructed = originalPiece;
                return;
            }

            int canvasY = finalVector[0] * this.board.TILE_HEIGHT;
            int canvasX = finalVector[1] * this.board.TILE_WIDTH;

            if (canvasY > this.board.BOTTOM_MARGIN || canvasX < 0 || canvasX > this.board.RIGHT_MARGIN){
                this.pieceConstructed = originalPiece;
                return;
            }

            block.X_POSITION = canvasX;
            block.Y_POSITION = canvasY;


        }
        this.board.drawBoard();
    }

    private int[] rotateBy90(int[] vector){
        return new int[]{vector[0] * 0 + vector[1] * -1, vector[0] * 1 + vector[1] * 0};

    }

    private boolean matrixSpaceAvailable(int[] matrixSpace){
        int y = matrixSpace[0];
        int x = matrixSpace[1];

        try{
            if (this.board.boardLogic.boardMatrix[y][x] != null){
                return false;
            }
        }catch (ArrayIndexOutOfBoundsException e){
            return false;
        }

        return true;
    }


}

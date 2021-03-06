package Pieces;

import GameController.Block;
import GameController.PieceAbstraction;
import GameController.RotationProperties;
import GameController.TetrisPiece;
import View.MainBoard;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public final class SquarePiece extends PieceAbstraction {
    private Block topRightBlock;
    private Block topLeftBlock;
    private Block bottomLeftBlock;
    private Block bottomRightBlock;

    @Override
    public ArrayList<Block> initializePieces(){
        int tWidth = this.board.TILE_WIDTH;
        int tHeight = this.board.TILE_HEIGHT;
        Color c = this.color;

        this.topRightBlock = new Block(0,  tWidth * 5, tWidth, tHeight, c);

        this.topLeftBlock = new Block(0, tWidth * 4, tWidth, tHeight, c);

        this.bottomLeftBlock = new Block(tHeight, tWidth * 4, tWidth, tHeight, c);

        this.bottomRightBlock =  new Block(tHeight, tWidth * 5, tWidth, tHeight, c);

        return new ArrayList<>(){
            {
                add(topRightBlock);
                add(topLeftBlock);
                add(bottomLeftBlock);
                add(bottomRightBlock);
            }
        };
    }

    public SquarePiece(MainBoard board){
        this.color = Color.RED;
        this.board = board;

        this.pieceInitalized = true;

        ArrayList<Block> pieces = this.initializePieces();
        this.pieceRotationProperties = new RotationProperties(false, null);
        this.pieceConstructed = new TetrisPiece(4, pieces, this.color);
    }



    @Override
    public void updateBoardPosition() {
       if (this.pieceInitalized)
           super.downMovement();
    }
}

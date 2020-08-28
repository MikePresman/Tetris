package Pieces;

import GameController.Block;
import GameController.PieceAbstraction;
import GameController.RotationProperties;
import GameController.TetrisPiece;
import View.MainBoard;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public final class RightZPiece extends PieceAbstraction {
    private Block topRightBlock;
    private Block topMiddleBlock;
    private Block bottomMiddleBlock;
    private Block bottomRightBlock;

    @Override
    public ArrayList<Block> initializePieces(){
        int tWidth = this.board.TILE_WIDTH;
        int tHeight = this.board.TILE_HEIGHT;
        Color c = this.color;

        this.topRightBlock = new Block(0, tWidth * 6, tWidth, tHeight, c);

        this.topMiddleBlock = new Block(0, tWidth * 5, tWidth, tHeight, c);

        this.bottomMiddleBlock = new Block(tHeight, tWidth * 5, tWidth, tHeight, c);

        this.bottomRightBlock =  new Block(tHeight, tWidth * 4, tWidth, tHeight, c);

        return new ArrayList<>(){
            {
                add(topRightBlock);
                add(topMiddleBlock);
                add(bottomMiddleBlock);
                add(bottomRightBlock);
            }
        };
    }

    public RightZPiece(MainBoard board){
        this.board = board;
        this.color = Color.PURPLE;


        this.pieceInitalized = true;
        ArrayList<Block> pieces = this.initializePieces();
        this.pieceRotationProperties = new RotationProperties(true, this.bottomMiddleBlock);
        this.pieceConstructed = new TetrisPiece(4, pieces, this.color);
    }


    @Override
    public void updateBoardPosition() {
        if (this.pieceInitalized)
            super.downMovement();
    }
}




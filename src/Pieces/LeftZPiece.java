package Pieces;

import GameController.Block;
import GameController.PieceAbstraction;
import GameController.RotationProperties;
import GameController.TetrisPiece;
import View.MainBoard;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public final class LeftZPiece extends PieceAbstraction {
    private Block topLeftBlock;
    private Block topMiddleBlock;
    private Block bottomMiddleBlock;
    private Block bottomRightBlock;

    @Override
    public ArrayList<Block> initializePieces(){
        int tWidth = this.board.TILE_WIDTH;
        int tHeight = this.board.TILE_HEIGHT;
        Color c = this.color;

        this.topLeftBlock = new Block(0, tWidth * 3, tWidth, tHeight, c);

        this.topMiddleBlock = new Block(0, tWidth * 4, tWidth, tHeight, c);

        this.bottomMiddleBlock = new Block(tHeight, tWidth * 4, tWidth, tHeight, c);

        this.bottomRightBlock =  new Block(tHeight, tWidth * 5, tWidth, tHeight, c);

        return new ArrayList<>(){
            {
                add(topLeftBlock);
                add(topMiddleBlock);
                add(bottomMiddleBlock);
                add(bottomRightBlock);
            }
        };
    }

    public LeftZPiece(MainBoard board){
        this.board = board;
        this.color = Color.BLUE;


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


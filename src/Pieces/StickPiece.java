package Pieces;

import GameController.Block;
import GameController.PieceAbstraction;
import GameController.RotationProperties;
import GameController.TetrisPiece;
import View.MainBoard;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public final class StickPiece extends PieceAbstraction {
    private Block topBlock;
    private Block secondBlock;
    private Block middleBlock;
    private Block bottomBlock;

    @Override
    public ArrayList<Block> initializePieces(){
        int tWidth = this.board.TILE_WIDTH;
        int tHeight = this.board.TILE_HEIGHT;
        Color c = this.color;

        this.topBlock = new Block(0, tWidth * 4, tWidth, tHeight, c);

        this.secondBlock = new Block(tHeight, tWidth * 4, tWidth, tHeight, c);

        this.middleBlock = new Block(tHeight  * 2, tWidth * 4, tWidth, tHeight, c);

        this.bottomBlock =  new Block(tHeight * 3, tWidth * 4, tWidth, tHeight, c);

        return new ArrayList<>(){
            {
                add(topBlock);
                add(secondBlock);
                add(middleBlock);
                add(bottomBlock);
            }
        };
    }

    public StickPiece(MainBoard board){
        this.board = board;
        this.color = Color.TEAL;


        this.pieceInitalized = true;
        ArrayList<Block> pieces = this.initializePieces();
        this.pieceRotationProperties = new RotationProperties(true, this.middleBlock);
        this.pieceConstructed = new TetrisPiece(4, pieces, this.color);
    }


    @Override
    public void updateBoardPosition() {
        if (this.pieceInitalized)
            super.downMovement();
    }
}


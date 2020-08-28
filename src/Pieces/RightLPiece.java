package Pieces;

import GameController.Block;
import GameController.PieceAbstraction;
import GameController.RotationProperties;
import GameController.TetrisPiece;
import View.MainBoard;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class RightLPiece extends PieceAbstraction {
    private Block topBlock;
    private Block bottomLeftBlock;
    private Block bottomCenterBlock;
    private Block bottomRightBlock;

    @Override
    public ArrayList<Block> initializePieces(){
        int tWidth = this.board.TILE_WIDTH;
        int tHeight = this.board.TILE_HEIGHT;
        Color c = this.color;

        this.topBlock = new Block(0, tWidth * 5, tWidth, tHeight, c);

        this.bottomLeftBlock = new Block(tHeight, tWidth * 3, tWidth, tHeight, c);

        this.bottomCenterBlock = new Block(tHeight, tWidth * 4, tWidth, tHeight, c);

        this.bottomRightBlock =  new Block(tHeight, tWidth * 5, tWidth, tHeight, c);

        return new ArrayList<>(){
            {
                add(topBlock);
                add(bottomCenterBlock);
                add(bottomLeftBlock);
                add(bottomRightBlock);
            }
        };
    }

    public RightLPiece(MainBoard board){
        this.board = board;
        this.color = Color.ORANGE;


        this.pieceInitalized = true;
        ArrayList<Block> pieces = this.initializePieces();
        this.pieceRotationProperties = new RotationProperties(true, this.bottomCenterBlock);
        this.pieceConstructed = new TetrisPiece(4, pieces, this.color);
    }

    @Override
    public void updateBoardPosition() {
        if (this.pieceInitalized)
            super.downMovement();
    }
}


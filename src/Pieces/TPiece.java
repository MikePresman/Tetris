package Pieces;

import GameController.Block;
import GameController.PieceAbstraction;
import GameController.RotationProperties;
import GameController.TetrisPiece;
import View.MainBoard;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public final class TPiece extends PieceAbstraction {
    private Block topBlock;
    private Block bottomLeftBlock;
    private Block bottomCenterBlock;
    private Block bottomRightBlock;

    @Override
    public ArrayList<Block> initializePieces(){
        int tWidth = this.board.TILE_WIDTH;
        int tHeight = this.board.TILE_HEIGHT;
        Color c = this.color;

        this.topBlock = new Block(0, tWidth * 4, tWidth, tHeight, Color.RED);

        this.bottomLeftBlock = new Block(tHeight, tWidth * 3, tWidth, tHeight, Color.BLUE);

        this.bottomCenterBlock = new Block(tHeight, tWidth * 4, tWidth, tHeight, Color.GREEN);

        this.bottomRightBlock =  new Block(tHeight, tWidth * 5, tWidth, tHeight, c);

        return new ArrayList<>(){
            {
                add(topBlock);
                add(bottomLeftBlock);
                add(bottomCenterBlock);
                add(bottomRightBlock);
            }
        };
    }

    public TPiece(MainBoard board){
        this.board = board;
        this.color = Color.AQUA;


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


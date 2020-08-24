package Pieces;

import GameController.Block;
import GameController.PieceAbstraction;
import GameController.TetrisPiece;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class SquarePiece extends PieceAbstraction {
    private HashMap<Block, Object> topRightBlock;
    private HashMap<Block, Object> topLeftBlock;
    private HashMap<Block, Object> bottomLeftBlock;
    private HashMap<Block, Object> bottomRightBlock;

    @Override
    public ArrayList<HashMap<Block, Object>> initializePieces(){
        int tWidth = this.TILE_WIDTH;
        int tHeight = this.TILE_HEIGHT;
        Color c = this.color;
        this.topRightBlock = new HashMap<>(){
            {
                put(Block.Y_POSITION, 0);
                put(Block.X_POSITION, tWidth * 5);
                put(Block.WIDTH, tWidth);
                put(Block.HEIGHT, tHeight);
                put(Block.COLOR, c);
            }
        };

        this.topLeftBlock = new HashMap<>(){
            {
                put(Block.Y_POSITION, 0);
                put(Block.X_POSITION, tWidth * 4);
                put(Block.WIDTH, tWidth);
                put(Block.HEIGHT, tHeight);
                put(Block.COLOR, c);
            }
        };

        this.bottomLeftBlock = new HashMap<>(){
            {
                put(Block.Y_POSITION, tHeight);
                put(Block.X_POSITION, tWidth * 4);
                put(Block.WIDTH, tWidth);
                put(Block.HEIGHT, tHeight);
                put(Block.COLOR, c);
            }
        };

        this.bottomRightBlock = new HashMap<>(){
            {
                put(Block.Y_POSITION, tHeight);
                put(Block.X_POSITION, tWidth * 5);
                put(Block.WIDTH, tWidth);
                put(Block.HEIGHT, tHeight);
                put(Block.COLOR, c);
            }
        };



        return new ArrayList<>(){
            {
                add(topRightBlock);
                add(topLeftBlock);
                add(bottomLeftBlock);
                add(bottomRightBlock);
            }
        };
    }

    public SquarePiece(int TILE_WIDTH, int TILE_HEIGHT, Pane canvas){
        this.canvas = canvas;
        this.TILE_HEIGHT = TILE_HEIGHT;
        this.TILE_WIDTH = TILE_WIDTH;
        this.color = Color.RED;
        ArrayList<HashMap<Block,Object>> pieces = this.initializePieces();

        this.pieceConstructed = new TetrisPiece(4, pieces, this.color);
    }

    @Override
    public void updateBoardPosition() {
        for (HashMap<Block, Object> block : this.pieceConstructed.blockContainer){
            block.put(Block.Y_POSITION, (int) block.getOrDefault(Block.Y_POSITION, 0) + this.TILE_HEIGHT);
        }


    }
}

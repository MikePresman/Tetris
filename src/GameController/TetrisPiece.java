package GameController;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.paint.Color;

public class TetrisPiece{
    public int amountOfBlocks;
    public ArrayList<HashMap<Block, Object>> blockContainer;
    public Color color;

    public TetrisPiece(int amountOfBlocks, ArrayList<HashMap<Block,Object>> blockContainer, Color color){
        this.amountOfBlocks = amountOfBlocks;
        this.blockContainer = blockContainer;
        this.color = color;
    }
}

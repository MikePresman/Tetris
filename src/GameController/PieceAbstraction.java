package GameController;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

class RotationProperties{
    boolean isRotatable;
    Block axisOfRotation;
}

public abstract class PieceAbstraction {


    public TetrisPiece pieceConstructed;
    public boolean pieceInitalized = false;
    public boolean hitBottom = false;
    public int leftEdge;
    public int rightEdge;
    public int bottomEdge;
    public Color color;
    public Pane canvas;
    public int TILE_WIDTH;
    public int TILE_HEIGHT;

    public RotationProperties pieceRotationProperties;

    public TetrisPiece getPiece(){return this.pieceConstructed;}

    public abstract void updateBoardPosition();
    public abstract ArrayList<HashMap<Block, Object>> initializePieces();




}

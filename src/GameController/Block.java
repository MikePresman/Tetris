package GameController;

import javafx.scene.paint.Color;

public class Block {
    public int Y_POSITION;
    public int X_POSITION;
    public int WIDTH;
    public int HEIGHT;
    public Color COLOR;

    public Block(int y, int x, int width, int height, Color c){
        this.Y_POSITION = y;
        this.X_POSITION = x;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.COLOR = c;
    }
}

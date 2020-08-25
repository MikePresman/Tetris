package GameController;

import javafx.scene.paint.Color;

public class Block {
    public double Y_POSITION;
    public double X_POSITION;
    public double WIDTH;
    public double HEIGHT;
    public Color COLOR;

    public Block(double y, double x, double width, double height, Color c){
        this.Y_POSITION = y;
        this.X_POSITION = x;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.COLOR = c;
    }
}

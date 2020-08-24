package sample;

import View.MainBoard;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {
    public int WIDTH = 450;
    public int HEIGHT = 500;
    Pane canvas = new Pane();
    MainBoard board;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Scene scene = new Scene(canvas, WIDTH, HEIGHT);
        primaryStage.setTitle("Tetris");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        this.board = new MainBoard(canvas);



        primaryStage.show();


        Timer timer = new Timer();
        timer.schedule(new GameLoop(), 0, 200);

    }

    private class GameLoop extends TimerTask {
        public synchronized void run() {
                board.update();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }

}

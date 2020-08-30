package sample;

import View.MainBoard;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {
    public int WIDTH = 400;
    public int HEIGHT = 400;
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

        //might have to put this in a new thread in order access it after the fact
        Timer timer = new Timer();
        timer.schedule(new GameLoop(), 0, 500);

        primaryStage.getIcons().add(new Image("file:icon.png"));



        scene.setOnKeyTyped(e->{
            this.board.handleKeyPress(e.getCharacter());
        });

        primaryStage.show();


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

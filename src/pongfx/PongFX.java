/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pongfx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author 1DAW01
 */
public class PongFX extends Application {
    
    final int WORLD_WIDTH = 400;
    final int WORLD_HEIGHT = 240;   
    
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, WORLD_WIDTH, WORLD_HEIGHT, Color.AQUAMARINE);
        primaryStage.setTitle("Pong FX");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Rectangle bar = new Rectangle(5, 90, 5, 40);
        bar.setFill(Color.BLACK);
        root.getChildren().add(bar);
        
        Circle ball = new Circle(200, 115, 5);
        ball.setFill(Color.BROWN);
        root.getChildren().add(ball);
        
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                /*double posY = bar.getTranslateY();
                posY--;
                bar.setTranslateY(posY);*/
                double posX = ball.getTranslateX();
                posX++;
                ball.setTranslateX(posX);
                /*double posY = ballon.getTranslateY();
                posY--;
                ballon.setTranslateY(posY);
                System.out.println(posX);
                //System.out.println(posY);*/
                if(posX == 195){
                    posX--;
                    ball.setTranslateX(posX--);
                }
         }
        }.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

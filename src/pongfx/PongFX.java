/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pongfx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
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
    //int posX
    int barPosY = 90;
    final int BAR_SPEED = 8;
    
    int dirXBall = 3; 
    int dirYBall = 2;
     

    
            
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, WORLD_WIDTH, WORLD_HEIGHT, Color.AQUAMARINE);
        primaryStage.setTitle("Pong FX");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Rectangle bar = new Rectangle(5, barPosY, 5, 40);
        bar.setFill(Color.BLACK);
        root.getChildren().add(bar);
        
        Circle ball = new Circle(200, 115, 5);
        ball.setFill(Color.BROWN);
        root.getChildren().add(ball);
        
       
        
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                
                double posX = ball.getTranslateX();
                ball.setTranslateX(posX + dirXBall);
                //System.out.println(posX);
                if(posX == 195){
                    dirXBall = -3;
                }
                if(posX == -195){
                    dirXBall = +3;
                }
                /*double posY = ball.getTranslateY();
                ball.setTranslateY (posY + dirYBall);
                System.out.println(posY);
                if(posX == 125){
                    dirYBall = -2;
                }
                if(posX == -125){
                    dirYBall = +2;
                }*/
            }
        }.start();
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        bar.resizeRelocate(5, barPosY, 5, 40);
                        barPosY -= BAR_SPEED;
                    break;
                    case DOWN:
                        bar.resizeRelocate(5, barPosY, 5, 40);
                        barPosY += BAR_SPEED;
                    
                }  
            }  
        });
        
    }

    
                   
    
                    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

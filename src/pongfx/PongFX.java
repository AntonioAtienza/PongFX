/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pongfx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
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
    
    //Datos sobre la barra
    int barPosXPlayer1 = 390;
    int barPosXPlayer2 = 5;
    int barPosY = 100;
    //final int BAR_SPEED = 8;
    int gravityPlayer1 = 2;
    int gravityPlayer2 = 2;
    
    //Direccion de la bola
    int dirXBall = 3; 
    //int dirYBall = 2;
            
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, WORLD_WIDTH, WORLD_HEIGHT, Color.AQUAMARINE);
        primaryStage.setTitle("Pong FX");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Rectangle barPlayer1 = new Rectangle(barPosXPlayer1, barPosY, 5, 40);
        barPlayer1.setFill(Color.BLACK);
        root.getChildren().add(barPlayer1);
        
        Rectangle barPlayer2 = new Rectangle(barPosXPlayer2, barPosY, 5, 40);
        barPlayer2.setFill(Color.BLACK);
        root.getChildren().add(barPlayer2);
        
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
                double posYPlayer1 = barPlayer1.getTranslateY();
                posYPlayer1 += gravityPlayer1;
                barPlayer1.setTranslateY(posYPlayer1);
                //System.out.println(posYPlayer1);
                
                if(posYPlayer1 == 100){
                    gravityPlayer1 = -2;
                }
                if(posYPlayer1 == -100){
                    gravityPlayer1 = 2;
                }
                double posYPlayer2 = barPlayer2.getTranslateY();
                posYPlayer2 += gravityPlayer2;
                barPlayer2.setTranslateY(posYPlayer2);
                
                if(posYPlayer2 == 100){
                    gravityPlayer2 = -2;
                }
                if(posYPlayer2 == -100){
                    gravityPlayer2 = 2;
                }
            }
        }.start();
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        gravityPlayer1 = -2;
                    break;
                    case DOWN:
                        gravityPlayer1 = 2;
                    break;
                    case W:
                        gravityPlayer2 = -2;
                    break;
                    case S:
                        gravityPlayer2 = 2;
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

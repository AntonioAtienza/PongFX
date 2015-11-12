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
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.BROWN;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author 1DAW01
 */
public class PongFX extends Application {
    
    final int WORLD_WIDTH = 400;
    final int WORLD_HEIGHT = 250;
    
    //Datos sobre la barra
    //final int BAR_WIDTH = ;
    //int barPosXPlayer2 = 0;
    //int barPosY = 0;
    //final int BAR_SPEED = 8;
    int gravityPlayer1 = 2;
    int gravityPlayer2 = 2;
    final int BAR_HEIGHT = 50;
    final int RADIO_BALL = 5;
    //Direccion de la bola
    int dirXBall = 3; 
    int dirYBall = 2;
    int posBall= 0;
    int posBar2 = 0;
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, WORLD_WIDTH, WORLD_HEIGHT, Color.AQUAMARINE);
        primaryStage.setTitle("Pong FX");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Rectangle barPlayer1 = new Rectangle(5, BAR_HEIGHT, BLACK);
        root.getChildren().add(barPlayer1);
        barPlayer1.setTranslateX(5);
        
        Rectangle barPlayer2 = new Rectangle(5, BAR_HEIGHT, BLACK);
        barPlayer2.setTranslateX(390);
        root.getChildren().add(barPlayer2);
        
        
        Circle ball = new Circle(RADIO_BALL,BROWN);
        ball.setTranslateX (WORLD_WIDTH/2);
        ball.setTranslateY(WORLD_HEIGHT/2);
        root.getChildren().add(ball);
        
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                
                double posX = ball.getTranslateX();
                ball.setTranslateX(posX + dirXBall);
                //System.out.println(posX);
                if(posX == WORLD_WIDTH-RADIO_BALL ){
                    dirXBall = -3;
                }
                if(posX == RADIO_BALL){
                    dirXBall = +3;
                }
                double posY = ball.getTranslateY();
                ball.setTranslateY (posY + dirYBall);
                //System.out.println(posY);
                if(posY == WORLD_HEIGHT-RADIO_BALL){
                    dirYBall = -2;
                }
                if(posY == RADIO_BALL){
                    dirYBall = +2;
                }
                
                double posYPlayer1 = barPlayer1.getTranslateY();
                posYPlayer1 += gravityPlayer1;
                barPlayer1.setTranslateY(posYPlayer1);
                //System.out.println(posYPlayer1);
                
                if(posYPlayer1 <= 0){
                    gravityPlayer1 = 0;
                }
                if(posYPlayer1 >= WORLD_HEIGHT-BAR_HEIGHT){
                    gravityPlayer1 = 0;
                }
                
                double posYPlayer2 = barPlayer2.getTranslateY();
                posYPlayer2 += gravityPlayer2;
                barPlayer2.setTranslateY(posYPlayer2);
                
                if(posYPlayer2 <= 0){
                    gravityPlayer2 = 0;
                }
                if(posYPlayer2 >= WORLD_HEIGHT-BAR_HEIGHT){
                    gravityPlayer2 = 0;
                }
                /*if(posX == 390 && posYPlayer2 == 390){
                    dirXBall = -3;
                }*/
            }
        }.start();
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:
                        gravityPlayer1 = -2;
                    break;
                    case S:
                        gravityPlayer1 = 2;
                    break;
                    case UP:
                        gravityPlayer2 = -2;
                    break;
                    case DOWN:
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

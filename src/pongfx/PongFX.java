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
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @Antonio Rojo Atienza
 */
public class PongFX extends Application {
    
    final int WORLD_WIDTH = 400;
    final int WORLD_HEIGHT = 250;
    
    int gravityPlayer1 = 2;
    int gravityPlayer2 = 2;
    final int BAR_HEIGHT = 50;
    final int BAR_WIDTH = 5;
    final int RADIO_BALL = 5;
    //Direccion de la bola
    int dirXBall = 3; 
    int dirYBall = 2;
    int posBall= 0;
    int posBar2 = 0;
    int posXPlayer2 = WORLD_WIDTH - 15;
    int posXPlayer1 = 15;
    int marcadorPlayer1 = 1;
    int marcadorPlayer2 = 1;
    
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, WORLD_WIDTH, WORLD_HEIGHT, Color.BLACK);
        primaryStage.setTitle("Pong FX");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Rectangle barPlayer1 = new Rectangle(BAR_WIDTH, BAR_HEIGHT, Color.AQUA);
        barPlayer1.setTranslateX(5);
        barPlayer1.setTranslateY(WORLD_HEIGHT*0.5 -BAR_HEIGHT*0.5);
        root.getChildren().add(barPlayer1);
        
        Rectangle barPlayer2 = new Rectangle(BAR_WIDTH, BAR_HEIGHT, Color.AQUA);
        barPlayer2.setTranslateX(WORLD_WIDTH - 10);
        barPlayer2.setTranslateY(WORLD_HEIGHT*0.5 -BAR_HEIGHT*0.5);
        root.getChildren().add(barPlayer2);
        
        Rectangle line = new Rectangle(0.5,WORLD_HEIGHT, Color.WHITE);
        line.setTranslateX(WORLD_WIDTH*0.5);
        root.getChildren().add(line);
        
        Label marcador1 = new Label(String.valueOf(0));
        marcador1.setTranslateX(WORLD_WIDTH*0.35);
        marcador1.setTextFill(Color.GOLD);
        marcador1.setFont(Font.font(20));
        root.getChildren().add(marcador1);
        
        Label marcador2 = new Label(String.valueOf(0));
        marcador2.setTranslateX(WORLD_WIDTH*0.65 - 10);
        marcador2.setTextFill(Color.GOLD);
        marcador2.setFont(Font.font(20));
        root.getChildren().add(marcador2);
        
        Circle ball = new Circle(RADIO_BALL,Color.ORANGE);
        ball.setTranslateX (WORLD_WIDTH*0.5);
        ball.setTranslateY(WORLD_HEIGHT*0.5);
        root.getChildren().add(ball);
        
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                
                //Movimiento de la bola en el eje X
                double posXBall = ball.getTranslateX();
                ball.setTranslateX(posXBall + dirXBall);
                
                //System.out.println(posXBall);
                /*if(posXBall == WORLD_WIDTH - RADIO_BALL ){
                    dirXBall = -3;}*/
                
                /*if(posXBall == RADIO_BALL){
                    dirXBall = +3;
                }*/
                
                //Movimiento de la bola en el eje de coordenadas Y
                double posYBall = ball.getTranslateY();
                //ball.setTranslateY (posYBall + dirYBall);
                if(posYBall == WORLD_HEIGHT - RADIO_BALL){
                    dirYBall = -2;
                }
                if(posYBall == RADIO_BALL){
                    dirYBall = +2;
                }
                
                //Movimiento de la pala del jugador 1
                double posYPlayer1 = barPlayer1.getTranslateY();
                posYPlayer1 += gravityPlayer1;
                barPlayer1.setTranslateY(posYPlayer1);
                //System.out.println(posYPlayer1);
                if(posYPlayer1 <= 0){
                    barPlayer1.setTranslateY(0);
                }
                if(posYPlayer1 >= WORLD_HEIGHT - BAR_HEIGHT){
                    barPlayer1.setTranslateY(WORLD_HEIGHT - BAR_HEIGHT);
                }
                
                 //Movimiento de la pala del jugador 2
                double posYPlayer2 = barPlayer2.getTranslateY();
                posYPlayer2 += gravityPlayer2;
                barPlayer2.setTranslateY(posYPlayer2);
                //System.out.println(posYPlayer2);
                if(posYPlayer2 <= 0){
                    barPlayer2.setTranslateY(0);
                }
                if(posYPlayer2 >= WORLD_HEIGHT - BAR_HEIGHT){
                    barPlayer2.setTranslateY(WORLD_HEIGHT - BAR_HEIGHT);
                }
                
                //Rebote en la pala
                if(posXBall >= posXPlayer2){
                    if (posYBall >= posYPlayer2 && posYBall <= (posYPlayer2 + BAR_HEIGHT)){
                            dirXBall = -3;
                    }   
                }
                if (posXBall <= posXPlayer1){
                    if (posYBall >= posYPlayer1 && posYBall <= (posYPlayer1 + BAR_HEIGHT)){
                            dirXBall = 3;
                    }
                }
                if (posXBall < -RADIO_BALL){
                    ball.setTranslateX(WORLD_WIDTH * 0.5);
                    marcador2.setText(String.valueOf(marcadorPlayer1++));
                }
                if (posXBall > WORLD_WIDTH + RADIO_BALL){
                    ball.setTranslateX(WORLD_WIDTH * 0.5);
                    
                    marcador1.setText(String.valueOf(marcadorPlayer2++));
                        
                }
                
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

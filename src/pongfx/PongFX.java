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
    //Velocidad de las palas de los jugadores y tamaño de dichas palas
    int gravityPlayer1 = 2;
    int gravityPlayer2 = 2;
    final int BAR_HEIGHT = 50;
    final int BAR_WIDTH = 5;
    final int RADIO_BALL = 5;
    //Direccion de la bola
    int dirXBall = 3; 
    int dirYBall = 0;
    int posXPlayer2 = WORLD_WIDTH - 15;
    int posXPlayer1 = 15;
    int scoreboardPlayer1 = 0;
    int scoreboardPlayer2 = 0;
    final int WIDTH = 10;
    final double LINE_WIDTH = 0.5;
    
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        //primaryStage.setResizable(false);
        Scene scene = new Scene(root, WORLD_WIDTH, WORLD_HEIGHT, Color.BLACK);
        primaryStage.setTitle("Ultimate Pong FX");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Rectangle barPlayer1 = new Rectangle(BAR_WIDTH, BAR_HEIGHT, Color.AQUA);
        barPlayer1.setTranslateX(5);
        barPlayer1.setTranslateY(WORLD_HEIGHT*0.5 - BAR_HEIGHT*0.5);
        root.getChildren().add(barPlayer1);
        
        Rectangle barPlayer2 = new Rectangle(BAR_WIDTH, BAR_HEIGHT, Color.AQUA);
        barPlayer2.setTranslateX(WORLD_WIDTH - WIDTH);
        barPlayer2.setTranslateY(WORLD_HEIGHT*0.5 - BAR_HEIGHT*0.5);
        root.getChildren().add(barPlayer2);
        
        Rectangle line = new Rectangle(LINE_WIDTH,WORLD_HEIGHT, Color.WHITE);
        line.setTranslateX(WORLD_WIDTH*0.5);
        root.getChildren().add(line);
        
        Label scoreboard1 = new Label(String.valueOf(0));
        scoreboard1.setTranslateX(WORLD_WIDTH*0.35);
        scoreboard1.setTextFill(Color.GOLD);
        scoreboard1.setFont(Font.font(20));
        root.getChildren().add(scoreboard1);
        
        Label scoreboard2 = new Label(String.valueOf(0));
        scoreboard2.setTranslateX(WORLD_WIDTH*0.65 - 10);
        scoreboard2.setTextFill(Color.GOLD);
        scoreboard2.setFont(Font.font(20));
        root.getChildren().add(scoreboard2);
        
        Circle ball = new Circle(RADIO_BALL,Color.ORANGE);
        ball.setTranslateX (WORLD_WIDTH*0.5);
        ball.setTranslateY(WORLD_HEIGHT*0.5);
        root.getChildren().add(ball);
        
        new AnimationTimer() {
            @Override
            public void handle(long now) { 
                //Movimiento de la bola en el eje de coordenadas X
                double posXBall = ball.getTranslateX();
                ball.setTranslateX(posXBall+ dirXBall);
                //Movimiento de la bola en el eje de coordenadas Y
                double posYBall = ball.getTranslateY();
                ball.setTranslateY (posYBall + dirYBall);
                if(posYBall >= WORLD_HEIGHT - RADIO_BALL){
                    dirYBall = -2;
                }
                if(posYBall <= RADIO_BALL){
                    dirYBall = +2;
                } 
                /*Movimiento de la pala del jugador 1 limitado entre dos valores
                que sería entre el ancho de la escena*/
                double posYPlayer1 = barPlayer1.getTranslateY();
                posYPlayer1 += gravityPlayer1;
                barPlayer1.setTranslateY(posYPlayer1);

                if(posYPlayer1 <= 0){
                    barPlayer1.setTranslateY(0);
                }
                if(posYPlayer1 >= WORLD_HEIGHT - BAR_HEIGHT){
                    barPlayer1.setTranslateY(WORLD_HEIGHT - BAR_HEIGHT);
                }
                /*Movimiento de la pala del jugador 2 limitado entre dos valores
                que sería entre el ancho de la escena*/
                double posYPlayer2 = barPlayer2.getTranslateY();
                posYPlayer2 += gravityPlayer2;
                barPlayer2.setTranslateY(posYPlayer2);
                if(posYPlayer2 <= 0){
                    barPlayer2.setTranslateY(0);
                }
                if(posYPlayer2 >= WORLD_HEIGHT - BAR_HEIGHT){
                    barPlayer2.setTranslateY(WORLD_HEIGHT - BAR_HEIGHT);
                }     
                /*Rebote en la pala del jugador 1, dependiendo de las distitntas zonas 
                donde la pelota rebote saldrá con una velocidad y dirección distinta*/
                int zona;
                if (posXBall <= posXPlayer1){
                    if (posYBall >= posYPlayer1 && posYBall <= (posYPlayer1 + BAR_HEIGHT)){
                            dirXBall = 3;
                        zona = (int)(posYBall-posYPlayer1)/10;
                        switch (zona){
                            case 0:
                                dirXBall =3;
                                dirYBall = -2;
                            break;
                            case 1:
                                dirXBall =3;
                                dirYBall = -1;
                            break;
                            case 2:
                                dirXBall =3;
                                dirYBall=0;
                            break;
                            case 3:
                                dirXBall =3;
                                dirYBall = 1;
                            break;
                            case 4:
                                dirXBall =3;
                                dirYBall = 2;
                            break;
                        }     
                    }
                }
                /*Rebote en la pala del jugador 2, dependiendo de las distitntas zonas 
                donde la pelota rebote saldrá con una velocidad y dirección distinta*/
                if(posXBall >= posXPlayer2){
                    if (posYBall >= posYPlayer2 && posYBall <= (posYPlayer2 + BAR_HEIGHT)){
                            dirXBall = -3;
                            zona = (int)(posYBall-posYPlayer2)/10;
                        switch (zona){
                            case 0:
                                dirXBall =-3;
                                dirYBall = -2;
                            break;
                            case 1:
                                dirXBall =-3;
                                dirYBall = -1;
                            break;
                            case 2:
                                dirXBall =-3;
                                dirYBall=0;
                            break;
                            case 3:
                                dirXBall =-3;
                                dirYBall = 1;
                            break;
                            case 4:
                                dirXBall =-3;
                                dirYBall = 2;
                            break;
                        }
                    }           
                }
                /*Realizamos un movimiento a la bola cuando se realiza un punto 
                para el jugador, aumentamos el marcador y realizamos un reinicio
                del marcador cuando este llega a un determinado número de puntos*/
                if (posXBall < -RADIO_BALL){
                    dirYBall=0;
                    dirXBall=-3;
                    ball.setTranslateY(WORLD_HEIGHT *0.5);
                    ball.setTranslateX(WORLD_WIDTH * 0.5);
                    scoreboard2.setText(String.valueOf(++scoreboardPlayer1));
                    if(Integer.valueOf(scoreboard2.getText())==11){
                        scoreboardPlayer1=0;
                        scoreboard2.setText(String.valueOf(scoreboardPlayer1));
                        scoreboardPlayer2=0;
                        scoreboard1.setText(String.valueOf(scoreboardPlayer2));
                    }  
                }
                if (posXBall > WORLD_WIDTH + RADIO_BALL){
                    dirYBall=0;
                    dirXBall=3;
                    ball.setTranslateY(WORLD_HEIGHT *0.5);
                    ball.setTranslateX(WORLD_WIDTH * 0.5);
                    scoreboard1.setText(String.valueOf(++scoreboardPlayer2));
                    if(Integer.valueOf(scoreboard1.getText())==11){
                        scoreboardPlayer1=0;
                        scoreboard2.setText(String.valueOf(scoreboardPlayer1));
                        scoreboardPlayer2=0;
                        scoreboard1.setText(String.valueOf(scoreboardPlayer2));  
                    }
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
                    break;
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

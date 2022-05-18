package main;

import java.awt.*;
//import java.security.Key;
import java.util.Currency;

//import java.awt.Graphics2D;
//import java.awt.Graphics;
//import java.awt.Color;
//import java.awt.Dimension;

import javax.swing.JPanel;
//import javax.swing.text.AttributeSet.ColorAttribute;


public class GamePanel extends JPanel implements Runnable{
//screen setting

    final int originalTileSize = 16;//16*16 tile pic??
    final int scale = 3;//3 times

    final int tileSize = originalTileSize * scale; //display (16*3)*(16*3)
    final int maxScreenCol =16;
    final int maxScreenRow =12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;//screen size

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;//start time image

    //set player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    //gameloop
    // public void  run(){

    //     double drawInterval = 1000000000/FPS;   //0.1666/sec
    //     double nextDrawTime = System.nanoTime() + drawInterval;

    //     while(gameThread != null){

    //         System.out.println("The game is running");

    //         update();

    //         repaint();
            
    //         try {
    //             long remainingTime = (long) (nextDrawTime - System.nanoTime());
    //             remainingTime = remainingTime/1000000;

    //             if (remainingTime < 0) {
    //                 remainingTime = 0;
    //             }

    //             Thread.sleep(remainingTime);

    //             nextDrawTime += drawInterval;

    //         } catch (InterruptedException e) {
    //             // TODO Auto-generated catch block
    //             e.printStackTrace();
    //         }

    //     }
    // }

    //Better game loop method (Delta/Accumulator method)
    public void run(){

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >=1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        if (keyH.upPressed == true) {
            playerY -= playerSpeed;
        }else if(keyH.downPressed == true){
            playerY += playerSpeed;
        }else if(keyH.leftPressed == true){
            playerX -= playerSpeed;
        }else if(keyH.rightPressed == true){
            playerX += playerSpeed;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);

        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose();
    }
    
}
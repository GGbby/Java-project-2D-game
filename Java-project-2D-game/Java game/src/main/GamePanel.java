package main;

import java.awt.*;
//import java.security.Key;
//import java.util.Currency;

//import java.awt.Graphics2D;
//import java.awt.Graphics;
//import java.awt.Color;
//import java.awt.Dimension;

import javax.swing.JPanel;
//import javax.swing.text.AttributeSet.ColorAttribute;

import entity.Player;
import tile.Tile;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable{
//screen setting

    final int originalTileSize = 16;//16*16 tile pic??
    final int scale = 3;//3 times

    public final int tileSize = originalTileSize * scale; //display (16*3)*(16*3)
    public final int maxScreenCol =16;
    public final int maxScreenRow =12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;//screen size
    
    
    
    //World setting
    public final int maxWorldCol = 50; //worldmap 50*50
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol; //worldmap in right size 50*3 * 50*3
    public final int worldHeigth = tileSize * maxWorldRow;

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;//start time image
    public Player player = new Player(this,keyH);


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
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        player.draw(g2);        

        g2.dispose();
    }
    
}
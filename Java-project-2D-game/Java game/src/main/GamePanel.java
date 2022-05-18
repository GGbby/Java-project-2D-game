package main;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
//screen setting

    final int originalTileSize = 16;//16*16 tile pic??
    final int scale = 3;//3 times

    final int tileSize = originalTileSize * scale; //display (16*3)*(16*3)
    final int maxScreenCol =16;
    final int maxScreenRow =12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;//screen size

    Thread gameThread;//start time image

    


    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    //gameloop
    public void  run(){

    }





}
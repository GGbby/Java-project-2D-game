package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {
    
    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B= new Font("Arial", Font.BOLD, 80); 
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {//放在draw in gameloop 會消耗運算資源

        if(gameFinished == true) {

            g2.setFont(arial_40);//Font
            g2.setColor(Color.white);

            String text;
            int textLenght;
            int x;
            int y;

            text = "You found the treasure!";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();//回報文字長度 為了顯示位置做調整

            x = gp.screenWidth/2 - textLenght/2;
            y = gp.screenHeight/2 - (gp.tileSize*3); //文字顯示位置
            g2.drawString(text, x, y);

            text = "Your time is "+ dFormat.format(playTime) + "s";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();//回報文字長度 為了顯示位置做調整
            x = gp.screenWidth/2 - textLenght/2;
            y = gp.screenHeight/2 + (gp.tileSize*4); //文字顯示位置
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);//結束Font
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();//回報文字長度

            x = gp.screenWidth/2 - textLenght/2;
            y = gp.screenHeight/2 + (gp.tileSize*2); //文字顯示位置
            g2.drawString(text, x, y);

            gp.gameThread = null;
        }else {
            
            g2.setFont(arial_40);//Font
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);//導入圖片位置
            g2.drawString("x " + gp.player.hasKey , 74, 65);//文字

            //Time
            playTime +=(double)1/60;
            g2.drawString("Time:" + dFormat.format(playTime), gp.tileSize *11, 65);

            //Message
            if(messageOn == true) {

                g2.setFont(g2.getFont().deriveFont(30F));//修改文字大小
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);//文字位置

                messageCounter++;
                
                if(messageCounter > 120) {//60fps = 2s
                    messageCounter = 0;
                    messageOn = false;
                }

            }
        }

    }
}

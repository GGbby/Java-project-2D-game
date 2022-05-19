package entity;

//import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import main.GamePanel;
import java.awt.Rectangle;

public class Player extends Entity{
    
    GamePanel gp;
    main.KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, main.KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(0, 0, 48, 48);//角色邊框觸碰
        solidArea.x = 10;
        solidArea.y = 20;
        solidArea.width = 28;
        solidArea.height = 28;

        setDefaultCloseOperation();
        getPlayerImage();
    }

    public void setDefaultCloseOperation(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResource("../res/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResource("../res/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResource("../res/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResource("../res/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResource("../res/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResource("../res/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResource("../res/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResource("../res/player/boy_right_2.png"));
        } catch (Exception e) {
        }
    }

    public void update(){
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction="up";         
            }else if(keyH.downPressed == true){
                direction="down";      
            }else if(keyH.leftPressed == true){
                direction="left";               
            }else if(keyH.rightPressed == true){
                direction="right";      
            }

            //check tile collison
            collisionOn = false;
            gp.cChecker.checkTile(this);
    
            // if cillision is false ,player can move
            if(collisionOn == false) {

                switch(direction) {
                case "up":worldY -= speed;break;
                case "down":worldY += speed;break;
                case "left":worldX -= speed;break;
                case "right":worldX += speed;break;

                    
                }
            }

            spriteCounter++;
            if (spriteCounter > 15) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        
    }
    public void draw(Graphics2D g2){
        //rectangle
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum ==2) {
                    image = up2;    
                }
                break;
            case "down":
            if (spriteNum == 1) {
                image = down1;
            }
            if (spriteNum ==2) {
                image = down2;    
            }
                break;
            case "left":
            if (spriteNum == 1) {
                image = left1;
            }
            if (spriteNum ==2) {
                image = left2;    
            }
                break;
            case "right":
            if (spriteNum == 1) {
                image = right1;
            }
            if (spriteNum ==2) {
                image = right2;    
            }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}

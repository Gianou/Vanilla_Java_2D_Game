package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public Player (GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 12;
        direction = "right";
    }
    public void getPlayerImage(){
        try {
            left2 = ImageIO.read(getClass().getResourceAsStream("src/main/resources/BatLeft2.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(keyH.rightPressed){
            x += speed;
        }
        else if(keyH.leftPressed){
            x -= speed;
        }
        else if(keyH.upPressed){
            y -= speed;
        }
        else if(keyH.downPressed){
            y += speed;
        }

    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        //switch case
        image = left2;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }
}

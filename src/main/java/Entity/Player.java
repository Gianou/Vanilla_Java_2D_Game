package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
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
        x = -100;
        y = 100;
        speed = 24;
        direction = "right";
    }
    public void getPlayerImage(){
        try {

            File file = new File("src/main/resources/BatStill1.png");
            FileInputStream fis = new FileInputStream(file);
            still1 = ImageIO.read(fis);

            file = new File("src/main/resources/BatStill2.png");
            fis = new FileInputStream(file);
            still2 = ImageIO.read(fis);

            file = new File("src/main/resources/BatLeft1.png");
            fis = new FileInputStream(file);
            left1 = ImageIO.read(fis);

            file = new File("src/main/resources/BatLeft2.png");
            fis = new FileInputStream(file);
            left2 = ImageIO.read(fis);

            file = new File("src/main/resources/BatRight1.png");
            fis = new FileInputStream(file);
            right1 = ImageIO.read(fis);

            file = new File("src/main/resources/BatRight2.png");
            fis = new FileInputStream(file);
            right2 = ImageIO.read(fis);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(keyH.rightPressed){
            direction = "right";
            x += speed;
        }
        else if(keyH.leftPressed){
            direction = "left";
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
        switch (direction){
            case "right":
                image = right1;
                break;
            case "left":
                image = left2;
                break;

        }
        g2.drawImage(image, x, y, gp.tileSize/2,gp.tileSize, null);

    }
}

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
        x = 100;
        y = 100;
        speed = 8;
        direction = "right";
    }
    public void getPlayerImage(){
        try {

            File file = new File("src/main/resources/player/BatStillR1.png");
            FileInputStream fis = new FileInputStream(file);
            stillR1 = ImageIO.read(fis);

            file = new File("src/main/resources/player/BatStillR2.png");
            fis = new FileInputStream(file);
            stillR2 = ImageIO.read(fis);

            file = new File("src/main/resources/player/BatStillL1.png");
            fis = new FileInputStream(file);
            stillL1 = ImageIO.read(fis);

            file = new File("src/main/resources/player/BatStillL2.png");
            fis = new FileInputStream(file);
            stillL2 = ImageIO.read(fis);

            file = new File("src/main/resources/player/BatLeft1.png");
            fis = new FileInputStream(file);
            left1 = ImageIO.read(fis);

            file = new File("src/main/resources/player/BatLeft2.png");
            fis = new FileInputStream(file);
            left2 = ImageIO.read(fis);

            file = new File("src/main/resources/player/BatRight1.png");
            fis = new FileInputStream(file);
            right1 = ImageIO.read(fis);

            file = new File("src/main/resources/player/BatRight2.png");
            fis = new FileInputStream(file);
            right2 = ImageIO.read(fis);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(keyH.rightPressed && keyH.upPressed){
            direction = "right";
            x += speed;
            y -= speed;

        }
        else if(keyH.leftPressed && keyH.upPressed){
            direction = "left";
            x -= speed;
            y -= speed;
        }
        else if(keyH.rightPressed && keyH.downPressed){
            direction = "right";
            x += speed;
            y += speed;
        }
        else if(keyH.leftPressed && keyH.downPressed){
            direction = "left";
            x -= speed;
            y += speed;
        }
        else if(keyH.rightPressed){
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
        //Add still animation when no keys pressed
        else{
            if(direction.equals("right") || direction.equals("stillR"))
                direction = "stillR";
            else
                direction = "stillL";
        }
        spriteCounter++;
        if(spriteCounter>10) {
            if (spriteNum == 1){
                spriteNum = 2;
            }

            else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        //switch case
        switch (direction){
            case "right":
                if(spriteNum == 1)
                    image = right1;
                if(spriteNum == 2)
                    image = right2;
                break;
            case "left":
                if(spriteNum == 1)
                    image = left1;
                if(spriteNum == 2)
                    image = left2;
                break;
            case "stillR":
                if(spriteNum == 1)
                    image = stillR1;
                if(spriteNum == 2)
                    image = stillR2;
                break;
            case "stillL":
                if(spriteNum == 1)
                    image = stillL1;
                if(spriteNum == 2)
                    image = stillL2;
                break;

        }
        g2.drawImage(image, x, y, gp.tileSize,gp.tileSize*2, null);

    }
}

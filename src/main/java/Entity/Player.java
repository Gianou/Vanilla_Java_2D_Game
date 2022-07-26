package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends Entity{

    KeyHandler keyH;
    char orientation = 'r';

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;


    public Player (GamePanel gp, KeyHandler keyH){
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2-(gp.tileSize/2);
        screenY = gp.screenHeight/2-(gp.tileSize);

        solidArea = new Rectangle(gp.tileSize/8, gp.tileSize + gp.tileSize/8, gp.tileSize/5*3, gp.tileSize/4*3);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize*12;
        worldY = gp.tileSize*12;
        speed = gp.tileSize/8;
        direction = "right";

        // PLAYER STATUS
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage(){
        try {
            UtilityTool uT = new UtilityTool();

            File file = new File("src/main/resources/player/BatStillR1.png");
            FileInputStream fis = new FileInputStream(file);
            stillR1 = ImageIO.read(fis);
            stillR1 = uT.scaleImage(stillR1, gp.tileSize, gp.tileSize*2);

            file = new File("src/main/resources/player/BatStillR2.png");
            fis = new FileInputStream(file);
            stillR2 = ImageIO.read(fis);
            stillR2 = uT.scaleImage(stillR2, gp.tileSize, gp.tileSize*2);

            file = new File("src/main/resources/player/BatStillL1.png");
            fis = new FileInputStream(file);
            stillL1 = ImageIO.read(fis);
            stillL1 = uT.scaleImage(stillL1, gp.tileSize, gp.tileSize*2);

            file = new File("src/main/resources/player/BatStillL2.png");
            fis = new FileInputStream(file);
            stillL2 = ImageIO.read(fis);
            stillL2 = uT.scaleImage(stillL2, gp.tileSize, gp.tileSize*2);

            file = new File("src/main/resources/player/BatLeft1.png");
            fis = new FileInputStream(file);
            left1 = ImageIO.read(fis);
            left1 = uT.scaleImage(left1, gp.tileSize, gp.tileSize*2);

            file = new File("src/main/resources/player/BatLeft2.png");
            fis = new FileInputStream(file);
            left2 = ImageIO.read(fis);
            left2 = uT.scaleImage(left2, gp.tileSize, gp.tileSize*2);

            file = new File("src/main/resources/player/BatRight1.png");
            fis = new FileInputStream(file);
            right1 = ImageIO.read(fis);
            right1 = uT.scaleImage(right1, gp.tileSize, gp.tileSize*2);

            file = new File("src/main/resources/player/BatRight2.png");
            fis = new FileInputStream(file);
            right2 = ImageIO.read(fis);
            right2 = uT.scaleImage(right2, gp.tileSize, gp.tileSize*2);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){

        if(gp.gameState == gp.dialogueState){
            if(orientation == 'r'){
                direction = "stillR";
            }
            else{
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

        else{
            if(keyH.rightPressed || keyH.leftPressed || keyH.upPressed || keyH.downPressed){
                if(keyH.rightPressed && keyH.upPressed){
                    direction = "rightUp";
                    orientation = 'r';
                }
                else if(keyH.leftPressed && keyH.upPressed){
                    direction = "leftUp";
                    orientation = 'l';
                }
                else if(keyH.rightPressed && keyH.downPressed){
                    direction = "rightDown";
                    orientation = 'r';
                }
                else if(keyH.leftPressed && keyH.downPressed){
                    direction = "leftDown";
                    orientation = 'l';
                }

                else if(keyH.rightPressed){
                    direction = "right";
                    orientation = 'r';
                }
                else if(keyH.leftPressed){
                    direction = "left";
                    orientation = 'l';

                }
                else if(keyH.upPressed){
                    direction = "up";

                }
                else if(keyH.downPressed){
                    direction = "down";

                }
                // Check Tile Collision
                collision = false;
                gp.cChecker.checkTile(this);

                // Check object collision
                int objIndex = gp.cChecker.checkObject(this, true);
                pickUpObject(objIndex);

                // Check NPC collision
                int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
                interactNPC(npcIndex);

                //Check Event
                gp.eventH.checkEvent();

                // If collision false, player can move
                if(!collision){
                    switch(direction){
                        case "up":
                            worldY -= speed;
                            break;
                        case "down":
                            worldY += speed;
                            break;
                        case "right":
                            worldX += speed;
                            break;
                        case "left":
                            worldX -= speed;
                            break;
                        case "rightUp":
                            if(rightOk)
                                worldX += speed;
                            if(upOk)
                                worldY -= speed;
                            break;
                        case "leftUp":
                            if(leftOk)
                                worldX -= speed;
                            if(upOk)
                                worldY -= speed;
                            break;
                        case "leftDown":
                            if(leftOk)
                                worldX -= speed;
                            if(downOk)
                                worldY += speed;
                            break;
                        case "rightDown":
                            if(rightOk)
                                worldX += speed;
                            if(downOk)
                                worldY += speed;
                            break;
                    }
                }

                resetDirectionsBoolean();

            }
            else if(orientation == 'r'){
                direction = "stillR";
            }
            else{
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


    }

    public void interactNPC(int i){
        if(i != 999){
            if(gp.keyH.spacePressed){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
                life--;
            }

        }
    }

    //OBJECT INTERACTION EVENT
    // Create an interaction function in SuperObject
    public void pickUpObject(int i){
        if(i != 999){
            String objectName = gp.obj[i].name;
            switch(objectName){
                case "Key":

                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a key");
                    gp.playSE(1);
                    break;
                case "Door":
                    if(hasKey > 0){
                        gp.playSE(2);
                        gp.obj[i] = null;
                        gp.ui.showMessage("Door Unlocked");
                        hasKey--;
                    }
                    break;
                case "Chest":
                    if(hasKey > 0){
                        gp.ui.gameFinished = true;
                        gp.stopMusic();
                        gp.playSE(1);
                        gp.playSE(2);
                        //gp.obj[i] = null;
                        //gp.ui.showMessage("You found the treasure, it was the friendship you made along the way");
                        hasKey--;
                    }
                    break;
            }
        }
    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        //switch case
        switch (direction){
            case "right", "rightDown", "rightUp":
                if(spriteNum == 1)
                    image = right1;
                if(spriteNum == 2)
                    image = right2;
                break;
            case "left", "leftDown", "leftUp":
                if(spriteNum == 1)
                    image = left1;
                if(spriteNum == 2)
                    image = left2;
                break;

            case "up", "down":
                if(orientation == 'l'){
                    if(spriteNum == 1)
                        image = left1;
                    if(spriteNum == 2)
                        image = left2;
                }
                if(orientation == 'r'){
                    if(spriteNum == 1)
                        image = right1;
                    if(spriteNum == 2)
                        image = right2;
                }
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
        g2.drawImage(image, screenX, screenY,null);

    }
    //public void resetDirectionsBoolean(){

    //}
}

package Entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Entity {
    GamePanel gp;
    UtilityTool uT = new UtilityTool();
    public int worldX, worldY;
    public int speed;
    public BufferedImage left1, left2, right1, right2;
    public BufferedImage upRight1, upRight2, downRight1, downRight2, up1, up2, down1, down2;
    public int width, height;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false, collisionDash = false;
    public boolean upOk = true, rightOk = true, downOk = true, leftOk = true;
    public char orientation = 'r';
    public int actionLockCounter = 0;
    String [] dialogues = new String[10];
    int dialogueIndex = 0;
    public int dashSpeed = 48*2;

    //ENTITY STATUS
    public int maxLife;
    public int life;



    public Entity(){}
    public Entity(GamePanel gp, int width, int height){
        this.gp = gp;
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        this.width = width;
        this.height = height;
    }

    public void resetDirectionsBoolean(){
        upOk = true;
        rightOk = true;
        downOk = true;
        leftOk = true;
    }

    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            BufferedImage image = null;
            //switch case
            switch (direction) {
                case "right", "rightDown", "rightUp":
                    if (spriteNum == 1)
                        image = right1;
                    if (spriteNum == 2)
                        image = right2;
                    break;
                case "left", "leftDown", "leftUp":
                    if (spriteNum == 1)
                        image = left1;
                    if (spriteNum == 2)
                        image = left2;
                    break;

                case "up", "down":
                    if (orientation == 'l') {
                        if (spriteNum == 1)
                            image = left1;
                        if (spriteNum == 2)
                            image = left2;
                    }
                    if (orientation == 'r') {
                        if (spriteNum == 1)
                            image = right1;
                        if (spriteNum == 2)
                            image = right2;
                    }
                    break;


                case "idle":
                    image = right1;
                    break;
            }
            g2.drawImage(image, screenX, screenY, null);
        }
    }

    public void setAction(){

    }

    public void update(){

        setAction();

        collision = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);

        if(!collision) {
            switch (direction) {
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
                case "idle":
                    break;

            }


            resetDirectionsBoolean();
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
    public void speak(){

        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        switch (gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "right":
                direction = "left";
                break;
            case "left":
                direction = "right";
                break;

        }
    }
    public BufferedImage getNPCImage(String name, UtilityTool uT) throws IOException {
        File file = new File("src/main/resources/npc/" + name + ".png");
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = ImageIO.read(fis);
        image = uT.scaleImage(image, gp.tileSize*width, gp.tileSize*height);

        return image;
    }

    public BufferedImage getPlayerImage(String name, UtilityTool uT, int width, int height) throws IOException {
        File file = new File("src/main/resources/player/" + name + ".png");
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = ImageIO.read(fis);
        image = uT.scaleImage(image, gp.tileSize*width, gp.tileSize*height);

        return image;
    }
}


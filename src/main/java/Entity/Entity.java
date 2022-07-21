package Entity;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;
    public BufferedImage stillR1, stillR2, stillL2, stillL1, left1,left2,right1,right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    public boolean upOk = true, rightOk = true, downOk = true, leftOk = true;
    public char orientation = 'r';
    public int actionLockCounter = 0;
    String [] dialogues = new String[10];
    int dialogueIndex = 0;

    GamePanel gp;


    public Entity(){}
    public Entity(GamePanel gp){
        this.gp = gp;
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
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

                case "stillR":
                    if (spriteNum == 1)
                        image = stillR1;
                    if (spriteNum == 2)
                    break;
                case "stillL":
                    if (spriteNum == 1)
                        image = stillL1;
                    if (spriteNum == 2)
                        image = stillL2;
                    break;
                case "idle":
                    image = right1;
                    break;
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
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
}


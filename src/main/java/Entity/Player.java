package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    KeyHandler keyH;
    char orientation = 'r';

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public boolean dash = false;
    public int dashCoolDownTime = 10, dashCoolDown = 0;


    public Player(GamePanel gp, KeyHandler keyH, int width, int height) {
        super(gp, width, height);
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize);

        //solidArea = new Rectangle(gp.tileSize/8, gp.tileSize + gp.tileSize/8, gp.tileSize/5*3, gp.tileSize/4*3);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 12;
        worldY = gp.tileSize * 12;
        speed = gp.tileSize / 8;
        dashSpeed = gp.tileSize * 3;
        //speed =25;
        direction = "right";

        // PLAYER STATUS
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {
        UtilityTool uT = new UtilityTool();

        try {
            up1 = getEntityImage("GhoulU1", uT);
            up2 = getEntityImage("GhoulU2", uT);

            down1 = getEntityImage("GhoulD1", uT);
            down2 = getEntityImage("GhoulD2", uT);

            left1 = getEntityImage("GhoulL1", uT);
            left2 = getEntityImage("GhoulL2", uT);

            right1 = getEntityImage("GhoulR1", uT);
            right2 = getEntityImage("GhoulR2", uT);

            upRight1 = getEntityImage("GhoulUR1", uT);
            upRight2 = getEntityImage("GhoulUR2", uT);

            upLeft1 = getEntityImage("GhoulUL1", uT);
            upLeft2 = getEntityImage("GhoulUL2", uT);

            downRight1 = getEntityImage("GhoulDR1", uT);
            downRight2 = getEntityImage("GhoulDR2", uT);

            downLeft1 = getEntityImage("GhoulDL1", uT);
            downLeft2 = getEntityImage("GhoulDL2", uT);

            still1 = getEntityImage("GhoulDR1", uT);
            still2 = getEntityImage("GhoulDR2", uT);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void update() {


        if (keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed || keyH.tPressed) {
            if (gp.gameState == gp.dialogueState) {
                if (orientation == 'r') {
                    direction = "stillR";
                } else {
                    direction = "stillL";
                }
                spriteCounter++;
                if (spriteCounter > 10) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }
            else {
                if(keyH.rightPressed || keyH.leftPressed || keyH.upPressed || keyH.downPressed) {

                    if (keyH.rightPressed && keyH.upPressed) {
                        direction = "rightUp";
                        orientation = 'r';
                    } else if (keyH.leftPressed && keyH.upPressed) {
                        direction = "leftUp";
                        orientation = 'l';
                    } else if (keyH.rightPressed && keyH.downPressed) {
                        direction = "rightDown";
                        orientation = 'r';
                    } else if (keyH.leftPressed && keyH.downPressed) {
                        direction = "leftDown";
                        orientation = 'l';
                    } else if (keyH.rightPressed) {
                        direction = "right";
                        orientation = 'r';
                    } else if (keyH.leftPressed) {
                        direction = "left";
                        orientation = 'l';

                    } else if (keyH.upPressed) {
                        direction = "up";

                    } else if (keyH.downPressed) {
                        direction = "down";

                    }
                }
            }


            //CHECK COLLISION

            collision = false;

            // Check Tile Collision
            gp.cChecker.checkTile(this);


            // Check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // Check NPC collision

            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // Check NPC collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //Check Event
            gp.eventH.checkEvent();



            // If collision false, player can move
            if (!collision && !keyH.tPressed) {
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
                        if (rightOk)
                            worldX += speed;
                        if (upOk)
                            worldY -= speed;
                        break;
                    case "leftUp":
                        if (leftOk)
                            worldX -= speed;
                        if (upOk)
                            worldY -= speed;
                        break;
                    case "leftDown":
                        if (leftOk)
                            worldX -= speed;
                        if (downOk)
                            worldY += speed;
                        break;
                    case "rightDown":
                        if (rightOk)
                            worldX += speed;
                        if (downOk)
                            worldY += speed;
                        break;
                }
            }


            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

            resetDirectionsBoolean();


        }//End of up down left right pressed loop

        //DASH

        if (keyH.spacePressed && dashCoolDown > dashCoolDownTime) {
            dash = true;
            dashCoolDown = 0;
            System.out.println(direction);
        }

        collisionDash = false;

        //CHECK FOR DASH
        if (dash) {
            gp.cChecker.checkTileDash(this);

            int objIndex = gp.cChecker.checkObjectDash(this, true); //If there is an object,
            // this fct will set collisionDash as true but the object willl still be picked
            pickUpObject(objIndex);
        }

        //DASH
        if (!collisionDash && dash) {
            switch (direction) {
                case "up":
                    worldY -= dashSpeed;
                    break;
                case "down":
                    worldY += dashSpeed;
                    break;
                case "right":
                    worldX += dashSpeed;
                    break;
                case "left":
                    worldX -= dashSpeed;
                    break;
                case "rightUp":
                    if (rightOk)
                        worldX += dashSpeed;
                    if (upOk)
                        worldY -= dashSpeed;
                    break;
                case "leftUp":
                    if (leftOk)
                        worldX -= dashSpeed;
                    if (upOk)
                        worldY -= dashSpeed;
                    break;
                case "leftDown":
                    if (leftOk)
                        worldX -= dashSpeed;
                    if (downOk)
                        worldY += dashSpeed;
                    break;
                case "rightDown":
                    if (rightOk)
                        worldX += dashSpeed;
                    if (downOk)
                        worldY += dashSpeed;
                    break;
            }
        }
        dash = false;


        dashCoolDown++;

        if(invincible){
            invincibleCounter++;
            if(invincibleCounter>60){
                invincibleCounter=0;
                invincible = false;
            }
        }
    }


    public void interactNPC(int i) {
        if (i != 999) {
            if (gp.keyH.tPressed) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }

        }
    }
    public void contactMonster(int i) {
        if (i != 999) {
            if(!invincible){
                life -= 1;
                invincible = true;
            }

        }
    }

    //OBJECT INTERACTION EVENT
    // Create an interaction function in SuperObject
    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.obj[i].name;
            switch (objectName) {
                case "Key":

                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a key");
                    gp.playSE(1);
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.playSE(2);
                        gp.obj[i] = null;
                        gp.ui.showMessage("Door Unlocked");
                        hasKey--;
                    }
                    break;
                case "Chest":
                    if (hasKey > 0) {
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

    @Override
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        //switch case
        switch (direction) {

            case "right":
                if (spriteNum == 1)
                    image = right1;
                if (spriteNum == 2)
                    image = right2;
                break;
            case "rightUp":
                if (spriteNum == 1)
                    image = upRight1;
                if (spriteNum == 2)
                    image = upRight2;
                break;
            case "rightDown":
                if (spriteNum == 1)
                    image = downRight1;
                if (spriteNum == 2)
                    image = downRight2;
                break;
            case "left":
                if (spriteNum == 1)
                    image = left1;
                if (spriteNum == 2)
                    image = left2;
                break;

            case "leftUp":
                if (spriteNum == 1)
                    image = upLeft1;
                if (spriteNum == 2)
                    image = upLeft2;
                break;

            case "leftDown":
                if (spriteNum == 1)
                    image = downLeft1;
                if (spriteNum == 2)
                    image = downLeft2;
                break;

            case "down":
                if (spriteNum == 1)
                    image = down1;
                if (spriteNum == 2)
                    image = down2;
                break;
            case "up":
                if (spriteNum == 1)
                    image = up1;
                if (spriteNum == 2)
                    image = up2;
                break;

        }
        //Transparent if damage taken
        if(invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, screenX, screenY, null);
        // Reset g2
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));



        // Invicibility debug
        g2.setColor(Color.blue);
        String invincibilityState = "Invincible : " + String.valueOf(invincible) + " " + invincibleCounter;
        g2.setFont(gp.ui.pixelFont);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        g2.setColor(Color.BLACK);
        int textLength;
        int x;
        int y;
        textLength = (int) g2.getFontMetrics().getStringBounds(invincibilityState, g2).getWidth();
        x = gp.screenWidth - textLength;
        y = gp.tileSize*2;

        if(keyH.debug){
            g2.drawRect(screenX + solidArea.x, screenY +solidArea.y, solidArea.width, solidArea.height);

            g2.drawString(invincibilityState, x, y);
        }

    }
}

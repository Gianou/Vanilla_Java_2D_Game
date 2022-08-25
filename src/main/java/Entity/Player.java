package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import Main.MouseHandler;
import Main.UtilityTool;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    KeyHandler keyH;
    MouseHandler mouseH;
    public int orientation = 1;

    public final int screenX;
    public final int screenY;
    public final int ScreenAttackRadiusX, ScreenAttackRadiusY;
    public final int solidAreaCenterX, solidAreaCenterY;
    public int hasKey = 0;

    public boolean dash = false;
    public int dashCoolDownTime = 10, dashCoolDown = 0;

    public BufferedImage attackD1, attackD2, attackU1, attackU2, attackR1, attackR2, attackL1, attackL2,
            attackUR1, attackUR2, attackUL1, attackUL2, attackDL1, attackDL2, attackDR1, attackDR2;
    boolean attacking = false;
    int attackSpriteCounter =0;
    public Shape attackAreas;
    public int worldAttackX, worldAttackY; //
    public int radius = 120;
    public int mouseX, mouseY;
    double angle;


    public Player(GamePanel gp, KeyHandler keyH, MouseHandler mouseH, int width, int height) {
        super(gp, width, height);
        this.keyH = keyH;
        this.mouseH = mouseH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize);

        //solidArea = new Rectangle(gp.tileSize/8, gp.tileSize + gp.tileSize/8, gp.tileSize/5*3, gp.tileSize/4*3);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);

        // 80 is the radius
        ScreenAttackRadiusX = screenX + gp.tileSize/2 -radius/2;
        ScreenAttackRadiusY = screenY + solidArea.y + solidArea.height/2 - radius / 2;

        solidAreaCenterX = screenX + solidArea.x + solidArea.width/2;
        solidAreaCenterY = screenY + solidArea.y + solidArea.height/2;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 12;
        worldY = gp.tileSize * 12;
        speed = gp.tileSize / 8;
        dashSpeed = gp.tileSize * 3;
        //speed =25;
        direction = 2;

        // PLAYER STATUS
        maxLife = 6;
        life = maxLife;

        //Attack areas
        attackAreas = new Arc2D.Float(worldAttackX, worldAttackY, 80, 80, 0, 90, Arc2D.PIE);

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

            attackD1 = getEntityAttackImage("AttackD1",1,2, uT);
            attackD2 = getEntityAttackImage("AttackD2",1,2, uT);

            attackU1 = getEntityAttackImage("AttackU1",1,2, uT);
            attackU2 = getEntityAttackImage("AttackU2",1,2, uT);

            attackR1 = getEntityAttackImage("AttackR1",2,1, uT);
            attackR2 = getEntityAttackImage("AttackR2",2,1, uT);

            attackL1 = getEntityAttackImage("AttackL1",2,1, uT);
            attackL2 = getEntityAttackImage("AttackL2",2,1, uT);

            attackUR1 = getEntityAttackImage("AttackUR1",2,2, uT);
            attackUR2 = getEntityAttackImage("AttackUR2",2,2, uT);

            attackUL1 = getEntityAttackImage("AttackUL1",2,2, uT);
            attackUL2 = getEntityAttackImage("AttackUL2",2,2, uT);

            attackDL1 = getEntityAttackImage("AttackDL1",2,2, uT);
            attackDL2 = getEntityAttackImage("AttackDL2",2,2, uT);

            attackDR1 = getEntityAttackImage("AttackDR1",2,2, uT);
            attackDR2 = getEntityAttackImage("AttackDR2",2,2, uT);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void attack(){
        attackSpriteCounter++;

        if(attackSpriteCounter <= 5){
            spriteNum = 1;
        }
        if(attackSpriteCounter>5 && attackSpriteCounter <=25){
            spriteNum = 2;
            int monsterIndex = gp.cChecker.checkAttack(this, gp.monster);
            damageMonster(monsterIndex);

            /*
            //During the attack frame you can check hit detection
            //Save current data
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction){
                case 0: worldY -= attackArea.height;
                    break;
                case 2: worldX += attackArea.width;
                    break;
                case 4: worldY += attackArea.height;
                    break;
                case 6: worldX -= attackArea.width;
                    break;
            }
            //Solid area becomes attack area to use checkEntity from cChecker
            solidArea.height = attackArea.height;
            solidArea.width = attackArea.width;
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            //Restore values
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

             */
        }
        if(attackSpriteCounter>25){
            spriteNum = 1;
            attackSpriteCounter = 0;
            attacking = false;
        }

    }

    public void update() {
        /*
        // Orientation WITH MOUSE
        mouseX =  MouseInfo.getPointerInfo().getLocation().x - 7;
        mouseY = MouseInfo.getPointerInfo().getLocation().y - 30;
        angle = getAngle(new Point(mouseX, mouseY));
        //System.out.println((int)angle);
        if(angle<=90){
            orientation = 1;
        }
        else if (angle<=180){
            orientation = 2;
        }
        else if(angle<=270){
            orientation = 3;
        }
        else if(angle<=360){
            orientation = 0;
        }

         */
        //With controller orientation is done in GamePadHanler
        //if no orientation is chosen with the left stick, we use direction
       if(gp.gPadH.right.x == 0 && gp.gPadH.right.y == 0){
           //orientation = 0;
       }

        if (keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed || keyH.tPressed) {
            if (gp.gameState == gp.dialogueState) {
                /*if (orientation == 'r') {
                    direction = "stillR";
                } else {
                    direction = "stillL";
                }

                 */
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
                        direction = 1;
                    }
                    else if (keyH.rightPressed && keyH.downPressed) {
                        direction = 3;
                    }
                    else if (keyH.leftPressed && keyH.downPressed) {
                        direction = 5;
                    }
                    else if (keyH.leftPressed && keyH.upPressed) {
                        direction = 7;
                    }
                    else if (keyH.upPressed) {
                        direction = 0;
                    }
                    else if (keyH.rightPressed) {
                        direction = 2;
                    }
                    else if (keyH.downPressed) {
                        direction = 4;
                    }
                    else if (keyH.leftPressed) {
                        direction = 6;
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
                    case 0:
                        worldY -= speed;
                        break;
                    case 1:
                        if (rightOk)
                            worldX += speed;
                        if (upOk)
                            worldY -= speed;
                        break;
                    case 2:
                        worldX += speed;
                        break;
                    case 3:
                        if (rightOk)
                            worldX += speed;
                        if (downOk)
                            worldY += speed;
                        break;
                    case 4:
                        worldY += speed;
                        break;
                    case 5:
                        if (leftOk)
                            worldX -= speed;
                        if (downOk)
                            worldY += speed;
                        break;
                    case 6:
                        worldX -= speed;
                        break;
                    case 7:
                        if (leftOk)
                            worldX -= speed;
                        if (upOk)
                            worldY -= speed;
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


        //Attack

        if(gp.mouseH.leftClick){
            attacking = true;

        }

        if(attacking == true){
            attack();
            System.out.println(attackSpriteCounter);
        }
        //DASH

        if (keyH.spacePressed && dashCoolDown > dashCoolDownTime) {
            dash = true;
            dashCoolDown = 0;
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
                case 0:
                    worldY -= dashSpeed;
                    break;
                case 1:
                    if (rightOk)
                        worldX += dashSpeed;
                    if (upOk)
                        worldY -= dashSpeed;
                    break;
                case 2:
                    worldX += dashSpeed;
                    break;
                case 5:
                    if (leftOk && downOk)
                        worldX -= dashSpeed;
                    if (downOk)
                        worldY += dashSpeed;
                    break;
                case 4:
                    worldY += dashSpeed;
                    break;
                case 3:
                    if (rightOk)
                        worldX += dashSpeed;
                    if (downOk)
                        worldY += dashSpeed;
                    break;
                case 6:
                    worldX -= dashSpeed;
                    break;
                case 7:
                    if (leftOk)
                        worldX -= dashSpeed;
                    if (upOk)
                        worldY -= dashSpeed;
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
// gp.keyH can be replaced by keyH
            if (gp.keyH.tPressed) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }

        }
/*
        if(gp.mouseH.leftClick){
            attacking = true;
            System.out.println("attack");
        }


 */

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

        int tempScreenX = screenX;
        int tempScreenY = screenY;
        BufferedImage image = null;

        switch (orientation){
            case 0:
                if(!attacking){
                    if (spriteNum == 1)
                        image = upRight1;
                    if (spriteNum == 2)
                        image = upRight2;
                }
                else {
                    tempScreenY -= gp.tileSize;
                    if (spriteNum == 1)
                        image = attackUR1;
                    if (spriteNum == 2)
                        image = attackUR2;
                }
                break;
            case 1:
                if(!attacking){
                    if (spriteNum == 1)
                        image = downRight1;
                    if (spriteNum == 2)
                        image = downRight2;
                }
                else {

                    if (spriteNum == 1)
                        image = attackDR1;
                    if (spriteNum == 2)
                        image = attackDR2;
                }
                break;
            case 2:
                if(!attacking){
                    if (spriteNum == 1)
                        image = downLeft1;
                    if (spriteNum == 2)
                        image = downLeft2;
                }
                else {

                    tempScreenX -= gp.tileSize;
                    if (spriteNum == 1)
                        image = attackDL1;
                    if (spriteNum == 2)
                        image = attackDL2;
                }
                break;
            case 3:
                if(!attacking){
                    if (spriteNum == 1)
                        image = upLeft1;
                    if (spriteNum == 2)
                        image = upLeft2;
                }
                else {
                    tempScreenY -= gp.tileSize;
                    tempScreenX -= gp.tileSize;
                    if (spriteNum == 1)
                        image = attackUL1;
                    if (spriteNum == 2)
                        image = attackUL2;
                }
                break;

        }
        /*
        //switch case
        switch (direction) {
            case 0:
                if(!attacking){
                    if (spriteNum == 1)
                        image = up1;
                    if (spriteNum == 2)
                        image = up2;
                }
                else {
                    tempScreenY -= gp.tileSize;
                    if (spriteNum == 1)
                        image = attackU1;
                    if (spriteNum == 2)
                        image = attackU2;
                }
                break;
            case 1:
                if(!attacking){
                    if (spriteNum == 1)
                        image = upRight1;
                    if (spriteNum == 2)
                        image = upRight2;
                }
                else {
                    tempScreenY -= gp.tileSize;
                    if (spriteNum == 1)
                        image = attackUR1;
                    if (spriteNum == 2)
                        image = attackUR2;
                }
                break;
            case 2:
                if(!attacking){
                    if (spriteNum == 1)
                        image = right1;
                    if (spriteNum == 2)
                        image = right2;
                }
                else {
                    if (spriteNum == 1)
                        image = attackR1;
                    if (spriteNum == 2)
                        image = attackR2;
                }
                break;
            case 3:
                if(!attacking){
                    if (spriteNum == 1)
                        image = downRight1;
                    if (spriteNum == 2)
                        image = downRight2;
                }
                else {
                    if (spriteNum == 1)
                        image = downRight1;
                    if (spriteNum == 2)
                        image = downRight2;
                }

                break;
            case 4:
                if(!attacking){
                    if (spriteNum == 1)
                        image = down1;
                    if (spriteNum == 2)
                        image = down2;
                }
                else {
                    if (spriteNum == 1)
                        image = attackD1;
                    if (spriteNum == 2)
                        image = attackD2;
                }

                break;
            case 5:
                if(!attacking){
                    if (spriteNum == 1)
                        image = downLeft1;
                    if (spriteNum == 2)
                        image = downLeft2;
                }
                else {
                    if (spriteNum == 1)
                        image = downLeft1;
                    if (spriteNum == 2)
                        image = downLeft2;
                }

                break;
            case 6:
                if(!attacking){
                    if (spriteNum == 1)
                        image = left1;
                    if (spriteNum == 2)
                        image = left2;
                }
                else {
                    tempScreenX -= gp.tileSize;
                    if (spriteNum == 1)
                        image = attackL1;
                    if (spriteNum == 2)
                        image = attackL2;
                }

                break;

            case 7:
                if(!attacking){
                    if (spriteNum == 1)
                        image = upLeft1;
                    if (spriteNum == 2)
                        image = upLeft2;
                }
                else {
                    if (spriteNum == 1)
                        image = upLeft1;
                    if (spriteNum == 2)
                        image = upLeft2;
                }

                break;
        }

         */
        //Transparent if damage taken
        if(invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);
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

            g2.setColor(Color.RED);
            //During the attack frame you can check hit detection
            //Save current data
            int currentScreenX = screenX;
            int currentScreenY = screenY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction){
                case 0:
                    currentScreenY -= attackArea.height;
                    break;
                case 2:
                    currentScreenX += attackArea.width;
                    break;
                case 4:
                    currentScreenY += attackArea.height;
                    break;
                case 6:
                    currentScreenX -= attackArea.width;
                    break;
            }
            //g2.drawRect(worldX, worldY, attackArea.width, attackArea.height);
            g2.drawRect(currentScreenX + solidArea.x, currentScreenY +solidArea.y, attackArea.width, attackArea.height);

            //Restore values

            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }

    }

    public void damageMonster(int monsterIndex){
        if(monsterIndex != 999){
            if(gp.monster[monsterIndex].invincible == false){
                gp.monster[monsterIndex].life -= 1;
                gp.monster[monsterIndex].invincible = true;
                if(gp.monster[monsterIndex].life <= 0){
                    gp.monster[monsterIndex] = null;
                }
            }

            //System.out.println("monster damaged");
        }
        else{
            //System.out.println("miss");
        }
    }
    public float getAngle(Point target) {
        float angle = (float) Math.toDegrees(Math.atan2(target.y - solidAreaCenterY, target.x - solidAreaCenterX));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }

}

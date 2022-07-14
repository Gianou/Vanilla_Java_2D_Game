package Entity;

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

    public void resetDirectionsBoolean(){
        upOk = true;
        rightOk = true;
        downOk = true;
        leftOk = true;
    }
}


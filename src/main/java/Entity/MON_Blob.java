package Entity;

import Entity.*;
import Main.GamePanel;

import java.io.IOException;

public class MON_Blob extends SuperNPC {

    public MON_Blob (GamePanel gp, int width, int height){

        super(gp, width, height);
        //name = "Blob"; no name in entity yet
        getBlobImage();
        direction = "right";
        speed = 0;
        maxLife = 4;
        life = maxLife;
        type = 2;

        //Solid Area is defined in super constructor but solidArea can be changed here
    }

    public void getBlobImage(){
        try {
            up1 = getEntityImage("monster/Blob1", uT);
            up2 = getEntityImage("monster/Blob2", uT);

            down1 = getEntityImage("monster/Blob1", uT);
            down2 = getEntityImage("monster/Blob2", uT);

            left1 = getEntityImage("monster/Blob1", uT);
            left2 = getEntityImage("monster/Blob2", uT);

            right1 = getEntityImage("monster/Blob1", uT);
            right2 = getEntityImage("monster/Blob2", uT);

            upRight1 = getEntityImage("monster/Blob1", uT);
            upRight2 = getEntityImage("monster/Blob2", uT);

            upLeft1 = getEntityImage("monster/Blob1", uT);
            upLeft2 = getEntityImage("monster/Blob2", uT);

            downRight1 = getEntityImage("monster/Blob1", uT);
            downRight2 = getEntityImage("monster/Blob2", uT);

            downLeft1 = getEntityImage("monster/Blob1", uT);
            downLeft2 = getEntityImage("monster/Blob2", uT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 60) {
            int i = (int) (Math.random() * 150 + 1);
            if (i <= 25) {
                direction = "up";
            } else if (i <= 50) {
                direction = "down";
            } else if (i <= 75) {
                direction = "right";
            } else if (i <= 100) {
                direction = "left";
            } else if (i <= 150) {
                direction = "idle";
            }
            actionLockCounter = 0;
        }
    }
}

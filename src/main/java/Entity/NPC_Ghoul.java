package Entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NPC_Ghoul extends SuperNPC {
    public NPC_Ghoul(GamePanel gp, int width, int height) {
        super(gp, width, height);

        direction = "right";
        speed = gp.tileSize / 32;
        getGhoulImage();
        setDialogues("eurg", "Aaaaaargh", "bruuuuh");
    }

    public void getGhoulImage() {
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

    @Override
    public void setAction() {
        actionLockCounter++;


        if (actionLockCounter == 60) {
            int i = (int) (Math.random() * 120 + 1);
            if (i <= 10) {
                direction = "up";
            } else if (i <= 20) {
                direction = "down";
            } else if (i <= 30) {
                direction = "right";
            } else if (i <= 40) {
                direction = "left";
            } else if (i <= 50) {
                direction = "leftUp";
            } else if (i <= 60) {
                direction = "leftDown";
            } else if (i <= 70) {
                direction = "rightDown";
            } else if (i <= 80) {
                direction = "rightUp";
            } else if (i <= 120) {
                direction = "idle";
            }
            actionLockCounter = 0;
        }
    }
}




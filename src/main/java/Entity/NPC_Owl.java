package Entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class NPC_Owl extends SuperNPC {
    public NPC_Owl(GamePanel gp, int width, int height) {
        super(gp, width, height);


        direction = 2;
        speed = gp.tileSize / 32;
        getOwlImage();
        setDialogues("hoot", "hooty hoot", "Hoooty partner?");
    }

    public void getOwlImage() {
        try {

            up1 = getEntityImage("OwlL1", uT);
            up2 = getEntityImage("OwlL2", uT);

            down1 = getEntityImage("OwlL1", uT);
            down2 = getEntityImage("OwlL2", uT);

            left1 = getEntityImage("OwlL1", uT);
            left2 = getEntityImage("OwlL2", uT);

            right1 = getEntityImage("OwlR1", uT);
            right2 = getEntityImage("OwlR2", uT);

            upRight1 = getEntityImage("OwlR1", uT);
            upRight2 = getEntityImage("OwlR2", uT);

            upLeft1 = getEntityImage("OwlL1", uT);
            upLeft2 = getEntityImage("OwlL2", uT);

            downRight1 = getEntityImage("OwlR1", uT);
            downRight2 = getEntityImage("OwlR2", uT);

            downLeft1 = getEntityImage("OwlL1", uT);
            downLeft2 = getEntityImage("OwlL2", uT);

            still1 = getEntityImage("OwlR1", uT);
            still2 = getEntityImage("OwlR2", uT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setAction() {
        actionLockCounter++;


        if (actionLockCounter == 60) {
            int i = (int) (Math.random() * 150 + 1);
            if (i <= 25) {
                direction = 0;
            } else if (i <= 50) {
                direction = 4;
            } else if (i <= 75) {
                direction = 2;
            } else if (i <= 100) {
                direction = 6;
            } else if (i <= 150) {
                direction = -999;
            }
            actionLockCounter = 0;
        }
    }

}
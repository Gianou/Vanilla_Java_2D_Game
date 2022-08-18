package Entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class NPC_Owl extends SuperNPC{
    public NPC_Owl(GamePanel gp, int width, int height){
        super(gp, width, height);


        direction = "right";
        speed = gp.tileSize/32;
        getOwlImage();
        setDialogues("hoot", "hooty hoot", "Hoooty partner?");
    }
    public void getOwlImage(){
        try {

            up1 = getNPCImage("OwlL1", uT);
            up2 = getNPCImage("OwlL2", uT);

            down1 = getNPCImage("OwlL1", uT);
            down2 = getNPCImage("OwlL2", uT);

            left1 = getNPCImage("OwlL1", uT);
            left2 = getNPCImage("OwlL2", uT);

            right1 = getNPCImage("OwlR1", uT);
            right2 = getNPCImage("OwlR2", uT);

            upRight1 = getNPCImage("OwlR1", uT);
            upRight2 = getNPCImage("OwlR2", uT);

            upLeft1 = getNPCImage("OwlL1", uT);
            upLeft2 = getNPCImage("OwlL2", uT);

            downRight1 = getNPCImage("OwlR1", uT);
            downRight2 = getNPCImage("OwlR2", uT);

            downLeft1 = getNPCImage("OwlL1", uT);
            downLeft2 = getNPCImage("OwlL2", uT);

            still1 = getNPCImage("OwlR1", uT);
            still2 = getNPCImage("OwlR2", uT);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void setAction(){
        actionLockCounter++;


        if(actionLockCounter == 60){
            int i = (int) (Math.random()*150+1);
            if(i<=25){
                direction = "up";
            }
            else if(i<=50){
                direction = "down";
            }
            else if(i<=75){
                direction = "right";
            }
            else if(i<=100){
                direction = "left";
            }
            else if(i<=150){
                direction = "idle";
            }
            actionLockCounter = 0;
        }
    }

    @Override
    public void speak(){
        super.speak();
    }
}

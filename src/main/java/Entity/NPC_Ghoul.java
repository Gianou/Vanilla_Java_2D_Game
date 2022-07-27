package Entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NPC_Ghoul extends Entity{
    public NPC_Ghoul(GamePanel gp){
        super(gp);

        direction = "right";
        speed = gp.tileSize/32;
        getOwlImage();
        setDialogues();
    }
    public void getOwlImage(){
        try {
            UtilityTool uT = new UtilityTool();

            left1 = getNPCImage("GhoulD1",uT,2,  1);
            left2 = getNPCImage("GhoulD2",uT,2,  1);
            right1 = getNPCImage("GhoulR1",uT,2,  1);
            right2 = getNPCImage("GhoulR2",uT,2,  1);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void setAction(){
        actionLockCounter++;


        if(actionLockCounter == 60){System.out.println(direction);
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
    public void setDialogues(){
        dialogues[0] = "eurhagh?";
        dialogues[1] = "mmmmmmh";
        dialogues[2] = "argh argh";
    }


    @Override
    public void speak(){
        super.speak();
    }
}

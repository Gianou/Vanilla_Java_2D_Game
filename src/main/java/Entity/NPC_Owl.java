package Entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class NPC_Owl extends Entity{
    public NPC_Owl(GamePanel gp, int width, int height){
        super(gp, width, height);


        direction = "right";
        speed = gp.tileSize/32;
        getOwlImage();
        setDialogues();
    }
    public void getOwlImage(){
        try {

            left1 = getNPCImage("OwlL1",uT);
            left2 = getNPCImage("OwlL2",uT);

            right1 = getNPCImage("OwlR1",uT);
            right2 = getNPCImage("OwlR2",uT);

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
    public void setDialogues(){
        dialogues[0] = "Who?";
        dialogues[1] = "Hoot";
        dialogues[2] = "Hoooooooooooooooooooooooot";
    }

    @Override
    public void speak(){
        super.speak();
    }
}

package Entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class NPC_Owl extends Entity{
    public NPC_Owl(GamePanel gp){
        super(gp);

        direction = "right";
        speed = gp.tileSize/32;
        getOwlImage();
        setDialogues();
    }
    public void getOwlImage(){
        try {
            UtilityTool uT = new UtilityTool();

            /*
            File file = new File("src/main/resources/npc/OwlL1.gif");
            FileInputStream fis = new FileInputStream(file);
            stillR1 = ImageIO.read(fis);
            stillR1 = uT.scaleImage(stillR1, gp.tileSize, gp.tileSize*2);

            file = new File("src/main/resources/npc/OwlL1.gif");
            fis = new FileInputStream(file);
            stillR2 = ImageIO.read(fis);
            stillR2 = uT.scaleImage(stillR2, gp.tileSize, gp.tileSize*2);

            file = new File("src/main/resources/npc/OwlL1.gif");
            fis = new FileInputStream(file);
            stillL1 = ImageIO.read(fis);
            stillL1 = uT.scaleImage(stillL1, gp.tileSize, gp.tileSize*2);

            file = new File("src/main/resources/npc/OwlL1.gif");
            fis = new FileInputStream(file);
            stillL2 = ImageIO.read(fis);
            stillL2 = uT.scaleImage(stillL2, gp.tileSize, gp.tileSize*2);

             */

            File file = new File("src/main/resources/npc/OwlL1.png");
            FileInputStream fis = new FileInputStream(file);
            left1 = ImageIO.read(fis);
            left1 = uT.scaleImage(left1, gp.tileSize, gp.tileSize*2);

            file = new File("src/main/resources/npc/OwlL2.gif");
            fis = new FileInputStream(file);
            left2 = ImageIO.read(fis);
            left2 = uT.scaleImage(left2, gp.tileSize, gp.tileSize*2);

            file = new File("src/main/resources/npc/OwlR1.gif");
            fis = new FileInputStream(file);
            right1 = ImageIO.read(fis);
            right1 = uT.scaleImage(right1, gp.tileSize, gp.tileSize*2);

            file = new File("src/main/resources/npc/OwlR2.gif");
            fis = new FileInputStream(file);
            right2 = ImageIO.read(fis);
            right2 = uT.scaleImage(right2, gp.tileSize, gp.tileSize*2);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void setAction(){
        actionLockCounter++;


        if(actionLockCounter == 60){System.out.println(direction);
            int i = (int) (Math.random()*100+1);
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

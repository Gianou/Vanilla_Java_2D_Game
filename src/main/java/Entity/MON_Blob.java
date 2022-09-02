package Entity;

import Entity.*;
import Main.GamePanel;

import java.awt.*;
import java.io.IOException;

public class MON_Blob extends SuperNPC {

    public MON_Blob (GamePanel gp, int width, int height){

        super(gp, width, height);
        //name = "Blob"; no name in entity yet
        getBlobImage();
        direction = 2;
        speed = 3;
        maxLife = 3;
        life = maxLife;
        type = 2;
        //solidArea = new Rectangle(0,0, gp.tileSize, gp.tileSize);
        //solidAreaDefaultX = solidArea.x;
        //solidAreaDefaultY = solidArea.y;

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
        Point player = new Point(gp.player.worldX, gp.player.worldY);
        float angle =  getAngle(player);
        float fortyFiveByTwo = 45 / 2;


        //System.out.println(angle);
        if(angle >= 360 - fortyFiveByTwo || angle < fortyFiveByTwo){direction = 2;}
        else if (angle >= 45 - fortyFiveByTwo && angle < 45 + fortyFiveByTwo){direction = 3;}
        else if (angle >= 90 - fortyFiveByTwo && angle < 90 + fortyFiveByTwo){direction = 4;}
        else if (angle >= 135 - fortyFiveByTwo && angle < 135 + fortyFiveByTwo){direction = 5;}
        else if (angle >= 180 - fortyFiveByTwo && angle < 180 + fortyFiveByTwo){direction = 6;}
        else if (angle >= 225 - fortyFiveByTwo && angle < 225 + fortyFiveByTwo){direction = 7;}
        else if (angle >= 270 - fortyFiveByTwo && angle < 270 + fortyFiveByTwo){direction = 0;}
        else if (angle >= 315 - fortyFiveByTwo && angle < 315 + fortyFiveByTwo){direction = 1;}
        //System.out.println(direction);

    }

    public float getAngle(Point target) {
        float angle = (float) Math.toDegrees(Math.atan2(target.y - worldY, target.x - worldX));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }
}


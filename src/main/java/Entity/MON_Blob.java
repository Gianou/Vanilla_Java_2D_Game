package Entity;

import Entity.*;
import Main.GamePanel;
import PathFinder.PathFinder;
import PathFinder.SubMatrix;
import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.io.IOException;

public class MON_Blob extends SuperNPC {
    SubMatrix subM;
    int cpt = 0;
    Point spawn;

    public MON_Blob (GamePanel gp, int width, int height, int worldX, int worldY){

        super(gp, width, height, worldX, worldY);
        //name = "Blob"; no name in entity yet
        getBlobImage();
        direction = 2;
        speed = 2;
        maxLife = 12;
        life = maxLife;
        type = 2;
        spawn = new Point(spawnX, spawnY);


        subM = new SubMatrix(gp);
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
        Point monster = new Point(worldX, worldY);
        Point player = new Point(gp.player.worldX, gp.player.worldY);
        if(offensif){

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

            //Check from player

            if(monster.distance(player) > gp.tileSize*10){
                offensif = false;
            }
        }

        else {
            if(monster.x == spawn.x && monster.y == spawn.y){
                //Do nothing
                direction = -999;
            }
            else {
                // Go back to its spawn
                float angle =  getAngle(spawn);
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
            }

        }

    }

    public float getAngle(Point target) {
        float angle = (float) Math.toDegrees(Math.atan2(target.y - worldY, target.x - worldX));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }
    @Override
    public void update(){
        super.update();
        cpt++;
        subM.monCol = (worldX+(width*gp.tileSize/2))/gp.tileSize;
        subM.monLine = (worldY+(height*gp.tileSize/2))/gp.tileSize;
        if(gp.keyH.enterPressed && cpt >=60){
            subM.getSubMat();
            subM.display();
            if(subM.playerInSub != null){
                PathFinder pathF = new PathFinder(subM.subMat, new Point(subM.col/2,subM.col/2), subM.playerInSub);
                pathF.recursive();
                if(pathF.shortestPath != null){
                    System.out.println(pathF.shortestPath);
                }
                else{
                    System.out.println("no path found");
                }
                cpt = 0;
            }
            else{
                System.out.println("Player not found");
            }
        }
    }
}


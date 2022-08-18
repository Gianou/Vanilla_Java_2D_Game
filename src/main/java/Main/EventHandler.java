package Main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;

    EventRect eventRect[][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow){

            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = gp.tileSize/2;
            eventRect[col][row].y = gp.tileSize/2;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }


    }
    public void checkEvent(){

        //Check if player is more than one tile away from last event
//To change tile's attribute, do it here
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize){
            canTouchEvent = true;
        }
//EVENT EXAMPLE
        if(canTouchEvent){
            if(hit( 12, 19, "any")){
                damagePit(12, 19, gp.dialogueState);
            }
            if (hit(17, 19, "up")) {
                healingPool(gp.dialogueState);
            }
        }

    }
    public boolean hit(int col, int row, String reqDirection){
        //Knowing the direction is important, because if any direction works,
        // n you cannot move away from the eventRect
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false){
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }
        // reset values
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }
    public void damagePit(int col, int row, int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fell in a hole";
        gp.player.life -=1;
        //eventRect[col][row].eventDone = true;
        canTouchEvent = false;

    }
    public void healingPool(int gameState){
        if(gp.keyH.spacePressed){
            gp.gameState = gameState;
            gp.ui.currentDialogue = "Your life was restored";
            gp.player.life = gp.player.maxLife;
        }

        gp.keyH.spacePressed = false;
    }
}

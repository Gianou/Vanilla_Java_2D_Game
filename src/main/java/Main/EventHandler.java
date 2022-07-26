package Main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;
    public EventHandler(GamePanel gp){
        this.gp = gp;
        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }
    public void checkEvent(){
//EVENT EXAMPLE
        if(hit( 41, 8, "right")){
            damagePit(gp.dialogueState);
        }
        if (hit(17, 19, "up")) {
            healingPool(gp.dialogueState);

        }
    }
    public boolean hit(int eventCol, int eventRow, String reqDirection){
        //Knowing the direction is important, because if any direction works,
        // n you cannot move away from the eventRect
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol*gp.tileSize + eventRect.x;
        eventRect.y = eventRow*gp.tileSize + eventRect.y;

        if(gp.player.solidArea.intersects(eventRect)){
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;
            }
        }
        // reset values
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }
    public void damagePit(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fell in a hole";
        gp.player.life -=1;
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

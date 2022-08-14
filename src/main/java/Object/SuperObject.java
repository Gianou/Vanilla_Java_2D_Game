package Object;

import Entity.Entity;
import Main.GamePanel;
import Main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject extends Entity {

    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;

    public Rectangle solidArea = new Rectangle(0,0,48,48);


    UtilityTool uT = new UtilityTool();


    public SuperObject(GamePanel gp){
        this.gp = gp;
    }


    public void draw(Graphics2D g2) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(image, screenX, screenY, gp.tileSize * width, gp.tileSize* height, null);
        }
    }

}

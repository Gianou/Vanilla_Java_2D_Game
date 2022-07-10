package Main;

import Object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Font myFont;
    OBJ_Key key;
    BufferedImage keyImg;
    boolean messageOn;
    String message = "";

    public UI(GamePanel gp) {
        this.gp = gp;
        myFont = new Font("Arial", Font.PLAIN, 40);
        key = new OBJ_Key();
        keyImg = key.image;
    }
    public void showMessage(String str){
        message = str;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        g2.setFont(myFont);
        g2.setColor(Color.BLACK);
        g2.drawImage(keyImg, gp.tileSize/4, gp.tileSize/4, gp.tileSize, gp.tileSize, null);
        g2.drawString(" x " + gp.player.hasKey,gp.tileSize, gp.tileSize + gp.tileSize/4);
        if(messageOn){
            g2.drawString(message, gp.player.screenX, gp.player.screenY);
        }
    }
}

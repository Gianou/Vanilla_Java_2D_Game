package Main;

import Object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Font arial_40, arial_55;
    OBJ_Key key;
    BufferedImage keyImg;
    boolean messageOn;
    String message = "";
    int messageLength;
    int msgCpt = 0;
    public boolean gameFinished = false;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Courier New", Font.BOLD, 40);
        arial_55 = new Font("Courier New", Font.BOLD, 55);
        key = new OBJ_Key(gp);
        keyImg = key.image;
    }
    public void showMessage(String str){
        message = str;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        if(gameFinished){

            drawFinished(g2);

        }

        else if (gp.gameState == gp.playState){
            g2.setFont(arial_40);
            g2.setColor(Color.BLACK);
            g2.drawImage(keyImg, gp.tileSize/4, gp.tileSize/4, gp.tileSize, gp.tileSize, null);
            g2.drawString(" x " + gp.player.hasKey,gp.tileSize, gp.tileSize + gp.tileSize/4);
            // Coordinate
            g2.drawString("X : " + String.valueOf(gp.player.worldX/gp.tileSize) +"  Y : " +
                    String.valueOf(gp.player.worldY/gp.tileSize + 2),gp.screenWidth - gp.tileSize*8, gp.tileSize + gp.tileSize/4);

            if(messageOn){
                messageLength = (int)g2.getFontMetrics().getStringBounds(message, g2).getWidth();
                g2.drawString(message, (gp.player.screenX + gp.tileSize/2) - messageLength/2, gp.player.screenY);
                msgCpt ++;
                if(msgCpt > 120){
                    messageOn = false;
                    msgCpt = 0;
                }
            }
        }
        else if (gp.gameState == gp.pauseState){
            drawPaused(g2);
        }

    }
    public void drawFinished(Graphics2D g2){
        g2.setFont(arial_55);
        g2.setColor(Color.BLACK);

        String text;
        int textLength;
        int x;
        int y;
        text = "You found the treasure!";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x = gp.screenWidth/2 - textLength/2;
        y = gp.screenHeight/2 - gp.tileSize*2;
        g2.drawString(text, x, y);

        gp.stopMusic();
        gp.gameThread = null;

    }
    public void drawPaused(Graphics2D g2){
        g2.setFont(arial_55);
        g2.setColor(Color.BLACK);

        String text;
        int textLength;
        int x;
        int y;
        text = "Paused";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x = gp.screenWidth/2 - textLength/2;
        y = gp.screenHeight/2 - gp.tileSize*2;
        g2.drawString(text, x, y);
    }
}

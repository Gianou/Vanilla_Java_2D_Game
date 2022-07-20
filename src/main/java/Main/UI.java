package Main;

import Object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    Font arial_40, arial_55, pixelFont;
    OBJ_Key key;
    BufferedImage keyImg;
    boolean messageOn;
    String message = "";
    int messageLength;
    int msgCpt = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";

    public UI(GamePanel gp){
        this.gp = gp;

        try{
            InputStream is = getClass().getResourceAsStream("/font/MP16REG.ttf");
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, is);
        }
        catch (FontFormatException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }


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

        //PLAYSTATE
        else if (gp.gameState == gp.playState){
            drawPlay(g2);
        }

        //PAUSESTATE
        else if (gp.gameState == gp.pauseState){
            System.out.println("ici dans l'ui");
            drawPaused(g2);
        }

        //DIALOGUESTATE
        else if (gp.gameState == gp.dialogueState){
            drawDialogue(g2);
        }

    }
    public void drawFinished(Graphics2D g2){
        g2.setFont(pixelFont);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
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
    public void drawPlay(Graphics2D g2) {
        g2.setFont(pixelFont);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        g2.setColor(Color.BLACK);

        g2.drawImage(keyImg, gp.tileSize / 4, gp.tileSize / 4, gp.tileSize, gp.tileSize, null);
        g2.drawString(" x " + gp.player.hasKey, gp.tileSize, gp.tileSize + gp.tileSize / 4);
        // Coordinate
        g2.drawString("X : " + String.valueOf(gp.player.worldX / gp.tileSize) + "  Y : " +
                String.valueOf(gp.player.worldY / gp.tileSize + 2), gp.screenWidth - gp.tileSize * 8, gp.tileSize + gp.tileSize / 4);

        if (messageOn) {
            messageLength = (int) g2.getFontMetrics().getStringBounds(message, g2).getWidth();
            g2.drawString(message, (gp.player.screenX + gp.tileSize / 2) - messageLength / 2, gp.player.screenY);
            msgCpt++;
            if (msgCpt > 120) {
                messageOn = false;
                msgCpt = 0;
            }
        }
    }
    public void drawPaused(Graphics2D g2){
        g2.setFont(pixelFont);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
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
    public void drawDialogue(Graphics2D g2){

        //WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize*6;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*2;

        drawSubWindow(x, y, width, height, g2);

        g2.setFont(pixelFont);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));

        g2.drawString(currentDialogue, x+gp.tileSize, y+gp.tileSize);
    }

    public void drawSubWindow(int x, int y, int width, int height, Graphics2D g2){
        Color c = new Color(0, 0, 0, 128);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width, height, 35, 35);

        c = new Color(255, 255, 255, 128);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);


    }
}

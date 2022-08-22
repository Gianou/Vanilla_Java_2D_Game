package Main;

import Object.OBJ_Key;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import Object.SuperObject;
import Object.OBJ_Heart;

public class UI {
    GamePanel gp;
    public Font arial_40, arial_55, pixelFont;
    OBJ_Key key;
    BufferedImage keyImg;
    boolean messageOn;
    String message = "";
    int messageLength;
    int msgCpt = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int menuNum = 0;
    BufferedImage fullHeart, halfHeart, emptyHeart;

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


        // CREATE HUD OBJECT
        SuperObject heart = new OBJ_Heart(gp);
        fullHeart = heart.image;
        halfHeart = heart.image2;
        emptyHeart = heart.image3;
    }
    public void showMessage(String str){
        message = str;
        messageOn = true;
    }

    public void drawTest(Graphics2D g2){
        Shape s = new Arc2D.Float(210, 210, 80, 80, 45, 90, Arc2D.PIE);
        g2.draw(s);

    }
    public void draw(Graphics2D g2){

        //TESTS
        drawTest(g2);

        if(gameFinished){
            drawFinished(g2);
        }
        else if(gp.player.life <=0){
            drawGameOver(g2);
        }

        else if(gp.gameState == gp.titleState){
            drawTitle(g2);
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

        //DRAW KEYS
        int x = gp.tileSize/4;
        int y = gp.tileSize;
        g2.drawImage(keyImg, x, y, gp.tileSize, gp.tileSize, null);
        g2.drawString(" x " + gp.player.hasKey, x + gp.tileSize, y*2);


        // Coordinate
        if(gp.keyH.debug){
            String coordinates = "X : " + String.valueOf(gp.player.worldX / gp.tileSize) + "  Y : " +
                    String.valueOf(gp.player.worldY / gp.tileSize + 2);
            int textLength = (int) g2.getFontMetrics().getStringBounds(coordinates, g2).getWidth();
            x = gp.screenWidth - ( textLength + gp.tileSize/4);
            y = gp.tileSize;
            g2.drawString(coordinates, x, y);
        }




        if (messageOn) {
            messageLength = (int) g2.getFontMetrics().getStringBounds(message, g2).getWidth();
            g2.drawString(message, (gp.player.screenX + gp.tileSize / 2) - messageLength / 2, gp.player.screenY);
            msgCpt++;
            if (msgCpt > 120) {
                messageOn = false;
                msgCpt = 0;
            }
        }
        drawPlayerLife(g2);
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
        drawPlayerLife(g2);
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
        drawPlayerLife(g2);
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
    public void drawTitle(Graphics2D g2){


        //TITLE NAME
        g2.setFont(pixelFont);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96f));
        String text = "The Little Bat 2D";
        int x = getXforCenteredText(text, g2);
        int y = gp.screenHeight/5;

        g2.setColor(Color.BLACK);
        g2.drawString(text, x+5, y+5);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);


        //NEW, CONTINUE, QUIT
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36f));

        text = "";
        if(menuNum == 0){
            text = "> ";
        }
        text += "NEW GAME";
        x = getXforCenteredText(text, g2);
        y = gp.screenHeight/4*3;
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+5, y+5);
        g2.setColor(Color.WHITE);
        g2.drawString(text,x, y);

        text = "";
        if(menuNum == 1){
            text = "> ";
        }
        text += "LOAD GAME";
        x = getXforCenteredText(text, g2);

        y += gp.tileSize;
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+5, y+5);
        g2.setColor(Color.WHITE);
        g2.drawString(text,x, y);

        text = "";
        if(menuNum == 2){
            text = "> ";
        }
        text += "QUIT";
        x = getXforCenteredText(text, g2);
        y += gp.tileSize;
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+5, y+5);
        g2.setColor(Color.WHITE);
        g2.drawString(text,x, y);

        // IMAGE

        gp.player.spriteCounter++;
        if(gp.player.spriteCounter>10) {
            if (gp.player.spriteNum == 1) {
                gp.player.spriteNum = 2;
            } else if (gp.player.spriteNum == 2) {
                gp.player.spriteNum = 1;
            }
            gp.player.spriteCounter = 0;
        }
        x = gp.screenWidth / 2 - gp.tileSize;
        y = gp.tileSize * 2;
        Image img = gp.player.right1;
        if (gp.player.spriteNum == 2) {
            img = gp.player.right2;
        }
        g2.drawImage(img, x, y, gp.tileSize*2, gp.tileSize*4, null);

    }
    public int getXforCenteredText(String text, Graphics2D g2){
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - textLength/2;
        return x;
    }
    public void drawPlayerLife(Graphics2D g2){
        int x = gp.tileSize/4;
        int y = gp.tileSize/4;
        int i = 0;

        //Draw max life
        while (i < gp.player.maxLife/2){
            g2.drawImage(emptyHeart, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // Reset
        x = gp.tileSize/4;
        y = gp.tileSize/4;
        i = 0;

        //Draw max life
        while (i < gp.player.life){
            g2.drawImage(halfHeart, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(fullHeart, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }
    public void drawGameOver(Graphics2D g2){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 64F));
        String text = "GAME OVER";
        int x = getXforCenteredText(text, g2);
        int y = gp.screenHeight/2 - gp.tileSize*2;
        g2.drawString(text, x, y);

        gp.stopMusic();
        gp.gameThread = null;
    }
}

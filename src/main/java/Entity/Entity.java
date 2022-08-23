package Entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Entity {
    public GamePanel gp;
    public UtilityTool uT = new UtilityTool();
    public int worldX, worldY;
    public int speed;
    public BufferedImage left1, left2, right1, right2, still1, still2;
    public BufferedImage upRight1, upRight2, upLeft1, upLeft2, downLeft1, downLeft2, downRight1, downRight2, up1, up2, down1, down2;
    public int width = 1, height = 1;

    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public Rectangle attackArea;
    public int solidAreaDefaultX = 0, solidAreaDefaultY = 0;
    public boolean collision = false, collisionDash = false;
    public boolean upOk = true, rightOk = true, downOk = true, leftOk = true;
    public char orientation = 'r';
    public int actionLockCounter = 0;
    String [] dialogues = new String[10];
    int dialogueIndex = 0;
    public int dashSpeed = 48*2;

    public int type; // 0 = player, 1 = npc, 2 = monster;

    //ENTITY STATUS
    public int maxLife;
    public int life;
    public boolean invincible = false;
    public int invincibleCounter = 0;



    public Entity(){}
    public Entity(GamePanel gp, int width, int height){
        this.gp = gp;
        this.width = width;
        this.height = height;
        solidArea = new Rectangle(width*gp.tileSize *3/16, height*gp.tileSize*6/16, width*gp.tileSize *10/16, height*gp.tileSize*10/16);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void resetDirectionsBoolean(){
        upOk = true;
        rightOk = true;
        downOk = true;
        leftOk = true;
    }

    public void draw(Graphics2D g2){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            BufferedImage image = null;
            //switch case
            switch (direction) {
                case "right":
                    if (spriteNum == 1)
                        image = right1;
                    if (spriteNum == 2)
                        image = right2;
                    break;
                case "rightUp":
                    if (spriteNum == 1)
                        image = upRight1;
                    if (spriteNum == 2)
                        image = upRight2;
                    break;
                case "rightDown":
                    if (spriteNum == 1)
                        image = downRight1;
                    if (spriteNum == 2)
                        image = downRight2;
                    break;
                case "left":
                    if (spriteNum == 1)
                        image = left1;
                    if (spriteNum == 2)
                        image = left2;
                    break;

                case "leftUp":
                    if (spriteNum == 1)
                        image = upLeft1;
                    if (spriteNum == 2)
                        image = upLeft2;
                    break;

                case "leftDown":
                    if (spriteNum == 1)
                        image = downLeft1;
                    if (spriteNum == 2)
                        image = downLeft2;
                    break;

                case "down":
                    if (spriteNum == 1)
                        image = down1;
                    if (spriteNum == 2)
                        image = down2;
                    break;
                case "up":
                    if (spriteNum == 1)
                        image = up1;
                    if (spriteNum == 2)
                        image = up2;
                    break;


                case "idle":
                    image = right1;
                    break;
            }

            //Make transparent
            if(invincible){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }
            g2.drawImage(image, screenX, screenY, null);

            //Reset Alpha
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

            g2.setColor(Color.blue);
            if(gp.keyH.debug){
                g2.drawRect(screenX + solidArea.x, screenY +solidArea.y, solidArea.width, solidArea.height);
            }


        }
    }

    public BufferedImage getEntityImage(String name, UtilityTool uT) throws IOException {
        File file = new File("src/main/resources/npc/" + name + ".png");
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = ImageIO.read(fis);
        image = uT.scaleImage(image, gp.tileSize*width, gp.tileSize*height);

        return image;
    }

    public BufferedImage getEntityAttackImage(String name, int width, int height, UtilityTool uT) throws IOException {
        File file = new File("src/main/resources/npc/" + name + ".png");
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = ImageIO.read(fis);
        image = uT.scaleImage(image, gp.tileSize*width*this.width, gp.tileSize*height*this.height);

        return image;
    }

}


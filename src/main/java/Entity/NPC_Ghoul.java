package Entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NPC_Ghoul extends Entity {
    public NPC_Ghoul(GamePanel gp) {
        super(gp);

        solidArea = new Rectangle(0, 0, gp.tileSize * 2, gp.tileSize * 2);
        direction = "right";
        speed = gp.tileSize / 32;
        getOwlImage();
        setDialogues();
    }

    public void getOwlImage() {
        try {
            up1 = getNPCImage("GhoulU1", uT, 2, 2);
            up2 = getNPCImage("GhoulU2", uT, 2, 2);

            down1 = getNPCImage("GhoulD1", uT, 2, 2);
            down2 = getNPCImage("GhoulD2", uT, 2, 2);

            left1 = getNPCImage("GhoulD1", uT, 2, 2);
            left2 = getNPCImage("GhoulD2", uT, 2, 2);

            right1 = getNPCImage("GhoulR1", uT, 2, 2);
            right2 = getNPCImage("GhoulR2", uT, 2, 2);

            upRight1 = getNPCImage("GhoulUR1", uT, 2, 2);
            upRight2 = getNPCImage("GhoulUR2", uT, 2, 2);

            downRight1 = getNPCImage("GhoulDR1", uT, 2, 2);
            downRight2 = getNPCImage("GhoulDR2", uT, 2, 2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setAction() {
        actionLockCounter++;


        if (actionLockCounter == 60) {
            System.out.println(direction);
            int i = (int) (Math.random() * 120 + 1);
            if (i <= 10) {
                direction = "up";
            } else if (i <= 20) {
                direction = "down";
            } else if (i <= 30) {
                direction = "right";
            } else if (i <= 40) {
                direction = "left";
            } else if (i <= 50) {
                direction = "leftUp";
            } else if (i <= 60) {
                direction = "leftDown";
            } else if (i <= 70) {
                direction = "rightDown";
            } else if (i <= 80) {
                direction = "rightUp";
            } else if (i <= 120) {
                direction = "idle";
            }
            actionLockCounter = 0;
        }
    }

    public void setDialogues() {
        dialogues[0] = "eurhagh?";
        dialogues[1] = "mmmmmmh";
        dialogues[2] = "argh argh";
    }


    @Override
    public void speak() {
        super.speak();
    }

    public void draw(Graphics2D g2, GamePanel gp) {
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
                case "left", "leftDown", "leftUp":
                    if (spriteNum == 1)
                        image = left1;
                    if (spriteNum == 2)
                        image = left2;
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

                case "stillR":
                    if (spriteNum == 1)
                        image = stillR1;
                    if (spriteNum == 2)
                        break;
                case "stillL":
                    if (spriteNum == 1)
                        image = stillL1;
                    if (spriteNum == 2)
                        image = stillL2;
                    break;
                case "idle":
                    image = right1;
                    break;
            }
            g2.drawImage(image, screenX, screenY, null);
        }
    }
}

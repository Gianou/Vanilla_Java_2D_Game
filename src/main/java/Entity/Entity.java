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
    public int spawnX, spawnY;
    public int speed;
    public BufferedImage left1, left2, right1, right2, still1, still2;
    public BufferedImage upRight1, upRight2, upLeft1, upLeft2, downLeft1, downLeft2, downRight1, downRight2, up1, up2, down1, down2;

    public int width = 1, height = 1;

    public int direction;
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
    int tmp = 0;
    public int dashSpeed = 48*2;

    public int type; // 0 = player, 1 = npc, 2 = monster;

    //ENTITY STATUS
    public int maxLife;
    public int life;
    public boolean invincible = false;
    public boolean recoil;
    public int invincibleCounter = 0;

    boolean dying = false;
    public boolean alive = true;
    int dyingCounter;
    public int recoilCounter = 0;
    public boolean offensif;



    public Entity(){}
    public Entity(GamePanel gp, int width, int height){
        this.gp = gp;
        this.width = width;
        this.height = height;
        solidArea = new Rectangle(width*gp.tileSize *3/16, height*gp.tileSize*6/16, width*gp.tileSize *10/16, height*gp.tileSize*10/16);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
    public Entity(GamePanel gp, int width, int height, int worldX, int worldY){
        this.gp = gp;
        this.width = width;
        this.height = height;
        this.worldX = worldX * gp.tileSize;
        this.worldY = worldY * gp.tileSize;
        solidArea = new Rectangle(width*gp.tileSize *3/16, height*gp.tileSize*6/16, width*gp.tileSize *10/16, height*gp.tileSize*10/16);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        spawnX = this.worldX;
        spawnY = this.worldY;

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
                case 0:
                    if (spriteNum == 1) image = upRight1;
                    if (spriteNum == 2) image = upRight2;
                    break;
                case 1:
                    if (spriteNum == 1) image = upRight1;
                    if (spriteNum == 2) image = upRight2;
                    break;
                case 2:
                    if (spriteNum == 1) image = downRight1;
                    if (spriteNum == 2) image = downRight2;
                    break;
                case 3:
                    if (spriteNum == 1) image = downRight1;
                    if (spriteNum == 2) image = downRight2;
                    break;
                case 4:
                    if (spriteNum == 1) image = downRight1;
                    if (spriteNum == 2) image = downRight2;
                    break;
                case 5:
                    if (spriteNum == 1) image = downLeft1;
                    if (spriteNum == 2) image = downLeft2;
                    break;
                case 6:
                    if (spriteNum == 1) image = downLeft1;
                    if (spriteNum == 2) image = downLeft2;
                    break;
                case 7:
                    if (spriteNum == 1) image = upLeft1;
                    if (spriteNum == 2) image = upLeft2;
                    break;
                case -999:
                    if (spriteNum == 1) image = downLeft1;
                    if (spriteNum == 2) image = downLeft1;
                    break;
            }

            //Make transparent
            if(invincible){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }
            if(dying){
                dyingAnimation(g2);
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

    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;
        if(dyingCounter <= 5){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        else if (dyingCounter <= 10){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        else if (dyingCounter <= 15){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        else if (dyingCounter <= 20){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        else if (dyingCounter <= 25){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        else if (dyingCounter <= 30){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        else if (dyingCounter <= 35){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        else if (dyingCounter <= 40){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        else if (dyingCounter > 40){
            dying = false;
            alive = false;
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
    public void hitRecoil(Entity entity){
        //int recoilSpeed = gp.tileSize;
        recoilCounter++;

        if(recoilCounter <= 1){
            tmp = speed;
            //inverse direction
            if(direction == -999){
                direction = gp.player.direction;
            }
            else{
                direction += 4;
                direction = direction%8;
            }

            speed = speed * 3;
        }
        if(recoilCounter>5 && recoilCounter <=25){
            gp.cChecker.checkTile(this);
            gp.cChecker.checkObject(this, true);
            if(!collision){
                switch (direction){
                    case 0:
                        worldY-=speed;
                        break;
                    case 1:
                        if(rightOk)
                            worldX += speed;
                        if(upOk)
                            worldY -= speed;
                        break;
                    case 2:
                        worldX += speed;
                        break;
                    case 3:
                        if(rightOk)
                            worldX += speed;
                        if(downOk)
                            worldY += speed;

                        break;
                    case 4:
                        worldY += speed;
                        break;
                    case 5:
                        if(leftOk)
                            worldX -= speed;
                        if(downOk)
                            worldY += speed;

                        break;
                    case 6:
                        worldX -= speed;
                        break;
                    case 7:
                        if(leftOk)
                            worldX -= speed;
                        if(upOk)
                            worldY -= speed;
                        break;
                }
            }
            /*
            if(!collision){
                switch (direction){
                    case 4:
                        worldY-=speed;
                        break;
                    case 5:
                        if(rightOk)
                            worldX += speed;
                        if(upOk)
                            worldY -= speed;
                        break;
                    case 6:
                        worldX += speed;
                        break;
                    case 7:
                        if(rightOk)
                            worldX += speed;
                        if(downOk)
                            worldY += speed;

                        break;
                    case 0:
                        worldY += speed;
                        break;
                    case 1:
                        if(leftOk)
                            worldX -= speed;
                        if(downOk)
                            worldY += speed;

                        break;
                    case 2:
                        worldX -= speed;
                        break;
                    case 3:
                        if(leftOk)
                            worldX -= speed;
                        if(upOk)
                            worldY -= speed;
                        break;
                }
            }

             */

            spriteNum = 2;
        }
        if(recoilCounter>25){
            spriteNum = 1;
            recoilCounter = 0;
            recoil = false;
            speed = tmp;
        }
        /*
        switch (entity.direction){
            case 0:
                entity.worldY += recoilSpeed;
                break;
            case 1:
                entity.worldX -= recoilSpeed;
                entity.worldY += recoilSpeed;
                break;
            case 2:
                entity.worldX -= recoilSpeed;
                break;
            case 3:
                entity.worldX -= recoilSpeed;
                entity.worldY -= recoilSpeed;
                break;
            case 4:
                entity.worldY -= recoilSpeed;
                break;
            case 5:
                entity.worldX += recoilSpeed;
                entity.worldY -= recoilSpeed;
                break;
            case 6:
                entity.worldX += recoilSpeed;
                break;
            case 7:
                entity.worldX += recoilSpeed;
                entity.worldY += recoilSpeed;
                break;
        }

         */
    }

}


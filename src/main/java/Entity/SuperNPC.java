package Entity;

import Main.GamePanel;

public class SuperNPC extends Entity{

    public SuperNPC(GamePanel gp, int width, int height){
        super(gp, width, height);
    }

    public void setDialogues(String zero, String one, String two) {
        dialogues[0] = zero;
        dialogues[1] = one;
        dialogues[2] = two;
    }
    public void speak(){

        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        switch (gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "right":
                direction = "left";
                break;
            case "left":
                direction = "right";
                break;

        }
    }

    public void setAction(){}
    public void update(){

        setAction();

        collision = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        //Monster inficlt damage
        if(this.type == 2 && contactPlayer && !gp.player.invincible){
            gp.player.life -= 1;
            gp.player.invincible = true;
        }

        if(!collision) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "rightUp":
                    if(rightOk)
                        worldX += speed;
                    if(upOk)
                        worldY -= speed;
                    break;
                case "leftUp":
                    if(leftOk)
                        worldX -= speed;
                    if(upOk)
                        worldY -= speed;
                    break;
                case "leftDown":
                    if(leftOk)
                        worldX -= speed;
                    if(downOk)
                        worldY += speed;
                    break;
                case "rightDown":
                    if(rightOk)
                        worldX += speed;
                    if(downOk)
                        worldY += speed;
                    break;
                case "idle":
                    break;

            }


            resetDirectionsBoolean();
        }


        spriteCounter++;
        if(spriteCounter>10) {
            if (spriteNum == 1){
                spriteNum = 2;
            }

            else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if(invincible){
            invincibleCounter++;
            if(invincibleCounter>60){
                invincibleCounter=0;
                invincible = false;
            }
        }
    }
}

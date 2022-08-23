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
            case 0:
                direction = 4;
                break;
            case 4:
                direction = 0;
                break;
            case 2:
                direction = 6;
                break;
            case 6:
                direction = 2;
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
                case 0:
                    worldY -= speed;
                    break;
                case 4:
                    worldY += speed;
                    break;
                case 2:
                    worldX += speed;
                    break;
                case 6:
                    worldX -= speed;
                    break;
                case 1:
                    if(rightOk)
                        worldX += speed;
                    if(upOk)
                        worldY -= speed;
                    break;
                case 7:
                    if(leftOk)
                        worldX -= speed;
                    if(upOk)
                        worldY -= speed;
                    break;
                case 5:
                    if(leftOk)
                        worldX -= speed;
                    if(downOk)
                        worldY += speed;
                    break;
                case 3:
                    if(rightOk)
                        worldX += speed;
                    if(downOk)
                        worldY += speed;
                    break;
                case -999: //idle?
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

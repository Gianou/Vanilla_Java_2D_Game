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
}

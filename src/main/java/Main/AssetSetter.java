package Main;

import Entity.NPC_Ghoul;
import Entity.NPC_Owl;
import Object.*;
import Entity.MON_Blob;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setNPC(){
        gp.npc[0] = new NPC_Owl(gp, 1, 1);
        gp.npc[0].worldX = gp.tileSize*25;
        gp.npc[0].worldY = gp.tileSize*11;

        gp.npc[1] = new NPC_Ghoul(gp, 2, 2);
        gp.npc[1].worldX = gp.tileSize*23;
        gp.npc[1].worldY = gp.tileSize*23;
    }
    public void setObject(){

        //This doesn't allow correct drawing priority
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = gp.tileSize*12;
        gp.obj[0].worldY = gp.tileSize*12;

        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = gp.tileSize*12;
        gp.obj[1].worldY = gp.tileSize*38;

        gp.obj[2] = new OBJ_Rock(gp);
        gp.obj[2].worldX = gp.tileSize*10;
        gp.obj[2].worldY = gp.tileSize*10;

        gp.obj[3] = new OBJ_Door(gp);
        gp.obj[3].worldX = gp.tileSize*37;
        gp.obj[3].worldY = gp.tileSize*14;

        gp.obj[4] = new OBJ_Chest(gp);
        gp.obj[4].worldX = gp.tileSize*37;
        gp.obj[4].worldY = gp.tileSize*10;


        gp.obj[21] = new OBJ_Rock(gp);
        gp.obj[21].worldX = gp.tileSize*9;
        gp.obj[21].worldY = gp.tileSize*10;

        gp.obj[22] = new OBJ_Rock(gp);
        gp.obj[22].worldX = gp.tileSize*8;
        gp.obj[22].worldY = gp.tileSize*10;

        gp.obj[23] = new OBJ_Rock(gp);
        gp.obj[23].worldX = gp.tileSize*10;
        gp.obj[23].worldY = gp.tileSize*9;

        gp.obj[24] = new OBJ_Rock(gp);
        gp.obj[24].worldX = gp.tileSize*10;
        gp.obj[24].worldY = gp.tileSize*8;

        gp.obj[25] = new OBJ_Statue(gp, gp.tileSize*16, gp.tileSize*16);
        //gp.obj[25].worldX = gp.tileSize*16;
       // gp.obj[25].worldY = gp.tileSize*16;

    }

    public void setMonster(){
        gp.monster[0] = new MON_Blob(gp, 1, 1);
        gp.monster[0].worldX = gp.tileSize*25;
        gp.monster[0].worldY = gp.tileSize*10;
    }
}

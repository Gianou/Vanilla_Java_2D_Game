package Main;

import Entity.NPC_Ghoul;
import Entity.NPC_Owl;
import Object.*;
import Entity.MON_Blob;

import java.awt.*;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setNPC(){

        gp.npc[0] = new NPC_Owl(gp, 1, 1);
        gp.npc[0].worldX = gp.tileSize*25;
        gp.npc[0].worldY = gp.tileSize*11;

        gp.npc[1] = new NPC_Ghoul(gp, 2, 2, 23, 23);



    }
    public void setObject(){
        setObjectTiles();

        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = gp.tileSize*43;
        gp.obj[0].worldY = gp.tileSize*19;

        gp.obj[4] = new OBJ_Chest(gp);
        gp.obj[4].worldX = gp.tileSize*23;
        gp.obj[4].worldY = gp.tileSize*11;

/*
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


        setForest(30, 9, 30);
        setForest(40,10,32);
        setForest(50,9,35);
        setForest(60,9,38);
        gp.obj[39] = new OBJ_Tree(gp, gp.tileSize*9, gp.tileSize*36 + gp.tileSize/2);


 */
    }

    public void setForest(int index, int x, int y){
        for(int i = 0; i<10; i++){
            gp.obj[index + i] = new OBJ_Tree(gp, gp.tileSize*(x + i*2), gp.tileSize*y);
        }


    }
    public void setMonster(){
        gp.monster[0] = new MON_Blob(gp, 1, 1, 38, 19);

    }

    public void setObjectTiles(){
        int index = 100;
        for (int i = 0; i < gp.tileM.mapTileNum.length; i++){
            for(int j = 0; j< gp.tileM.mapTileNum[i].length; j++){
                if ( gp.tileM.mapTileNum[i][j] == 8 || gp.tileM.mapTileNum[i][j] == 9 || gp.tileM.mapTileNum[i][j] == 10){
                    gp.obj[index] = new OBJ_TileSolidArea(gp);
                    gp.obj[index].worldX = j * gp.tileSize;
                    gp.obj[index].worldY = i * gp.tileSize + gp.tileSize/2;
                    index++;
                }
            }
        }
    }
}

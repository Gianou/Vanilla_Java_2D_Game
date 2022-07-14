package Main;

import Object.*;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = gp.tileSize*20;
        gp.obj[0].worldY = gp.tileSize*20;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = -gp.tileSize;
        gp.obj[1].worldY = 0;

        gp.obj[2] = new OBJ_Rock();
        gp.obj[2].worldX = gp.tileSize*10;
        gp.obj[2].worldY = gp.tileSize*10;
        gp.obj[21] = new OBJ_Rock();
        gp.obj[21].worldX = gp.tileSize*9;
        gp.obj[21].worldY = gp.tileSize*10;
        gp.obj[22] = new OBJ_Rock();
        gp.obj[22].worldX = gp.tileSize*8;
        gp.obj[22].worldY = gp.tileSize*10;
        gp.obj[23] = new OBJ_Rock();
        gp.obj[23].worldX = gp.tileSize*10;
        gp.obj[23].worldY = gp.tileSize*9;
        gp.obj[24] = new OBJ_Rock();
        gp.obj[24].worldX = gp.tileSize*10;
        gp.obj[24].worldY = gp.tileSize*8;

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = gp.tileSize*37;
        gp.obj[3].worldY = gp.tileSize*14;
    }
}

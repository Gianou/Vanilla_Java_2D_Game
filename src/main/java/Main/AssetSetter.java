package Main;

import Object.OBJ_Key;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = gp.tileSize*7;
        gp.obj[0].worldY = gp.tileSize*2;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = -gp.tileSize;
        gp.obj[1].worldY = 0;
    }
}

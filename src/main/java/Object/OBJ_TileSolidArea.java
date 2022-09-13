package Object;

import Main.GamePanel;

import java.awt.*;

public class OBJ_TileSolidArea extends SuperObject{
    public OBJ_TileSolidArea(GamePanel gp) {
        super(gp);
        name = "Rock";
        collision = true;
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize/2);
    }
}

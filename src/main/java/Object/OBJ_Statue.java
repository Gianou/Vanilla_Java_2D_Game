package Object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class OBJ_Statue extends SuperObject{
    public OBJ_Statue(GamePanel gp, int x, int y){
        super(gp);

        worldX = x;
        worldY = y;
        name = "Statue";
        collision = true;
        width = 3;
        height = 3;

        solidArea = new Rectangle(width*gp.tileSize *3/16, height*gp.tileSize*6/16, width*gp.tileSize *10/16, height*gp.tileSize*10/16);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        try{
            File file = new File("src/main/resources/objects/Statue.png");
            FileInputStream fis = new FileInputStream(file);
            image = ImageIO.read(fis);
            image = uT.scaleImage(image, gp.tileSize*width, gp.tileSize*height);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
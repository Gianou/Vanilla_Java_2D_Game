package Object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class OBJ_Statue extends SuperObject{
    public OBJ_Statue(GamePanel gp){

        name = "Statue";
        collision = true;
        width = 3;
        height = 3;

        solidArea = new Rectangle(gp.tileSize*width, gp.tileSize*height);

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
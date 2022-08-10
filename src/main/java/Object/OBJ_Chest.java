package Object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class OBJ_Chest extends SuperObject{
    public OBJ_Chest(GamePanel gp){
        super(gp);
        name = "Chest";
        collision = true;

        try{
            File file = new File("src/main/resources/objects/Chest.png");
            FileInputStream fis = new FileInputStream(file);
            image = ImageIO.read(fis);
            image = uT.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

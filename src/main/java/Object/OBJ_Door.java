

package Object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class OBJ_Door extends  SuperObject{
    public OBJ_Door(GamePanel gp){
        super(gp);
        name = "Door";
        collision = true;

        try{
            File file = new File("src/main/resources/objects/Door.png");
            FileInputStream fis = new FileInputStream(file);
            image = ImageIO.read(fis);
            image = uT.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}


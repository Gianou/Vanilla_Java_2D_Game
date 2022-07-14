package Object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class OBJ_Chest extends SuperObject{
    public OBJ_Chest(){
        name = "Chest";
        collision = true;

        try{
            File file = new File("src/main/resources/objects/Chest.png");
            FileInputStream fis = new FileInputStream(file);
            image = ImageIO.read(fis);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

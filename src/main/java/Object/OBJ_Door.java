

package Object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class OBJ_Door extends  SuperObject{
    public OBJ_Door(){
        name = "Door";
        collision = true;

        try{
            File file = new File("src/main/resources/objects/Door.png");
            FileInputStream fis = new FileInputStream(file);
            image = ImageIO.read(fis);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}


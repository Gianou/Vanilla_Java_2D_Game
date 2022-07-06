package Object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class OBJ_Key extends SuperObject{
    public OBJ_Key(){
        name = "Key";

        try{
            File file = new File("src/main/resources/objects/Key.png");
            FileInputStream fis = new FileInputStream(file);
            image = ImageIO.read(fis);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
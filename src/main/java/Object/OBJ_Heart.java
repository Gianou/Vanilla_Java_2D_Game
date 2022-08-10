package Object;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class OBJ_Heart extends SuperObject{


    public OBJ_Heart(GamePanel gp){
        super(gp);
        name = "Heart";
        collision = false;

        try{
            File file = new File("src/main/resources/objects/FullHeart.png");
            FileInputStream fis = new FileInputStream(file);
            image = ImageIO.read(fis);
            image = uT.scaleImage(image, gp.tileSize, gp.tileSize);

            file = new File("src/main/resources/objects/HalfHeart.png");
            fis = new FileInputStream(file);
            image2 = ImageIO.read(fis);
            image2 = uT.scaleImage(image2, gp.tileSize, gp.tileSize);

            file = new File("src/main/resources/objects/EmptyHeart.png");
            fis = new FileInputStream(file);
            image3 = ImageIO.read(fis);
            image3 = uT.scaleImage(image3, gp.tileSize, gp.tileSize);

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

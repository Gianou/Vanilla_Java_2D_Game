package Tiles;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TileManager {
    GamePanel gp;
    Tile []  tile;

    public TileManager(GamePanel gp) throws IOException {
        this.gp = gp;

        tile = new Tile[10];

        getTileImage();
    }

    public void getTileImage() throws IOException {

        tile[0] = new Tile();
        File file = new File("src/main/resources/tiles/Grass.png");
        FileInputStream fis = new FileInputStream(file);
        tile[0].image = ImageIO.read(fis);

        tile[1] = new Tile();
        file = new File("src/main/resources/tiles/Wall.png");
        fis = new FileInputStream(file);
        tile[1].image = ImageIO.read(fis);

        tile[2] = new Tile();
        file = new File("src/main/resources/tiles/Water.png");
        fis = new FileInputStream(file);
        tile[2].image = ImageIO.read(fis);


    }
    public void draw(Graphics2D g2){
        //g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
        for(int i = 0; i<=12*16*gp.scale ; i+=16*gp.scale){
            for(int j = 0; j<=16*16*gp.scale; j+=16*gp.scale){
                g2.drawImage(tile[0].image, j, i, gp.tileSize, gp.tileSize, null);
            }
        }
    }

}

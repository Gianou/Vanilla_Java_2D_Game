package Tiles;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

/**
 * Class used to read tile images and to draw maps
 */
public class TileManager {
    GamePanel gp;
    Tile []  tile;
    int [][] mapTileNum;

    public TileManager(GamePanel gp) throws IOException {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int [gp.maxScreenRow] [gp.maxScreenCol];

        getTileImage();
        readMapTxt("src/main/resources/maps/01map");
        System.out.println("su");

    }

    /**
     * Reads the images of the different tiles that can be used to draw map and saves them all to the tile[] array
     * @throws IOException
     */
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

    /**
     * Used to draw a map. Uses a double for loop to go through each tile of the GamePanel and paints
     * according to the tile value from readMapTxt()
     * @param g2
     * @throws IOException
     */
    public void draw(Graphics2D g2) throws IOException {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            int tileNum = mapTileNum[row][col];
            System.out.println(tileNum);
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }

    /**
     * Reads .txt file and copies content to an int array.
     * @param filepath, path to .txt file. Should always be in "src/main/resources/maps"
     * @return int array used to know what tile to use when drawing a map
     * @throws FileNotFoundException
     */
    private void readMapTxt(String filepath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filepath));
        int row = 0;
        int col = 0;
        while(scanner.hasNextInt())
        {
            mapTileNum[row][col++] = scanner.nextInt();
            if(col > gp.maxScreenCol-1){
                row++;
                col = 0;
            }
        }
    }
}


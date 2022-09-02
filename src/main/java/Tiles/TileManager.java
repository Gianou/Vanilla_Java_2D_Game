package Tiles;

import Main.GamePanel;
import Main.UtilityTool;
import jdk.jshell.execution.Util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

/**
 * Class used to read tile images and to draw maps
 */
public class TileManager {
    GamePanel gp;
    public Tile []  tile;
    public int [][] mapTileNum;

    public TileManager(GamePanel gp) throws IOException {
        this.gp = gp;

        tile = new Tile[100];
        mapTileNum = new int [gp.maxWorldRow] [gp.maxWorldCol];

        getTileImage();
        readMapTxt("src/main/resources/maps/AI_Tracking_Test");

    }
    public void setUpImage(int index, String imageName, boolean collision) throws IOException {
        UtilityTool uT = new UtilityTool();

        tile[index] = new Tile();
        File file = new File("src/main/resources/tiles/" + imageName + ".png");
        FileInputStream fis = new FileInputStream(file);
        tile[index].image = ImageIO.read(fis);
        tile[index].image = uT.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
        if(collision)
            tile[index].collision = true;

    }


    /**
     * Reads the images of the different tiles that can be used to draw map and saves them all to the tile[] array
     * @throws IOException
     */
    public void getTileImage() throws IOException {

        setUpImage(10, "Grass", false);
        setUpImage(1, "Wall", true);
        setUpImage(15, "Water", true);
        setUpImage(3, "Sand", false);
        setUpImage(4, "Sea1", true);
        setUpImage(5, "Tree4", true);
        setUpImage(6, "Hole", false);

        for(int i = 1; i<=14; i++){
            String name = "Lake" + i;
            setUpImage(i + 20, name, false);
        }

    }

    /**
     * Used to draw a map. Uses a double for loop to go through each tile of the GamePanel and paints
     * according to the tile value from readMapTxt()
     * @param g2
     * @throws IOException
     */
    public void draw(Graphics2D g2) throws IOException {
        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileNum = mapTileNum[worldRow][worldCol];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX  - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY  + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY  - gp.tileSize*2 < gp.player.worldY + gp.player.screenY){

                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;


            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
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
            if(col > gp.maxWorldCol-1){
                row++;
                col = 0;
            }
        }
    }

}


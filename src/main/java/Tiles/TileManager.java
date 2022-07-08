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
    public Tile []  tile;
    public int [][] mapTileNum;

    public TileManager(GamePanel gp) throws IOException {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int [gp.maxWorldRow] [gp.maxWorldCol];

        getTileImage();
        readMapTxt("src/main/resources/maps/02map");

        affiche();

    }
    public void affiche(){
        for (int i = 0; i<gp.maxWorldRow; i++) {
            for (int j = 0; j < gp.maxWorldCol; j++) {
                System.out.print(mapTileNum[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Reads the images of the different tiles that can be used to draw map and saves them all to the tile[] array
     * @throws IOException
     */
    public void getTileImage() throws IOException {

        tile[0] = new Tile();
        File file = new File("src/main/resources/tiles/Grass5.png");
        FileInputStream fis = new FileInputStream(file);
        tile[0].image = ImageIO.read(fis);

        tile[1] = new Tile();
        file = new File("src/main/resources/tiles/Wall.png");
        fis = new FileInputStream(file);
        tile[1].image = ImageIO.read(fis);
        tile[1].collision =true;

        tile[2] = new Tile();
        file = new File("src/main/resources/tiles/Water.png");
        fis = new FileInputStream(file);
        tile[2].image = ImageIO.read(fis);
        tile[2].collision =true;

        tile[3] = new Tile();
        file = new File("src/main/resources/tiles/Sand.png");
        fis = new FileInputStream(file);
        tile[3].image = ImageIO.read(fis);

        tile[4] = new Tile();
        file = new File("src/main/resources/tiles/Sea1.png");
        fis = new FileInputStream(file);
        tile[4].image = ImageIO.read(fis);
        tile[4].collision =true;

        tile[5] = new Tile();
        file = new File("src/main/resources/tiles/Tree4.png");
        fis = new FileInputStream(file);
        tile[5].image = ImageIO.read(fis);
        tile[5].collision =true;
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

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
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


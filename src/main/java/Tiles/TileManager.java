package Tiles;

import Main.GamePanel;

import java.io.IOException;

public class TileManager {
    GamePanel gp;
    Tile []  tile;

    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[10];

        getTileImage();
    }

    public void getTileImage(){
        /*
        try{

        }catch (IOException e) {
            e.printStackTrace();
        }

         */
    }
}

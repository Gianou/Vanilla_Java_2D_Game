package PathFinder;

import Main.GamePanel;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SubMatrix {
    public int line = 5;
    public int col = 5;
    public int [][] subMat = new int[line][col];
    public int monLine = 12;
    public int monCol = 12;

    public Point playerInSub;
    GamePanel gp;
    public SubMatrix(GamePanel gp){

        this.gp = gp;

    }

    public void getSubMat() {

        int lineSub = monLine - line/2 ;
        int lineSubMax = lineSub + line;
        int colSub = monCol - col/2;
        int colSubMax = colSub + col;
//Get the player
        int playerX = (gp.player.worldX + (gp.player.width*gp.tileSize/2))/gp.tileSize;
        int playerY = (gp.player.worldY + (gp.player.height*gp.tileSize/2))/gp.tileSize;
        System.out.println("X : " + playerX + " Y : " + playerY);

        for(int i = 0; lineSub + i < lineSubMax; i++){
            for (int j = 0;colSub + j < colSubMax; j++){
                subMat[i][j] = gp.tileM.mapTileNum[lineSub+i][colSub+j];
                if(lineSub+i == playerY && colSub + j == playerX){
                    subMat[i][j] = 66;
                    playerInSub = new Point(j,i);
                }
            }
        }
        subMat[line/2][col/2] = 99;



    }
    public void display(){
        for(int i = 0; i<subMat.length; i++){
            for(int j = 0; j<subMat[i].length; j++){
                System.out.print(subMat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

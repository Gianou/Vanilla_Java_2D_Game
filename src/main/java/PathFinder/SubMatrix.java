package PathFinder;

import Main.GamePanel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SubMatrix {
    int line = 5;
    int col = 5;
    int [][] subMat = new int[line][col];
    int monLine = 12;
    int monCol = 12;
    GamePanel gp;
    public SubMatrix(GamePanel gp){
        this.gp = gp;
    }

    public void getSubMat() {
        int lineSub = monLine - line/2;
        int lineSubMax = lineSub + line;
        int colSub = monCol - col/2;
        int colSubMax = colSub + col;

        for(int i = 0; lineSub + i < lineSubMax; i++){
            for (int j = 0;colSub + j < colSubMax; j++){
                subMat[i][j] = gp.tileM.mapTileNum[lineSub+i][colSub+j];
                //subMat[i][j] = 8;
            }
        }
    }
    public void display(){
        for(int i = 0; i<subMat.length; i++){
            for(int j = 0; j<subMat[i].length; j++){
                System.out.print(subMat[i][j] + " ");
            }
            System.out.println();
        }
    }
}

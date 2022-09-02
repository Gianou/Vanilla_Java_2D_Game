package PathFinder;

import java.awt.*;

public class PathFinder {
   int [][]map; // 0 means there is nothing, 1 means there is something
    boolean [][]explored = new boolean[5][5];
   Point a = new Point();
   Point b = new Point();
   char path[];
   char[] shortestPath;

    int max;
    static int deltaL[] = {-1, 0, 1, 0 };
    static int deltaC[] = { 0, 1, 0, -1 };
    int shortest = 500;


    public PathFinder(){
        map = new int[][]{  {0, 0, 0, 0, 0},
                            {1, 1, 0, 0, 0},
                            {0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0}};
        a.x = 0;
        a.y = 2;
        b.x = 0; //row
        b.y = 0; //line
        path = new char[map.length*map.length];

    }
//Y = line
    // X = row
    public void recursive(){
        recursive(a.y, a.x, 0 );
    }

    public void recursive(int line, int row, int index){
        if(line == b.y && row == b.x){

            if(pathLength()<shortest){
                shortest = pathLength();
                shortestPath = new char[shortest];
                for(int i = 0; i < shortest; i++){
                    shortestPath[i] = path[i];
                }
            }

        }

        /*
        for(int i = 0; i < 4; i++) { //pour chaque mouvement, 0 = U, 1 = R, 2 = D, 3 = L
            int ligneTest = line + deltaL[i];
            int colTest = row + deltaC[i];

            if(checkNextCase(ligneTest, colTest)) { //Test si on est bien dans la grille et si la case n'a pas déjà été visitée
                switch (i){
                    case 0:
                        path[index] = 'U';
                        break;
                    case 1:
                        path[index] = 'R';
                        break;
                    case 2:
                        path[index] = 'D';
                        break;
                    case 3:
                        path[index] = 'L';
                        break;
                }
                explored[ligneTest][colTest] = true;
                recursive(ligneTest, colTest, index+1); //Appel récursif avec les nouveaux ligne/colonne et index
                path[index] = 'x';
                explored[ligneTest][colTest] = false;
            }
        }
        */

        //Go up
        if(checkNextCase(line-1, row)){
            path [index] = 'U';
            explored[line-1][row] = true;
            recursive(line-1, row, index + 1);
            path [index] = 'x';
            explored[line-1][row] = false;
        }
            //Go down
            if(checkNextCase(line+1, row)){
                path [index] = 'D';
                explored[line+1][row] = true;
                recursive(line+1, row, index + 1);
                path [index] = 'x';
                explored[line+1][row] = false;
            }

            //Go right
            if(checkNextCase(line, row+1)){
                path [index] = 'R';
                explored[line][row+1] = true;
                recursive(line, row+1, index + 1);
                path [index] = 'x';
                explored[line][row+1] = false;
            }
            //Go left
            if(checkNextCase(line, row-1)){
                path [index] = 'L';
                explored[line][row-1] = true;
                recursive(line, row-1, index + 1);
                path [index] = 'x';
                explored[line][row-1] = false;
            }

    }

    public int pathLength(){
        int i = 0;
        int cpt = 0;
        while(path[i] != 'x' && i < path.length-1){
            cpt++;
            i++;
        }
        return cpt;

    }
    public void showPath(){
    int i = 0;

        while(path[i] != 'x' && i < path.length-1){
            System.out.print(path[i]);
            i++;
        }
        System.out.println(" ");
    }
    public boolean checkNextCase(int line, int row){
        boolean caseIsOk = true;
        if(line < 0 || line > map.length-1 || row < 0 || row > map.length-1){
            return false;
        }
        if(map[line][row]==1){
            return false;
        }
        if(explored[line][row]){
            return false;
        }
        return caseIsOk;
    }
}

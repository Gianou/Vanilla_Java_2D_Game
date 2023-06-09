package Main;


import Entity.Entity;
import Entity.Player;
import Entity.SuperNPC;
import Object.SuperObject;
import PathFinder.SubMatrix;
import Sound.Sound;
import Tiles.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{

    // Screen Settings
    final int originalTileSize = 16; //32x32 tile
    public final int scale = 3;
    public final int tileSize = originalTileSize*scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow =9;
    public final int screenWidth = tileSize*maxScreenCol; //768 pixels
    public final int screenHeight = tileSize*maxScreenRow; // 576 pixels

    // World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //SYSTEM
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public MouseHandler mouseH = new MouseHandler(this);
    public GamePadHandler gPadH;
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eventH = new EventHandler(this);
    Thread gameThread;
    //ENTITY OBJECT
    public Player player = new Player(this, keyH, mouseH, 1, 1);
    public SuperObject obj [] = new SuperObject[200];
    public SuperNPC npc[] = new SuperNPC[5];
    public SuperNPC monster[] = new SuperNPC[5];
    ArrayList<Entity> entityList = new ArrayList<>();
    int fps = 60;

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

    //TEST
    SubMatrix subM;



    public GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.setFocusable(true);
        setUpGame();
    }

    public void setUpGame(){

        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        playMusic(0);
        gameState = titleState;
        gPadH = new GamePadHandler(this);
        //subM = new SubMatrix(this);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        double drawInterval = 1000000000/fps;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){

            long currentTime = System.nanoTime();

            // update
            update();

            // draw
            repaint(); //This is how you call paintComponent()

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime<0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){
        if(gPadH.controllerOn){
            gPadH.update();
        }

        // So that we cannot move the player if the game is in pause
        if(gameState == playState){

            //PLAYER
            player.update();
            //NPC
            for (int i = 0; i < npc.length; i++){
                if(npc[i] != null)
                    npc[i].update();
            }
            //Monster
            for (int i = 0; i < monster.length; i++){
                if(monster[i] != null){
                    if(monster[i].alive){
                        monster[i].update();
                    }
                    if(!monster[i].alive){
                        monster[i] = null;
                    }
                }
            }
        }
        if(gameState == pauseState){

        }

        if(gameState == dialogueState){
                //PLAYER
                player.update();
                /*
                if(keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed){

                    gameState = playState;
                } */
        }
        if(gameState == titleState){
            if(keyH.enterPressed){
                gameState = playState;
            }
        }


    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //Debug
        long drawStart = 0;
        drawStart = System.nanoTime();


        // TITLE SCREEN
        if(gameState == titleState){

            ui.draw(g2);
        }

        else{
            // TILE
            try {
                tileM.draw(g2);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            // Add Entities to list
            entityList.add(player);

            for (int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    entityList.add(npc[i]);
                }
            }

            for (int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    entityList.add(obj[i]);
                }
            }

            for (int i = 0; i < monster.length; i++){
                if(monster[i] != null){
                    entityList.add(monster[i]);
                }
            }
            //Sort list
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    //int result = Integer.compare(e1.worldY, e2.worldY);
                    int result = Integer.compare(e1.worldY+e1.solidAreaDefaultY, e2.worldY+e2.solidAreaDefaultY);
                    //int result = Integer.compare(e1.solidAreaDefaultY, e2.solidAreaDefaultY);
                    return result;
                }
            });
            // Entities
            for(int i = 0; i<entityList.size(); i++){
                entityList.get(i).draw(g2);
            }
            //Empty list
            entityList.clear();

            //UI
            ui.draw(g2);

            //Debug
            if(keyH.debug){
                long drawEnd = System.nanoTime();
                long passed = (drawEnd - drawStart);
                g2.setColor(Color.BLACK);
                g2.drawString("DrawTime : " + passed, 10, 400);
            }



            g2.dispose();
        }


    }

    public void playMusic(int i){
        music.setFile(0);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}

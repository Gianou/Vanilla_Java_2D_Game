package Main;

import Entity.Entity;
import Entity.Player;
import Sound.Sound;
import Tiles.TileManager;
import Object.SuperObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;
    //ENTITY OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj [] = new SuperObject[30];
    public Entity npc[] = new Entity[5];
    int fps = 60;

    //GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;


    public GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        setUpGame();
    }

    public void setUpGame(){

        aSetter.setObject();
        aSetter.setNPC();
        playMusic(0);
        gameState = playState;
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

        // So that we cannot move the player if the game is in pause
        if(gameState == playState){
            //PLAYER
            player.update();
            //NPC
            for (int i = 0; i < npc.length; i++){
                if(npc[i] != null)
                    npc[i].update();
            }
        }
        if(gameState == pauseState){

        }


    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //Debug
        long drawStart = 0;
        drawStart = System.nanoTime();


        // TILE
        try {
            tileM.draw(g2);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // OBJECT
        for (int i = 0; i < obj.length; i++){
            if(obj[i] != null)
                obj[i].draw(g2, this);
        }
        // NPC
        for (int i = 0; i < npc.length; i++){
            if(npc[i] != null)
                npc[i].draw(g2, this);
        }

        player.draw(g2);

        //UI
        ui.draw(g2);

        //Debug
        if(keyH.checkTime){
            long drawEnd = System.nanoTime();
            long passed = (drawEnd - drawStart);
            g2.setColor(Color.BLACK);
            g2.drawString("DrawTime : " + passed, 10, 400);
            System.out.println(passed);
        }



        g2.dispose();
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

package Main;

import Entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // Screen Settings
    final int originalTileSize = 32; //32x32 tile
    final int scale = 4;
    public final int tileSize = originalTileSize*scale;
    final int maxScreenCol = 8;
    final int maxScreenRow = 6;
    final int screenWidth = tileSize*maxScreenCol; //768 pixels
    final int screenHeight = tileSize*maxScreenRow; // 576 pixels

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 12;
    int fps = 60;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLUE);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
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

        player.update();


    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        g2.dispose();
    }
}

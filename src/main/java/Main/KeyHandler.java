package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean rightPressed, leftPressed, downPressed, upPressed, spacePressed, enterPressed;
    public boolean debug;
    public boolean  tPressed;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        //PLAY
        if(gp.gameState == gp.playState) {

            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_SPACE) {
                spacePressed = true;
            }
            if (code == MouseEvent.BUTTON1) {
                spacePressed = true;
            }

            if (code == KeyEvent.VK_P){
                gp.gameState = gp.pauseState;
            }

            if (code == KeyEvent.VK_T){
                tPressed = true;
            }
            if(code== KeyEvent.VK_ENTER){
                enterPressed = true;
            }


            if (code == KeyEvent.VK_I) {
                if (debug == false) {
                    debug = true;
                } else if (debug) {
                    debug = false;
                }
            }
        }

            //PAUSE
        else if(gp.gameState == gp.pauseState) {
            if(code== KeyEvent.VK_P){
                gp.gameState = gp.playState;
            }

        }

        //DIALOGUE
        else if(gp.gameState == gp.dialogueState){
// ADD SMTH TO MAKE PLAYER IDDLE
            if (code == KeyEvent.VK_SPACE){
                gp.gameState = gp.playState;
            }
            if (code == KeyEvent.VK_D) {
                gp.gameState = gp.playState;
                rightPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                gp.gameState = gp.playState;
                leftPressed = true;
            }
            if (code == KeyEvent.VK_W) {
                gp.gameState = gp.playState;
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                gp.gameState = gp.playState;
                downPressed = true;
            }
        }

        //TITLE
        else if (gp.gameState == gp.titleState){
            if(code== KeyEvent.VK_ENTER || gp.gPadH.button[7]){
                enterPressed = true;
            }
            if (code == KeyEvent.VK_W) {
                gp.ui.menuNum--;
                if(gp.ui.menuNum < 0){
                    gp.ui.menuNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.menuNum++;
                if(gp.ui.menuNum > 2){
                    gp.ui.menuNum = 0;
                }
            }

            if (enterPressed){
                if(gp.ui.menuNum == 0){
                    gp.gameState = gp.playState;
                }
                if (gp.ui.menuNum == 1){

                }
                if (gp.ui.menuNum == 2){
                    System.exit(1);
                }
            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        if(code== KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code== KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code== KeyEvent.VK_W){
            upPressed = false;
        }
        if(code== KeyEvent.VK_S){
            downPressed = false;
        }
        if(code== KeyEvent.VK_SPACE){
            spacePressed = false;
        }
        if(code== KeyEvent.VK_T){
            tPressed = false;
        }
        if(code== KeyEvent.VK_ENTER){
            enterPressed = false;
        }
    }

}

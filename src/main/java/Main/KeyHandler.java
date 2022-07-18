package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean rightPressed, leftPressed, downPressed, upPressed;
    public boolean checkTime, tPressed;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(code== KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code== KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code== KeyEvent.VK_W){
            upPressed = true;
        }
        if(code== KeyEvent.VK_S){
            downPressed = true;
        }
        if(code== KeyEvent.VK_P){
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }

            else if(gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
            }

        }
        if(code== KeyEvent.VK_T){
            if(checkTime == false){
                checkTime = true;
            }
            else if (checkTime == true){
                checkTime = false;
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
    }
}

package Main;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class MouseHandler implements MouseListener {
    GamePanel gp;
    public boolean leftClick;


    public MouseHandler (GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        int code = e.getButton();

        if(gp.gameState == gp.playState) {

            if (code == MouseEvent.BUTTON1) {
                leftClick = true;
                System.out.println(leftClick);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int code = e.getButton();
        if(code == MouseEvent.BUTTON1){
            leftClick = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

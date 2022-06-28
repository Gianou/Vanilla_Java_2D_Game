package Main;

import javax.swing.*;

public class MainBat {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        frame.setTitle("Bat Game");
        ImageIcon batIcon = new ImageIcon("src/main/resources/BatIcon2Big.png");
        frame.setIconImage(batIcon.getImage());

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.pack();
        //frame.setLocation(null);
        frame.setVisible(true);

        gamePanel.startGameThread();
    }
}

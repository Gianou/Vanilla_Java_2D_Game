import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BatResolutionTest {
    public static void main(String[] args) throws IOException {
        /*
        JFrame frame = new JFrame();
        int width = 455, height = 256;
        int scale = 4;

        frame.setSize(width*scale, height*scale);
        frame.setVisible(true);

        ImageIcon batIcon = new ImageIcon("src/main/resources/BatStill 1.jpg");
        Image scaleImage = batIcon.getImage().getScaledInstance(batIcon.getIconWidth()*scale,batIcon.getIconHeight()*scale, Image.SCALE_SMOOTH);
        batIcon = new ImageIcon(scaleImage);
        JButton image = new JButton(batIcon);
        image.setBorderPainted(false);
        image.setBorder(null);
        image.setContentAreaFilled(false);
        image.setVisible(true);

        frame.add(image);


         */
        for (int i = 0; i<50; i++){
            for (int j = 0; j<50; j++){
                if(i == 0 || i == 49 || i == 49/2 || j == 0 || j == 49 || j == 49/2){
                    System.out.print(1 + " ");
                }
                else
                    System.out.print(0 + " ");
            }
            System.out.println();
        }
    }
}

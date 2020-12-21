package Page;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

abstract class Pageframe {

        JFrame frame=new JFrame();
        public void setFrame() {
            Image img=new ImageIcon("wallpaper.jpg").getImage();
            frame.getContentPane().setBackground(Color.PINK);
            frame.getContentPane().setLayout(null);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(false);
        }
        public void setVisible(boolean status){
            frame.setVisible(status);
        }
}

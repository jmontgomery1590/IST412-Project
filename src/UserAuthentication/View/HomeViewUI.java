package UserAuthentication.View;

import javax.swing.*;
import java.awt.*;

public class HomeViewUI extends JFrame{
    private JFrame homeFrame;
    private JPanel homePanel;
    private String pcUserName = System.getenv("USERNAME");
    private String imagePath = "C://Users//" + pcUserName + "//IdeaProjects//IST412-Project//images//PSU.png";

    public HomeViewUI(){
        homeFrame = new JFrame("Home Page");
        ImageIcon backgroundImage = new ImageIcon("C://Users//" + pcUserName + "//IdeaProjects//IST412-Project//images//PSU.png");
        homePanel = new JPanel(new BorderLayout()) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }

            @Override
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width = Math.max(backgroundImage.getIconWidth(), size.width);
                size.height = Math.max(backgroundImage.getIconHeight(), size.height);
                return size;
            }
        };
    }

    public JPanel getHomePanel() {
        return homePanel;
    }
}

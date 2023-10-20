package UserAuthentication.View;

import javax.swing.*;

public class HomeViewUI {
    private JPanel homePanel;
    private JLabel homePageLabel;

    public HomeViewUI(){}

    public JPanel getHomePanel() {
        return homePanel;
    }

    public void setHomePanel(JPanel homePanel) {
        this.homePanel = homePanel;
    }

    public JLabel getHomePageLabel() {
        return homePageLabel;
    }

    public void setHomePageLabel(JLabel homePageLabel) {
        this.homePageLabel = homePageLabel;
    }
}

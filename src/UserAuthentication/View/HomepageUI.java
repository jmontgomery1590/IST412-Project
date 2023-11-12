package UserAuthentication.View;

import UserAuthentication.Controller.HomepageController;

import javax.swing.*;
import java.awt.*;

public class HomepageUI extends JFrame{
	private JFrame homeFrame;
	private JButton homeButton, coursesButton, profileButton, exitButton;
	private JPanel navigationPanel;
	private JPanel HomePage, viewPanel, homePanel;
	private JLabel homeWelcomeLabel;
	private HomepageController homepageCntrl;
	private CardLayout cardSwapper;


	public HomepageUI (HomepageController homepageController) {
		homepageCntrl = homepageController;
		homeFrame = new JFrame("Slate LMS");

		// CardSwapper creates an easy-to-use way to swap
		// between our different UI's without reloading a
		// new window over and over
		cardSwapper = new CardLayout();
		viewPanel.setLayout(cardSwapper);
		homeFrame.getContentPane().add(HomePage);
		homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		homeFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//homeFrame.setUndecorated(true);
		//homeFrame.pack();
		homeFrame.setVisible(true);
	}

	public void loadNewView(JPanel newViewPanel){
		this.setViewPanel(newViewPanel);
	}


	public JFrame getHomeFrame() {
		return homeFrame;
	}

	public void setHomeFrame(JFrame homeFrame) {
		this.homeFrame = homeFrame;
	}

	public JButton getHomeButton() {
		return homeButton;
	}

	public void setHomeButton(JButton homeButton) {
		this.homeButton = homeButton;
	}

	public JButton getCoursesButton() {
		return coursesButton;
	}

	public void setCoursesButton(JButton coursesButton) {
		this.coursesButton = coursesButton;
	}

	public JButton getProfileButton() {
		return profileButton;
	}

	public void setProfileButton(JButton profileButton) {
		this.profileButton = profileButton;
	}

	public JButton getExitButton() {
		return exitButton;
	}

	public void setExitButton(JButton exitButton) {
		this.exitButton = exitButton;
	}

	public JPanel getNavigationPanel() {
		return navigationPanel;
	}

	public void setNavigationPanel(JPanel navigationPanel) {
		this.navigationPanel = navigationPanel;
	}

	public JPanel getHomePage() {
		return HomePage;
	}

	public void setHomePage(JPanel homePage) {
		HomePage = homePage;
	}

	public JPanel getViewPanel() {
		return viewPanel;
	}

	public void setViewPanel(JPanel viewPanel) {
		this.viewPanel = viewPanel;
	}

	public HomepageController getHomepageCntrl() {
		return homepageCntrl;
	}

	public void setHomepageCntrl(HomepageController homepageCntrl) {
		this.homepageCntrl = homepageCntrl;
	}

	public JPanel getHomePanel() {
		return homePanel;
	}

	public void setHomePanel(JPanel homePanel) {
		this.homePanel = homePanel;
	}

	public JLabel getHomeWelcomeLabel() {
		return homeWelcomeLabel;
	}

	public void setHomeWelcomeLabel(JLabel homeWelcomeLabel) {
		this.homeWelcomeLabel = homeWelcomeLabel;
	}

	public CardLayout getCardSwapper() {
		return cardSwapper;
	}

	public void setCardSwapper(CardLayout cardSwapper) {
		this.cardSwapper = cardSwapper;
	}
}

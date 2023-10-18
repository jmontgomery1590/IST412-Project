package UserAuthentication.View;

import CourseManagement.View.CourseMgmtInterface;
import UserAuthentication.Controller.HomepageController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomepageUI extends JFrame{
	private JFrame homeFrame;
	private JButton homeButton, coursesButton, profileButton, exitButton;
	private JPanel navigationPanel;
	private JPanel HomePage;
	private JPanel viewPanel;
	private HomepageController homepageCntrl;


	public HomepageUI (HomepageController homepageController) {
		this.setHomepageCntrl(homepageController);
		this.setHomeFrame(new JFrame("Slate LMS"));
		this.getHomeFrame().add(this.getHomePage());
		this.getHomeFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getHomeFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.getHomeFrame().setVisible(true);
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
}

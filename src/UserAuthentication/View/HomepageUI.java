package UserAuthentication.View;

import CourseManagement.Controller.CourseMgmtController;
import ProfileManagement.Controller.ProfileMgmtController;
import UserAuthentication.Controller.HomepageController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		addALButtons();
	}

	private void addALButtons(){
		homeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				homepageCntrl.setHomeViewUI(new HomeViewUI());

				viewPanel.add(homepageCntrl.getHomeViewUI().getHomePanel(), "Home");
				cardSwapper.show(viewPanel, "Home");
				viewPanel.revalidate();
				viewPanel.repaint();
			}
		});

		coursesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				homepageCntrl.setCourseMgmtCntrl(new CourseMgmtController(homepageCntrl));
				viewPanel.add(homepageCntrl.getCourseMgmtCntrl().getCourseMgmtUI().getCourseMgmtPanel(), "Courses");

				cardSwapper.show(viewPanel, "Courses");
				viewPanel.revalidate();
				viewPanel.repaint();
			}
		});

		profileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				homepageCntrl.setStudentMgmtCntrl(new ProfileMgmtController(homepageCntrl));
				viewPanel.add(homepageCntrl.getStudentMgmtCntrl().getStudentMgmtUI().getStudentMgmtPanel(), "User Profile");

				cardSwapper.show(viewPanel, "User Profile");
				viewPanel.revalidate();
				viewPanel.repaint();
			}
		});

		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	public JFrame getHomeFrame() {
		return homeFrame;
	}

	public JButton getHomeButton() {
		return homeButton;
	}

	public JButton getCoursesButton() {
		return coursesButton;
	}

	public JButton getProfileButton() {
		return profileButton;
	}

	public JButton getExitButton() {
		return exitButton;
	}

	public JPanel getViewPanel() {
		return viewPanel;
	}

	public void setViewPanel(JPanel viewPanel) {
		this.viewPanel = viewPanel;
	}

	public CardLayout getCardSwapper() {
		return cardSwapper;
	}
}

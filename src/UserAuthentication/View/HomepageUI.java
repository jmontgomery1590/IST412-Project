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
	private JPanel navigationPanel, HomePage, viewPanel, homePanel;
	private JLabel homeWelcomeLabel;
	private HomepageController homepageCntrl;
	private CardLayout cardSwapper;
	private String pcUserName = System.getenv("USERNAME");

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
		homeFrame.setVisible(true);
		loadHomeImage();
		addALButtons();
	}

	public void loadHomeImage(){
		ImageIcon backgroundImage = new ImageIcon("C://Users//" + pcUserName + "//IdeaProjects//IST412-Project//images//resizedPSU.png");
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

		viewPanel.add(homePanel, "Home");

		cardSwapper.show(viewPanel, "Home");
		viewPanel.revalidate();
		viewPanel.repaint();
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

	public JPanel getViewPanel() {
		return viewPanel;
	}

	public CardLayout getCardSwapper() {
		return cardSwapper;
	}
}

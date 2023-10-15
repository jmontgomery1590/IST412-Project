package UserAuthentication;

import javax.swing.*;
import java.awt.*;

public class HomepageUI {
	public JFrame homeFrame;
	public JButton addCourse, homeButton, coursesButton, profileButton;
	public JPanel navigationPanel, controlPanel;

	public HomepageUI () {
		homeFrame = new JFrame("Slate LMS");
		homeFrame.getContentPane().setBackground(Color.WHITE);
		homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addCourse = new JButton("Add New Course");
		homeButton = new JButton("Home");
		coursesButton = new JButton("Courses");
		profileButton = new JButton("Profile");

		navigationPanel = new JPanel(new GridLayout(4, 1));
		navigationPanel.add(homeButton);
		navigationPanel.add(coursesButton);
		navigationPanel.add(profileButton);

		controlPanel = new JPanel(new GridLayout(1, 3));
		controlPanel.add(addCourse);

		homeFrame.getContentPane().add(navigationPanel, BorderLayout.WEST);
		homeFrame.getContentPane().add(controlPanel, BorderLayout.SOUTH);
		homeFrame.setSize(1500, 1000);
		homeFrame.setVisible(true);
	}
}

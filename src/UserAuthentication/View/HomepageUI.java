package UserAuthentication.View;

import CourseManagement.View.CourseMgmtInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomepageUI {
	public JFrame homeFrame;
	public JButton homeButton, coursesButton, profileButton, exitButton;
	public JPanel navigationPanel;
	public JLabel welcomeText;

	public HomepageUI () {
		homeFrame = new JFrame("Slate LMS");
		homeFrame.getContentPane().setBackground(Color.BLACK);
		homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		homeButton = new JButton("Home");
		homeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HomepageUI homepageUI = new HomepageUI();
				homeFrame.setVisible(false);
			}
		});

		coursesButton = new JButton("Courses");
		coursesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CourseMgmtInterface courseUI = new CourseMgmtInterface();
				homeFrame.setVisible(false);
			}
		});

		profileButton = new JButton("Profile");

		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		welcomeText = new JLabel("Welcome to the Slate Learning Management System. Please choose an option above.");
		welcomeText.setFont(new Font("Times New Roman", Font.BOLD, 38));
		welcomeText.setForeground(Color.WHITE);
		welcomeText.setHorizontalAlignment(JLabel.CENTER);
		welcomeText.setVerticalAlignment(JLabel.CENTER);

		navigationPanel = new JPanel(new GridLayout(1, 4));
		navigationPanel.add(homeButton);
		navigationPanel.add(coursesButton);
		navigationPanel.add(profileButton);
		navigationPanel.add(exitButton);

		homeFrame.getContentPane().add(navigationPanel, BorderLayout.NORTH);
		homeFrame.add(welcomeText);
		homeFrame.setSize(1500, 1000);
		homeFrame.setVisible(true);

	}
}

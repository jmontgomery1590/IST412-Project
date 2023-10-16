package CourseManagement.View;

import CourseManagement.Model.Course;
import UserAuthentication.View.HomepageUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class CourseMgmtInterface {
    public DefaultTableModel tableModel;
    public JFrame courseFrame;
    public JButton homeButton, coursesButton, profileButton, viewButton, addButton, editButton, deleteButton, exitButton;
    public JPanel navigationPanel, controlPanel;
    public JTable courseTable;

    public CourseMgmtInterface() {
        courseFrame = new JFrame("Courses");
        courseFrame.getContentPane().setBackground(Color.WHITE);
        courseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Object columnNames[] = {"Course Name", "Course Number", "Instructor"};
        Object rowData[][] = {};
        tableModel = new DefaultTableModel(rowData, columnNames);
        courseTable = new JTable(tableModel);

        homeButton = new JButton("Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomepageUI homepageUI = new HomepageUI();
                courseFrame.setVisible(false);
            }
        });

        coursesButton = new JButton("Courses");
        coursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CourseMgmtInterface courseUI = new CourseMgmtInterface();
                courseFrame.setVisible(false);
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

        viewButton = new JButton("View Course");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CourseDetailInterface courseDetailUI = new CourseDetailInterface();
                courseFrame.setVisible(false);
            }
        });

        addButton = new JButton("Add Course");
        editButton = new JButton("Edit Course");
        deleteButton = new JButton("Delete Course");

        navigationPanel = new JPanel(new GridLayout(1, 4));
        navigationPanel.add(homeButton);
        navigationPanel.add(coursesButton);
        navigationPanel.add(profileButton);
        navigationPanel.add(exitButton);

        controlPanel = new JPanel(new GridLayout(1, 3));
        controlPanel.add(viewButton);
        controlPanel.add(addButton);
        controlPanel.add(editButton);
        controlPanel.add(deleteButton);

        courseFrame.getContentPane().add(navigationPanel, BorderLayout.NORTH);
        courseFrame.getContentPane().add(controlPanel, BorderLayout.SOUTH);
        courseFrame.setSize(1500, 1000);
        courseFrame.setVisible(true);
    }

    /**
     * Displays the chosen course interface
     */
    public Course createCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nCourse ID#:");
        String courseID = scanner.nextLine();
        System.out.println("Course Name:");
        String courseName = scanner.nextLine();
        System.out.println("Max Enrolled:");
        int max = 0;
        while (scanner.hasNext()){
            if (!scanner.hasNextInt()){
                System.out.println("Please input a valid integer.");
                scanner.next();
            } else {
                max = scanner.nextInt();
                break;
            }
        }
        System.out.println("\n---Course Created Successfully---\n");
        return new Course(courseID, courseName, max);
    }
}

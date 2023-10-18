package CourseManagement.View;

import CourseManagement.Model.Course;
import UserAuthentication.View.HomepageUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class CourseDetailInterface {
    public DefaultTableModel tableModel;
    public JFrame courseDetailFrame;
    public JButton homeButton, coursesButton, profileButton, viewButton, addButton, editButton, exitButton, deleteButton;
    public JPanel navigationPanel, controlPanel;
    public JTable courseTable;

    public CourseDetailInterface() {
        courseDetailFrame = new JFrame("Courses");
        courseDetailFrame.getContentPane().setBackground(Color.WHITE);
        courseDetailFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Object columnNames[] = {"Page Name", "Page Type"};
        Object rowData[][] = {};
        tableModel = new DefaultTableModel(rowData, columnNames);
        courseTable = new JTable(tableModel);

        homeButton = new JButton("Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //HomepageUI homepageUI = new HomepageUI();
                courseDetailFrame.setVisible(false);
            }
        });

        coursesButton = new JButton("Courses");
        coursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CourseMgmtInterface courseUI = new CourseMgmtInterface();
                courseDetailFrame.setVisible(false);
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

        viewButton = new JButton("View Page");
        addButton = new JButton("Add Page");
        editButton = new JButton("Edit Page");
        deleteButton = new JButton("Delete Page");

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

        courseDetailFrame.getContentPane().add(navigationPanel, BorderLayout.NORTH);
        courseDetailFrame.getContentPane().add(controlPanel, BorderLayout.SOUTH);
        courseDetailFrame.setSize(1500, 1000);
        courseDetailFrame.setVisible(true);
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

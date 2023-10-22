package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Page;

import javax.swing.*;
import java.util.Scanner;

public class ViewCourseUI {
    private JPanel pageMgmtPanel;
    private JPanel crudPanel;
    private JButton addPageButton;
    private JButton editPageButton;
    private JButton viewPageButton;
    private JButton deletePageButton;
    private JTable pageTable;

    private CourseMgmtController courseMgmtCntrl;

    public ViewCourseUI(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;
        pageTable.setModel(courseMgmtCntrl.getPageTable());
    }



    public Page createPage(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nCourse Page Title:");
        String pageTitle = scanner.nextLine();
        System.out.println("Course Page Content:");
        String pageBody = scanner.nextLine();
        System.out.println("\n---Page Created Successfully---\n");
        return new Page(pageTitle);
    }

    public JPanel getPageMgmtPanel() {
        return pageMgmtPanel;
    }

    public void setPageMgmtPanel(JPanel pageMgmtPanel) {
        this.pageMgmtPanel = pageMgmtPanel;
    }

    public JPanel getCrudPanel() {
        return crudPanel;
    }

    public void setCrudPanel(JPanel crudPanel) {
        this.crudPanel = crudPanel;
    }

    public JButton getAddPageButton() {
        return addPageButton;
    }

    public void setAddPageButton(JButton addPageButton) {
        this.addPageButton = addPageButton;
    }

    public JButton getEditPageButton() {
        return editPageButton;
    }

    public void setEditPageButton(JButton editPageButton) {
        this.editPageButton = editPageButton;
    }

    public JButton getViewPageButton() {
        return viewPageButton;
    }

    public void setViewPageButton(JButton viewPageButton) {
        this.viewPageButton = viewPageButton;
    }

    public JButton getDeletePageButton() {
        return deletePageButton;
    }

    public void setDeletePageButton(JButton deletePageButton) {
        this.deletePageButton = deletePageButton;
    }

    public JTable getPageTable() {
        return pageTable;
    }

    public void setPageTable(JTable pageTable) {
        this.pageTable = pageTable;
    }

    public CourseMgmtController getCourseMgmtCntrl() {
        return courseMgmtCntrl;
    }

    public void setCourseMgmtCntrl(CourseMgmtController courseMgmtCntrl) {
        this.courseMgmtCntrl = courseMgmtCntrl;
    }
}

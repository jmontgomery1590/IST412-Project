package CourseworkManagement.View;

import CourseworkManagement.Controller.CourseworkMgmtController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AssignmentInterface extends JFrame{
    public AssignmentInterface (CourseworkMgmtController courseworkMgmtCntrl) {
        this.courseworkMgmtCntrl = courseworkMgmtCntrl;
        assignmentFrame = new JFrame("Assignment Builder");
        assignmentFrame.setResizable(false);
        assignmentFrame.setMinimumSize(new Dimension(700, 600));
        assignmentFrame.setContentPane(newAssignmentPanel);
        assignmentFrame.setLocationRelativeTo(null);
        assignmentFrame.setVisible(true);
        assignmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        assignmentFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onWindowClosing();
            }
        });
    }

    // This method will be called when the window is closing
    private void onWindowClosing() {
        courseworkMgmtCntrl.getCourseworkMgmtInterface().getView().setEnabled(true);
        courseworkMgmtCntrl.getCourseworkMgmtInterface().getView().transferFocus();
    }


    private JFrame assignmentFrame;
    private CourseworkMgmtController courseworkMgmtCntrl;
    private JPanel newAssignmentPanel;
    private JButton createAssignmentButton;
    private JButton cancelButton;
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel buttonPanel;
    private JTextField assignmentNameTextField;
    private JButton addQuestionButton;
    private JList questionList;
    private JPanel listPanel;
    private JPanel assignmentNamePanel;
    private JLabel questionListLabel;

    public JPanel getNewAssignmentPanel() {
        return newAssignmentPanel;
    }

    public void setNewAssignmentPanel(JPanel newAssignmentPanel) {
        this.newAssignmentPanel = newAssignmentPanel;
    }

    public JButton getCreateAssignmentButton() {
        return createAssignmentButton;
    }

    public void setCreateAssignmentButton(JButton createAssignmentButton) {
        this.createAssignmentButton = createAssignmentButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(JPanel titlePanel) {
        this.titlePanel = titlePanel;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(JLabel titleLabel) {
        this.titleLabel = titleLabel;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public JTextField getAssignmentNameTextField() {
        return assignmentNameTextField;
    }

    public void setAssignmentNameTextField(JTextField assignmentNameTextField) {
        this.assignmentNameTextField = assignmentNameTextField;
    }

    public JButton getAddQuestionButton() {
        return addQuestionButton;
    }

    public void setAddQuestionButton(JButton addQuestionButton) {
        this.addQuestionButton = addQuestionButton;
    }

    public JList getQuestionList() {
        return questionList;
    }

    public void setQuestionList(JList questionList) {
        this.questionList = questionList;
    }

    public JPanel getListPanel() {
        return listPanel;
    }

    public void setListPanel(JPanel listPanel) {
        this.listPanel = listPanel;
    }

    public JPanel getAssignmentNamePanel() {
        return assignmentNamePanel;
    }

    public void setAssignmentNamePanel(JPanel assignmentNamePanel) {
        this.assignmentNamePanel = assignmentNamePanel;
    }

    public JFrame getAssignmentFrame() {
        return assignmentFrame;
    }

    public void setAssignmentFrame(JFrame assignmentFrame) {
        this.assignmentFrame = assignmentFrame;
    }
}

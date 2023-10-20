package UserAuthentication.Controller;

import CourseManagement.Controller.CourseMgmtController;
import CourseworkManagement.Controller.CourseworkMgmtController;
import UserAuthentication.Model.User;
import UserAuthentication.View.HomeViewUI;
import UserAuthentication.View.HomepageUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomepageController implements ActionListener {
    private User user;
    private HomepageUI homepageUI;
    private LoginController loginController;
    private CourseMgmtController courseMgmtCntrl;
    private HomeViewUI homeViewUI;


    public HomepageController(LoginController loginController){
        this.setUser(loginController.getU1());
        this.setHomepageUI(new HomepageUI(this));

        // set up the ActionListeners for buttons
        addALButtons();
    }

    /**
     * Assigns ActionListener to buttons
     */
    public void addALButtons(){
        this.getHomepageUI().getHomeButton().addActionListener(this);
        this.getHomepageUI().getCoursesButton().addActionListener(this);
        this.getHomepageUI().getProfileButton().addActionListener(this);
        this.getHomepageUI().getExitButton().addActionListener(this);
    }

    /**
     * Listener for user interaction. Performs action
     * based on what was clicked/done.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      // Assign e to a generic object
        Object obj = e.getSource();

        // traverse different if statements to find what to do

        // If Courses Navigation button is clicked
        if (obj == this.getHomepageUI().getCoursesButton()){

            // Check for first time clicking to build the UI & build controller
            if (this.getCourseMgmtCntrl() == null) {
                this.setCourseMgmtCntrl(new CourseMgmtController(this));

                // Add the UI to the homepage view panel
                this.getHomepageUI().getViewPanel().add(this.getCourseMgmtCntrl().getCi().getCourseMgmtPanel(), "Courses");
            }

            // Choose to show the courses UI in the view panel
            // Revalidate & Repaint refresh the view window w/ the new content
            this.getHomepageUI().getCardSwapper().show(this.getHomepageUI().getViewPanel(), "Courses");
            this.getHomepageUI().getViewPanel().revalidate();
            this.getHomepageUI().getViewPanel().repaint();
        }
        // If Home Navigation button is clicked
        if (obj == this.getHomepageUI().getHomeButton()){

            // Check for first time clicking to build the UI & build controller
            if (this.getHomeViewUI() == null){
                this.setHomeViewUI(new HomeViewUI());

                // Add the UI to the homepage view panel
                this.getHomepageUI().getViewPanel().add(this.getHomeViewUI().getHomePanel(), "Home");
            }

            // Choose to show the courses UI in the view panel
            // Revalidate & Repaint refresh the view window w/ the new content
            this.getHomepageUI().getCardSwapper().show(this.getHomepageUI().getViewPanel(), "Home");
            this.getHomepageUI().getViewPanel().revalidate();
            this.getHomepageUI().getViewPanel().repaint();
        }

        // Close the application if Exit is pressed
        if (obj == this.getHomepageUI().getExitButton()){
            System.exit(0);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HomepageUI getHomepageUI() {
        return homepageUI;
    }

    public void setHomepageUI(HomepageUI homepageUI) {
        this.homepageUI = homepageUI;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public CourseMgmtController getCourseMgmtCntrl() {
        return courseMgmtCntrl;
    }

    public void setCourseMgmtCntrl(CourseMgmtController courseMgmtCntrl) {
        this.courseMgmtCntrl = courseMgmtCntrl;
    }

    public HomeViewUI getHomeViewUI() {
        return homeViewUI;
    }

    public void setHomeViewUI(HomeViewUI homeViewUI) {
        this.homeViewUI = homeViewUI;
    }
}

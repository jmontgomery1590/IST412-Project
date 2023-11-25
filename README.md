# IST412-Project
Test profiles have the following login info for various user functionalities:
  
	***Usernames are not case sensitive
	***Passwords are case sensitive
      
      Student 1:
          Username: student
          Password: Student123
      Student 2:
          Username: student2
          Password: Student321
      TA:
          Username: TA
          Password: TA123
      Instructor 1:
          Username: instructor
          Password: Instructor123
      Instructor 2:
          Username: instra
          Password: Instructor321
      Admin 1:
          Username: admin
          Password: Admin123
      Admin 1:
          Username: admina
          Password: Admin321

Database paths depending on current machine:
 
    Joe (Cloud): "jdbc:ucanaccess://C://Users//Joe//OneDrive - The Pennsylvania State University//Database//LMSDB.accdb"
    Justin: (Cloud): "jdbc:ucanaccess://C://Users//jmont//OneDrive - The Pennsylvania State University//Database//LMSDB.accdb"
    Ethan:

As of current, TestHarness application flow for these profiles are:

	All Users: Upon successful login, LMS homepage is displayed with navigation bar at the top of the page.
          To view this deliverable's two use cases (view courses and page types within course/ creating new assignments), 
          follow the listed sequence:
          
          Start at homepage -> Click "Courses" within the navigation bar -> Select a course from the list provided ->
          Click "View Course" from the option menu on the left -> Select a page type from the three buttons provided ->
          For first use case click "View Announcement/Lesson/Assignment" from the option menu on the left. For second 
          use case, click add assignment and follow the path to create various types of new assignments.

    ***Student and TA will only have the "View Course" button on the option menu for courses (will be implemented for 
       various page types in final deliverable). Admins and Instructors will have additional "Add", "Edit", and "Delete"
       buttons (Edit and Delete not yet fully functional): Admins for both Course and Page, Instructors will only have
       these functions for various Page types.
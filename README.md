# IST412-Project
Test profiles have the following login info for various user functionalities:
  
	***Usernames are not case sensitive
	***Passwords are case sensitive
      
      Student:
          Username: student
          Password: Student123
      TA:
          Username: ta
          Password: TA123
      Instructor:
          Username: instructor
          Password: Instructor123
      Admin:
          Username: admin
          Password: Admin123

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
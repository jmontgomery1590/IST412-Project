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
          To view this deliverable's use case (view courses and pages within a course), follow the listed sequence:
          
          Start at homepage -> Click "Courses" within the navigation bar -> Select a course from the list provided ->
          Click "View Course" from the option menu on the left -> Select a page from the list provided ->
          Click "View Page" from the option menu on the left.

    ***Student and TA will only have the "View Course" and "View Page" buttons on the option menu. Admins and 
       Instructors will have additional "Add", "Edit", and "Delete" buttons: Admins for both Course and Page, 
       Instructors only Page. 
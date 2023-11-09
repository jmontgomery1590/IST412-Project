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

*For my UI design pattern, I chose the Navigation Panel. This is persistent throughout our entire system Interface, 
with it implemented in the HomepageUI view class under UserAuthentication.

*For my OO Design pattern, I chose Factory Method. I used my Page model class under CourseManagement, and extended it 
to three subclasses: Announcement, Lesson, and Activity.

As of current, TestHarness application flow for these profiles are:

	All Users: Upon successful login, LMS homepage is displayed with navigation bar at the top of the page.
          To view this deliverable's use case (view courses and pages within a course), follow the listed sequence:
          
          Start at homepage -> Click "Courses" within the navigation bar -> Select a course from the list provided ->
          Click "View Course" from the option menu on the left -> Select a page from the list provided ->
          Click "View Page" from the option menu on the left.

    ***Student and TA will only have the "View Course" and "View Page" buttons on the option menu. Admins and 
       Instructors will have additional "Add", "Edit", and "Delete" buttons (not yet functional): Admins for both
       Course and Page, Instructors only Page. 
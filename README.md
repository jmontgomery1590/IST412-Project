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

    Please note that these are only a few of the logins available. You can see login credentials for other created users
    within the Database itself if you would like.

(IMPORTANT!!!) Database paths depending on current machine:

    We will provide access to our database within PSU OneDrive with our Final Project Implementation. This can be saved
    to your personal PSU OneDrive, and after you add the shortcut to the database to your personal OneDrive in your file
    explorer, our project should run without needing to change the path in our program. 

    Instructions to complete this process: 
 
    Joe (Cloud): "jdbc:ucanaccess://C://Users//Joe//OneDrive - The Pennsylvania State University//Database//LMSDBJoe.accdb"
    Justin: (Cloud): "jdbc:ucanaccess://C://Users//jmont//OneDrive - The Pennsylvania State University//Database//LMSDBjmont.accdb"

    Professor: I have shared the database with you via one drive through Penn State. If your local one drive file path 
                is similar to mine and Justin's, your file path will most likely be similar. Feel free to adjust the 
                database connection to what works best for you in the DatabaseConnection.java class. The way it is set 
                up currently lets both myself and Justin use it without manually repointing everytime.

Use Cases Demonstrated:

    User Authentication/Login

    View Profile for current user

    CRUD for Courses (only when logged in as an Admin account)
    -- Students only have access to View Courses they are enrolled in
    -- Instructors only have access to View Courses they are assigned to
    ---- Deleting Courses disables the course in the database incase course needs to be reactivated thus preserving 
            student enrollment/assignments/etc.

    CRUD for Announcements & Lessons
    -- Students only have view access
    -- Instructors & Admin have full CRUD access
    ---- Deleting Announcements & Lessons fully delete them from the database

    CRUD for Assignments
    -- Students can view Assignments
    -- Admin and Instructors have full CRUD for Assignments
    ---- Deleting Assignments disable in the database just incase reactivation/reversal is needed and students grades 
            can be reaccessed
    ---- Editing an Assignment (reassigning points worth/marking question right or wrong) updates Student's grades 
            & earned points

    Taking/Submitting Assignments
    -- Only Students can take assignments and submit for a grade
    ---- Assignments are autograded by the application
    -- Teachers and Admin can view enrolled Students submitted assignments

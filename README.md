# IST412-Project
I implemented my UI Pattern after the "Getting Input"
    
    I designed the TestHarness to start right at the courseworkMgmt Interface
    When you click on "Add Assignment", you will see that it opens up a gui with
    options for user entry.

    Click "Add question" will open a new gui that lets the user utilize drop down lists
    and also asks for user input.

    When clicking "Add Answer" it opens up a new gui that has user entry (guided with pre-
    loaded text and radio buttons for selecting whether the answer is right or wrong.

For my OO Design Pattern I'm using Abstract Factory Pattern

	As of now there is not a lot of logic, but the skeleton/structure of the factory is implace.
    The Abstract Factory class is designed to create Abstract Questions and Abstract Answers. Those methods are then 
    pushed to the various types of questions/answers (such as open-ended/multiplechoice).

    In the test harness you can see the creation of the two different types of questions and answers. 
    You will also see in the CMD window after running the testharness how calling the same method in either versions
    prints different messages, meaning we can remove/change the types of questions/answers without changing the factory itself.

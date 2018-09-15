# SHPE-Sign-In
Program to be used as a "sign in" sheet for SHPE events. Student data is exported as a CSV file.

Included is the java code that was written for the executable jar that serves as a "sign in" sheet.

The "sign in" sheet executable jar runs as:
  - 1.  Bring up a window that prompts the user to enter any questions that they may want to ask
        their students. Up to three students can be asked, where the reponses to the questions must
        either be of form: yes/no or check box.
  - 2.  The user inputs their questions and selects the type of response that they want and then 
        hits save. If a user enters a question wthout selecting a reponse type then the default is
        yes/no.
  - 3.  A file chooser window appears and the user chooses where to save their CSV data file. If the
        file already exists then the file chooser requests for a new file name. If the file chooser
        window is closed or canceled then the program does not until advance until a file is saved.
        When an accepted file name is used then a csv file is created and closed. The CSV file is 
        created with the following header: "First Name, Last Name, N#, Email, Question 1(if created), 
        Question 2(if created), Question 3(if created), Date".
  - 4.  The program advances once the file is saved and text fields and any asked questions appear for
        a student to fill in. Students can then save their response and the CSV file will then open 
        save the responses and then close after each student hits save.

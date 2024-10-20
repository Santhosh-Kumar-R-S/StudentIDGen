# StudentIDGen
<h1>Summary of the Project</h1>
Here I uploaded Our team project resource. Our Team project name is StudentIDGen. This is our College 2nd Year 3rd Sem Java Mini Project.
     This Project ompletely based on the awt and Swing packeges of the Java. 
     # Student ID Card Generator - Java Swing Application

The provided code is a Java Swing application that serves as a **Student ID Card Generator**. It allows users to input student details, upload a picture, generate a preview of the ID card, and download the card as a PNG image. The application also connects to a MySQL database to store the student's information.

## Key Features

### 1. Graphical User Interface (GUI)
- The application uses Java's Swing framework to create the user interface (UI).
- Several input fields such as name, contact number, branch, USN, address, parent's name, Gmail, blood group, and joining year allow the user to enter student information.
- A file chooser is used to upload the student's picture.

### 2. Database Integration
- It establishes a connection with a MySQL database using JDBC, where it saves student information. This data includes personal details and the image file path of the uploaded picture.

### 3. ID Card Generation
- Once the information is saved to the database, the application generates an ID card using Java's Graphics class to draw text and the uploaded image onto a `BufferedImage`.
- It displays the student's details (name, USN, branch, contact info, etc.) on the ID card, and the student image is placed at the top.

### 4. Preview and Download
- Users can preview the generated ID card in a new window.
- They can also download the ID card as a PNG file using the `ImageIO.write` method.

### 5. Editing and Revising Details
- After generating the ID card, the user can choose to edit the details, allowing them to modify the information before finalizing the ID card.

## Structure

- **UI Components**: The `JFrame` is the main window, and components like `JLabel`, `JTextField`, `JButton`, and `JFileChooser` handle user interactions and input.
- **Database Handling**: The `Connection` object is used to connect to the MySQL database, and `PreparedStatement` is used to insert student details into the database.
- **Image Handling**: Student images are uploaded, previewed, and drawn onto the ID card. The ID card itself is generated as a `BufferedImage`.

## Functional Flow

1. The user inputs student details and uploads an image.
2. The data is saved to the database when "Generate ID Card" is clicked.
3. The ID card is created using the provided information and student picture.
4. Users can preview the generated ID card.
5. The ID card can be downloaded as a PNG file for offline use.

---


# Project Structure
The Project has the follwing structure: 
<pre> <code>Python Mini Project/
     |
     |
     |----- StudentIDCardDB.db
     |        
     |
     |----- StudentIDCardGenerator.java
</code></pre>

# Screenshots 
Below screenshots shows you the output of this project:<br>
<h3>Welcome Page(<i>home.html</i>)</h3>
     After running the <b>app.py</b> file it gives an link in terminal just copy that link or hit that link with the cntrl button then it go to the your systems default browser and it shows this page. <br><br>
     
![Screenshot 2024-10-20 190706](https://github.com/user-attachments/assets/0440723e-9c97-4e7e-9ccf-9d7ed4348835)<br>


<h3>After filling the required data</h3>
   After filling the required data like Name, USN, Mobile Number, Parents Name, Place and attaching photo to genarate ID Card 
     
![Screenshot 2024-10-20 190848](https://github.com/user-attachments/assets/f1f212ee-8adc-4ffa-86b8-df316496790c)<br>


<h3>Problems Page(<i>problems.html</i>)</h3><br>
     After hitting the <b>Start Quiz</b> in <i>quiz.html</i> browser loads the 10 random probelms here I attached only one question like wise 10 questions will be arises.<br><br>

![problems.html](https://github.com/Santhosh-Kumar-R-S/Python-Mini-Project/assets/161617061/1807967f-f53b-468d-907c-ca86edbd5e5a)<br>

<h3>Results![Screenshot 2024-10-20 190706](https://github.com/user-attachments/assets/4fcdb43d-47be-4c64-9342-73ac2fe22d92)
 Sheet(<i>result.html</i>) and Result got in Mail</h3>
     In this page it shows the results of the user. In this result sheet it shows how many questions has submitted correct answer with grade, how many of questions skipped, how much time taken to solve the questions and result sent mail address.<br><br>
     
![results](https://github.com/Santhosh-Kumar-R-S/Python-Mini-Project/assets/161617061/7c4c15ff-2748-4c3e-b463-ee8f42e4076e) <br>

<b>This picture from user mail:<br></b><br>
![mail](https://github.com/Santhosh-Kumar-R-S/Python-Mini-Project/assets/161617061/82f1b955-2bde-445f-a58b-ad5c10722ebd)<br>

<h3>Database(<i>quiz_data.db</i>) and CSV File(quiz_data.csv</i>)</h3><br>
     In this picture You clearly know that what are the participants are particiated, what is the score of that participants, mail etc. By the csv file we can sort the participants based on thiere score and timings to announce the ranking.
     <br><br>
     <i><b>quiz_data.db: <br></b><br></i>

![data_base_stored](https://github.com/Santhosh-Kumar-R-S/Python-Mini-Project/assets/161617061/55c14592-6735-4daf-b8cc-a92f703c0fbe) <br><br>
<i><b>CSV File:</b><br></i>
<br>
![CSV results](https://github.com/Santhosh-Kumar-R-S/Python-Mini-Project/assets/161617061/ae601c08-b4e8-4673-8a3a-edc6faf9dc52)<br>




     

     

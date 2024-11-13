# StudentIDGen
<h1>Summary of the Project</h1>
Here I uploaded Our team project resource. Our Team project name is StudentIDGen. This is our College 2nd Year 3rd Sem Java Mini Project.
     This Project ompletely based on the awt and Swing packeges of the Java. 


## Student ID Card Generator - Java Swing Application

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
     
![Screenshot 2024-10-20 190706](https://github.com/user-attachments/assets/0440723e-9c97-4e7e-9ccf-9d7ed4348835)<br><br>


<h3>After filling the required data</h3>
   After filling the required data like Name, USN, Mobile Number, Parents Name, Place and attaching photo to genarate ID Card.<br><br>
     
![Screenshot 2024-10-20 190848](https://github.com/user-attachments/assets/f1f212ee-8adc-4ffa-86b8-df316496790c)<br><br>


<h3>After hitting the Generate ID Card button</h3><br>
     After hitting the <b>Generate ID Card</b> in <i>USer Inerface</i> it shows an pop-up notification(this time only the details of the student will be processed to store in database <i> StudentIDCardDB.db</i>) that is <b><i>Student details saved to database</i></b><br> and after hitting the <b>OK</b> button then it will redirect to another tab(which will shows edit information tab) those pages are attached in below.<br><br>
<h4>Pop-up Notification</h4><br>

![Screenshot 2024-10-20 190903](https://github.com/user-attachments/assets/bb6f75fd-dc81-43a0-a7f6-d1a41f8ccf85)<br><br>

<h4>Editable Tab</h4><br>

![Screenshot 2024-10-20 190922](https://github.com/user-attachments/assets/8dcf6e6d-b447-4941-aed7-3fc5893e8a1c)<br><br>



<h3>Preview of ID Card</h3>
In the user interface redirect to the editable tab which will help to edit the details. In this page it contaion Preview ID Card, Download ID Card, Edit Details and Genarate ID Card with previous text feilds and buttons. If the user click the <b>Edit Details</b> button it will redirect to the old page. If the user click the <b>Preview ID Card</b> then it will show the Previw of ID Card. This sample is attached here.<br><br>

![Screenshot 2024-10-20 194242](https://github.com/user-attachments/assets/64e8a8f2-d652-4536-8ee9-7c7772ab8385)<br>

<h2>Database <i>StudentIDCardDB.db</i></h2>
<h3>Data Stored in the Database</h3>
After hitting the Generate ID Card that will shows a pop-up notification that picture attached to earlier that the data will be processed to store in database processed in background. that data retrived from the data base using this command:<br><br>
MySQL Command:<br>

```bash
     USE StudentIDCardDB
     SELECT * FROM student
```

![Screenshot 2024-10-20 191307](https://github.com/user-attachments/assets/80e44f02-c1f6-46da-ac16-cba1fa3d32fa)

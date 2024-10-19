import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import javax.imageio.ImageIO;

public class StudentIDCardGenerator {

    private JFrame frame;
    private JTextField nameField, contactField, branchField, usnField, addressField, parentNameField, gmailField, bloodGroupField, joiningYearField;
    private JLabel pictureLabel;
    private BufferedImage idCardImage; // To hold the generated ID card as an image
    private Image studentImage; // To hold the uploaded student image
    private JButton previewButton, editButton, generateButton, downloadButton;
    private String imagePath; // Path of the uploaded image file

    private Connection conn; // Database connection

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StudentIDCardGenerator window = new StudentIDCardGenerator();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public StudentIDCardGenerator() {
        initializeDatabaseConnection(); // Initialize DB connection
        initializeUI();
    }

    // Step 1: Initialize the database connection
    private void initializeDatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL driver
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentIDCardDB", "root", "yourpassword"); // Replace with your DB credentials
            System.out.println("Database connected successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Failed to connect to the database.");
        }
    }

    // Step 2: Initialize the UI
    private void initializeUI() {
        frame = new JFrame("Student ID Card Generator");
        frame.setBounds(100, 100, 600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 30, 100, 25);
        frame.getContentPane().add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 30, 200, 25);
        frame.getContentPane().add(nameField);

        JLabel contactLabel = new JLabel("Contact Number:");
        contactLabel.setBounds(30, 70, 120, 25);
        frame.getContentPane().add(contactLabel);

        contactField = new JTextField();
        contactField.setBounds(150, 70, 200, 25);
        frame.getContentPane().add(contactField);

        JLabel branchLabel = new JLabel("Branch:");
        branchLabel.setBounds(30, 110, 100, 25);
        frame.getContentPane().add(branchLabel);

        branchField = new JTextField();
        branchField.setBounds(150, 110, 200, 25);
        frame.getContentPane().add(branchField);

        JLabel usnLabel = new JLabel("USN:");
        usnLabel.setBounds(30, 150, 100, 25);
        frame.getContentPane().add(usnLabel);

        usnField = new JTextField();
        usnField.setBounds(150, 150, 200, 25);
        frame.getContentPane().add(usnField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(30, 190, 100, 25);
        frame.getContentPane().add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(150, 190, 200, 25);
        frame.getContentPane().add(addressField);

        JLabel parentNameLabel = new JLabel("Parent Name:");
        parentNameLabel.setBounds(30, 230, 120, 25);
        frame.getContentPane().add(parentNameLabel);

        parentNameField = new JTextField();
        parentNameField.setBounds(150, 230, 200, 25);
        frame.getContentPane().add(parentNameField);

        JLabel gmailLabel = new JLabel("Gmail:");
        gmailLabel.setBounds(30, 270, 100, 25);
        frame.getContentPane().add(gmailLabel);

        gmailField = new JTextField();
        gmailField.setBounds(150, 270, 200, 25);
        frame.getContentPane().add(gmailField);

        JLabel bloodGroupLabel = new JLabel("Blood Group:");
        bloodGroupLabel.setBounds(30, 310, 100, 25);
        frame.getContentPane().add(bloodGroupLabel);

        bloodGroupField = new JTextField();
        bloodGroupField.setBounds(150, 310, 200, 25);
        frame.getContentPane().add(bloodGroupField);

        JLabel joiningYearLabel = new JLabel("Joining Year:");
        joiningYearLabel.setBounds(30, 350, 100, 25);
        frame.getContentPane().add(joiningYearLabel);

        joiningYearField = new JTextField();
        joiningYearField.setBounds(150, 350, 200, 25);
        frame.getContentPane().add(joiningYearField);

        JLabel pictureTextLabel = new JLabel("Picture:");
        pictureTextLabel.setBounds(30, 390, 100, 25);
        frame.getContentPane().add(pictureTextLabel);

        JButton uploadButton = new JButton("Upload Picture");
        uploadButton.setBounds(150, 390, 200, 25);
        frame.getContentPane().add(uploadButton);

        pictureLabel = new JLabel("");
        pictureLabel.setBounds(150, 420, 100, 100);
        frame.getContentPane().add(pictureLabel);

        generateButton = new JButton("Generate ID Card");
        generateButton.setBounds(150, 530, 150, 30);
        frame.getContentPane().add(generateButton);

        previewButton = new JButton("Preview ID Card");
        previewButton.setBounds(310, 530, 150, 30);
        frame.getContentPane().add(previewButton);
        previewButton.setVisible(false); // Initially hidden

        editButton = new JButton("Edit Details");
        editButton.setBounds(310, 580, 150, 30);
        frame.getContentPane().add(editButton);
        editButton.setVisible(false); // Initially hidden

        downloadButton = new JButton("Download ID Card");
        downloadButton.setBounds(150, 580, 150, 30);
        frame.getContentPane().add(downloadButton);
        downloadButton.setVisible(false); // Initially hidden

        uploadButton.addActionListener(e -> uploadPicture());
        generateButton.addActionListener(e -> saveStudentDetailsToDatabase());
        previewButton.addActionListener(e -> previewIDCard());
        editButton.addActionListener(e -> editDetails());
        downloadButton.addActionListener(e -> downloadIDCard());
    }

    // Step 3: Handle Picture Upload
    private void uploadPicture() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            imagePath = fileChooser.getSelectedFile().getAbsolutePath();
            ImageIcon imageIcon = new ImageIcon(imagePath);
            studentImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            pictureLabel.setIcon(new ImageIcon(studentImage)); // Set the image to JLabel
        }
    }

    // Step 4: Save Student Details to Database
    private void saveStudentDetailsToDatabase() {
        String name = nameField.getText();
        String contact = contactField.getText();
        String branch = branchField.getText();
        String usn = usnField.getText();
        String address = addressField.getText();
        String parentName = parentNameField.getText();
        String gmail = gmailField.getText();
        String bloodGroup = bloodGroupField.getText();
        String joiningYear = joiningYearField.getText();

        try {
            // SQL Query to insert student details into the database
            String query = "INSERT INTO students (name, contact, branch, usn, address, parent_name, gmail, blood_group, joining_year, photo_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, contact);
            pst.setString(3, branch);
            pst.setString(4, usn);
            pst.setString(5, address);
            pst.setString(6, parentName);
            pst.setString(7, gmail);
            pst.setString(8, bloodGroup);
            pst.setString(9, joiningYear);
            pst.setString(10, imagePath); // Store the image path
            pst.executeUpdate();

            JOptionPane.showMessageDialog(frame, "Student details saved to database!");
            generateIDCard(); // Now generate the ID card

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error saving details to database.");
        }
    }

    // Step 5: Generate ID Card
    private void generateIDCard() {
        // Set up an image where we will draw the ID card content
        idCardImage = new BufferedImage(400, 300, BufferedImage.TYPE_INT_RGB);
        Graphics g = idCardImage.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 400, 300); // Fill background with white

        g.setColor(Color.BLACK);
        g.drawRect(10, 10, 380, 280); // Border for ID card

        // Draw student image at top center
        if (studentImage != null) {
            g.drawImage(studentImage, 150, 20, 100, 100, null);
        }

        // Draw student details
        g.drawString("Name: " + nameField.getText(), 30, 150);
        g.drawString("USN: " + usnField.getText(), 30, 170);
        g.drawString("Branch: " + branchField.getText(), 30, 190);
        g.drawString("Contact: " + contactField.getText(), 30, 210);
        g.drawString("Parent: " + parentNameField.getText(), 30, 230);
        g.drawString("Gmail: " + gmailField.getText(), 30, 250);
        g.drawString("Blood Group: " + bloodGroupField.getText(), 30, 270);
        g.drawString("Joining Year: " + joiningYearField.getText(), 30, 290);

        previewButton.setVisible(true); // Show the Preview button
        downloadButton.setVisible(true); // Show the Download button
        editButton.setVisible(true); // Show the Edit button

        g.dispose(); // Clean up the graphics object
    }

    // Step 6: Preview the Generated ID Card
    private void previewIDCard() {
        // Create a new JFrame to show the preview
        JFrame previewFrame = new JFrame("ID Card Preview");
        previewFrame.setBounds(100, 100, 420, 350);
        JLabel previewLabel = new JLabel(new ImageIcon(idCardImage));
        previewFrame.getContentPane().add(previewLabel);
        previewFrame.setVisible(true);
    }

    // Step 7: Allow Editing of Details
    private void editDetails() {
        nameField.setEditable(true);
        contactField.setEditable(true);
        branchField.setEditable(true);
        usnField.setEditable(true);
        addressField.setEditable(true);
        parentNameField.setEditable(true);
        gmailField.setEditable(true);
        bloodGroupField.setEditable(true);
        joiningYearField.setEditable(true);
        JOptionPane.showMessageDialog(frame, "You can now edit the details.");
    }

    // Step 8: Download the ID Card as PNG
    private void downloadIDCard() {
        try {
            File outputfile = new File("StudentIDCard.png");
            ImageIO.write(idCardImage, "png", outputfile);
            JOptionPane.showMessageDialog(frame, "ID Card downloaded as StudentIDCard.png!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error downloading ID card.");
        }
    }
}

Santhosh Kumar R import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import javax.imageio.ImageIO;

public class StudentIDCardGenerator {

    private JFrame frame;
    private JTextField nameField, contactField, branchField, usnField, addressField, parentNameField, gmailField, bloodGroupField, joiningYearField;
    private JLabel pictureLabel;
    private BufferedImage idCardImage; // To hold the generated ID card as an image
    private Image studentImage; // To hold the uploaded student image
    private JButton previewButton, editButton, generateButton, downloadButton;
    private String imagePath; // Path of the uploaded image file

    private Connection conn; // Database connection

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StudentIDCardGenerator window = new StudentIDCardGenerator();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public StudentIDCardGenerator() {
        initializeDatabaseConnection(); // Initialize DB connection
        initializeUI();
    }

    // Step 1: Initialize the database connection
    private void initializeDatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL driver
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentIDCardDB", "root", "yourpassword"); // Replace with your DB credentials
            System.out.println("Database connected successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Failed to connect to the database.");
        }
    }

    // Step 2: Initialize the UI
    private void initializeUI() {
        frame = new JFrame("Student ID Card Generator");
        frame.setBounds(100, 100, 600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 30, 100, 25);
        frame.getContentPane().add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 30, 200, 25);
        frame.getContentPane().add(nameField);

        JLabel contactLabel = new JLabel("Contact Number:");
        contactLabel.setBounds(30, 70, 120, 25);
        frame.getContentPane().add(contactLabel);

        contactField = new JTextField();
        contactField.setBounds(150, 70, 200, 25);
        frame.getContentPane().add(contactField);

        JLabel branchLabel = new JLabel("Branch:");
        branchLabel.setBounds(30, 110, 100, 25);
        frame.getContentPane().add(branchLabel);

        branchField = new JTextField();
        branchField.setBounds(150, 110, 200, 25);
        frame.getContentPane().add(branchField);

        JLabel usnLabel = new JLabel("USN:");
        usnLabel.setBounds(30, 150, 100, 25);
        frame.getContentPane().add(usnLabel);

        usnField = new JTextField();
        usnField.setBounds(150, 150, 200, 25);
        frame.getContentPane().add(usnField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(30, 190, 100, 25);
        frame.getContentPane().add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(150, 190, 200, 25);
        frame.getContentPane().add(addressField);

        JLabel parentNameLabel = new JLabel("Parent Name:");
        parentNameLabel.setBounds(30, 230, 120, 25);
        frame.getContentPane().add(parentNameLabel);

        parentNameField = new JTextField();
        parentNameField.setBounds(150, 230, 200, 25);
        frame.getContentPane().add(parentNameField);

        JLabel gmailLabel = new JLabel("Gmail:");
        gmailLabel.setBounds(30, 270, 100, 25);
        frame.getContentPane().add(gmailLabel);

        gmailField = new JTextField();
        gmailField.setBounds(150, 270, 200, 25);
        frame.getContentPane().add(gmailField);

        JLabel bloodGroupLabel = new JLabel("Blood Group:");
        bloodGroupLabel.setBounds(30, 310, 100, 25);
        frame.getContentPane().add(bloodGroupLabel);

        bloodGroupField = new JTextField();
        bloodGroupField.setBounds(150, 310, 200, 25);
        frame.getContentPane().add(bloodGroupField);

        JLabel joiningYearLabel = new JLabel("Joining Year:");
        joiningYearLabel.setBounds(30, 350, 100, 25);
        frame.getContentPane().add(joiningYearLabel);

        joiningYearField = new JTextField();
        joiningYearField.setBounds(150, 350, 200, 25);
        frame.getContentPane().add(joiningYearField);

        JLabel pictureTextLabel = new JLabel("Picture:");
        pictureTextLabel.setBounds(30, 390, 100, 25);
        frame.getContentPane().add(pictureTextLabel);

        JButton uploadButton = new JButton("Upload Picture");
        uploadButton.setBounds(150, 390, 200, 25);
        frame.getContentPane().add(uploadButton);

        pictureLabel = new JLabel("");
        pictureLabel.setBounds(150, 420, 100, 100);
        frame.getContentPane().add(pictureLabel);

        generateButton = new JButton("Generate ID Card");
        generateButton.setBounds(150, 530, 150, 30);
        frame.getContentPane().add(generateButton);

        previewButton = new JButton("Preview ID Card");
        previewButton.setBounds(310, 530, 150, 30);
        frame.getContentPane().add(previewButton);
        previewButton.setVisible(false); // Initially hidden

        editButton = new JButton("Edit Details");
        editButton.setBounds(310, 580, 150, 30);
        frame.getContentPane().add(editButton);
        editButton.setVisible(false); // Initially hidden

        downloadButton = new JButton("Download ID Card");
        downloadButton.setBounds(150, 580, 150, 30);
        frame.getContentPane().add(downloadButton);
        downloadButton.setVisible(false); // Initially hidden

        uploadButton.addActionListener(e -> uploadPicture());
        generateButton.addActionListener(e -> saveStudentDetailsToDatabase());
        previewButton.addActionListener(e -> previewIDCard());
        editButton.addActionListener(e -> editDetails());
        downloadButton.addActionListener(e -> downloadIDCard());
    }

    // Step 3: Handle Picture Upload
    private void uploadPicture() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            imagePath = fileChooser.getSelectedFile().getAbsolutePath();
            ImageIcon imageIcon = new ImageIcon(imagePath);
            studentImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            pictureLabel.setIcon(new ImageIcon(studentImage)); // Set the image to JLabel
        }
    }

    // Step 4: Save Student Details to Database
    private void saveStudentDetailsToDatabase() {
        String name = nameField.getText();
        String contact = contactField.getText();
        String branch = branchField.getText();
        String usn = usnField.getText();
        String address = addressField.getText();
        String parentName = parentNameField.getText();
        String gmail = gmailField.getText();
        String bloodGroup = bloodGroupField.getText();
        String joiningYear = joiningYearField.getText();

        try {
            // SQL Query to insert student details into the database
            String query = "INSERT INTO students (name, contact, branch, usn, address, parent_name, gmail, blood_group, joining_year, photo_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, contact);
            pst.setString(3, branch);
            pst.setString(4, usn);
            pst.setString(5, address);
            pst.setString(6, parentName);
            pst.setString(7, gmail);
            pst.setString(8, bloodGroup);
            pst.setString(9, joiningYear);
            pst.setString(10, imagePath); // Store the image path
            pst.executeUpdate();

            JOptionPane.showMessageDialog(frame, "Student details saved to database!");
            generateIDCard(); // Now generate the ID card

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error saving details to database.");
        }
    }

    // Step 5: Generate ID Card
    private void generateIDCard() {
        // Set up an image where we will draw the ID card content
        idCardImage = new BufferedImage(400, 300, BufferedImage.TYPE_INT_RGB);
        Graphics g = idCardImage.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 400, 300); // Fill background with white

        g.setColor(Color.BLACK);
        g.drawRect(10, 10, 380, 280); // Border for ID card

        // Draw student image at top center
        if (studentImage != null) {
            g.drawImage(studentImage, 150, 20, 100, 100, null);
        }

        // Draw student details
        g.drawString("Name: " + nameField.getText(), 30, 150);
        g.drawString("USN: " + usnField.getText(), 30, 170);
        g.drawString("Branch: " + branchField.getText(), 30, 190);
        g.drawString("Contact: " + contactField.getText(), 30, 210);
        g.drawString("Parent: " + parentNameField.getText(), 30, 230);
        g.drawString("Gmail: " + gmailField.getText(), 30, 250);
        g.drawString("Blood Group: " + bloodGroupField.getText(), 30, 270);
        g.drawString("Joining Year: " + joiningYearField.getText(), 30, 290);

        previewButton.setVisible(true); // Show the Preview button
        downloadButton.setVisible(true); // Show the Download button
        editButton.setVisible(true); // Show the Edit button

        g.dispose(); // Clean up the graphics object
    }

    // Step 6: Preview the Generated ID Card
    private void previewIDCard() {
        // Create a new JFrame to show the preview
        JFrame previewFrame = new JFrame("ID Card Preview");
        previewFrame.setBounds(100, 100, 420, 350);
        JLabel previewLabel = new JLabel(new ImageIcon(idCardImage));
        previewFrame.getContentPane().add(previewLabel);
        previewFrame.setVisible(true);
    }

    // Step 7: Allow Editing of Details
    private void editDetails() {
        nameField.setEditable(true);
        contactField.setEditable(true);
        branchField.setEditable(true);
        usnField.setEditable(true);
        addressField.setEditable(true);
        parentNameField.setEditable(true);
        gmailField.setEditable(true);
        bloodGroupField.setEditable(true);
        joiningYearField.setEditable(true);
        JOptionPane.showMessageDialog(frame, "You can now edit the details.");
    }

    // Step 8: Download the ID Card as PNG
    private void downloadIDCard() {
        try {
            File outputfile = new File("StudentIDCard.png");
            ImageIO.write(idCardImage, "png", outputfile);
            JOptionPane.showMessageDialog(frame, "ID Card downloaded as StudentIDCard.png!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error downloading ID card.");
        }
    }
}

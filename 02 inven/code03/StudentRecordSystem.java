import java.awt.*;
import java.awt.event.*;

public class StudentRecordSystem {
    private Frame frame;
    private Label nameLabel, rollNoLabel, infoLabel;
    private TextField nameTextField, rollNoTextField;
    private Button addButton, displayButton;

    public StudentRecordSystem() {
        frame = new Frame("Student Record System");
        nameLabel = new Label("Name:");
        rollNoLabel = new Label("Roll No:");
        infoLabel = new Label();

        nameTextField = new TextField();
        rollNoTextField = new TextField();

        addButton = new Button("Add Student");
        displayButton = new Button("Display All");

        // Add action listeners to buttons
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayAllStudents();
            }
        });

        // Set layout for the frame
        frame.setLayout(new GridLayout(4, 2));

        // Add components to the frame
        frame.add(nameLabel);
        frame.add(nameTextField);
        frame.add(rollNoLabel);
        frame.add(rollNoTextField);
        frame.add(addButton);
        frame.add(displayButton);
        frame.add(infoLabel);

        // Set frame properties
        frame.setSize(400, 200);
        frame.setVisible(true);

        // Close the frame when the user closes it
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new StudentRecordSystem();
    }

    // Function to add a student and display their information
    private void addStudent() {
        String name = nameTextField.getText();
        String rollNo = rollNoTextField.getText();
        String studentInfo = "Name: " + name + ", Roll No: " + rollNo;

        // Display student information in the infoLabel
        infoLabel.setText("Student added: " + studentInfo);

        // You can further process and store the student information as needed.
    }

    // Function to display all students (placeholder)
    private void displayAllStudents() {
        // Placeholder for displaying all students.
        infoLabel.setText("Displaying all students...");
    }
}

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// ==================== Student Class ====================
// Represents a single student with name and grade
class Student {
    private String name;   // Student name
    private double grade;  // Student grade

    // Constructor to initialize student object
    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for grade
    public double getGrade() {
        return grade;
    }

    // Return formatted string for student info
    @Override
    public String toString() {
        return String.format("%s - %.2f", name, grade);
    }
}

// ==================== StudentManager Class ====================
// Handles all operations like adding, deleting, searching, etc.
class StudentManager {
    private ArrayList<Student> students = new ArrayList<>(); // List of students

    // Add a student to the list
    public void addStudent(String name, double grade) {
        if (grade < 0 || grade > 100) { // Validate grade
            System.out.println("Grade must be between 0 and 100.\n");
            return;
        }
        students.add(new Student(name, grade)); // Add new student object
        System.out.println("Student added successfully!\n");
    }

    // Display all students
    public void displayStudents() {
        if (students.isEmpty()) { // Check if list is empty
            System.out.println("No students available.\n");
            return;
        }
        System.out.println("=== Student List ===");
        for (int i = 0; i < students.size(); i++) { // Loop through students
            System.out.printf("%d. %s%n", i + 1, students.get(i));
        }
        System.out.println();
    }

    // Search for a student by name
    public void searchStudent(String name) {
        boolean found = false;
        for (Student s : students) { // Loop through all students
            if (s.getName().equalsIgnoreCase(name)) { // Case-insensitive match
                System.out.println("Found: " + s + "\n");
                found = true;
            }
        }
        if (!found) System.out.println("Student not found.\n");
    }

    // Delete a student by name
    public void deleteStudent(String name) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equalsIgnoreCase(name)) {
                students.remove(i); // Remove the student
                System.out.println("Student deleted successfully!\n");
                return;
            }
        }
        System.out.println("Student not found.\n");
    }

    // Show the student with the highest grade
    public void showHighestGrade() {
        if (students.isEmpty()) {
            System.out.println("No students available.\n");
            return;
        }
        Student top = students.get(0); // Assume first student is highest
        for (Student s : students) {
            if (s.getGrade() > top.getGrade()) top = s;
        }
        System.out.println("Highest Grade: " + top + "\n");
    }

    // Show the student with the lowest grade
    public void showLowestGrade() {
        if (students.isEmpty()) {
            System.out.println("No students available.\n");
            return;
        }
        Student low = students.get(0); // Assume first student is lowest
        for (Student s : students) {
            if (s.getGrade() < low.getGrade()) low = s;
        }
        System.out.println("Lowest Grade: " + low + "\n");
    }

    // Calculate and display average grade
    public void calculateAverage() {
        if (students.isEmpty()) { // Check if there are any grades
            System.out.println("No students to calculate average.\n");
            return;
        }
        double total = 0;
        for (Student s : students) total += s.getGrade(); // Sum grades
        double avg = total / students.size();
        System.out.printf("Average Grade: %.2f%n%n", avg);
    }
}

// ==================== Main Application ====================
// Provides a menu for the user to interact with the system
public class StudentApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // Scanner for user input
        StudentManager manager = new StudentManager(); // Manage student data

        while (true) {
            // Display main menu
            System.out.println("=== Student Management System ===");
            System.out.println("1- Add Student");
            System.out.println("2- Show Students");
            System.out.println("3- Search Student");
            System.out.println("4- Delete Student");
            System.out.println("5- Show Highest Grade");
            System.out.println("6- Show Lowest Grade");
            System.out.println("7- Get Average");
            System.out.println("8- Exit");
            System.out.print("Your Choice: ");

            int choice;
            try {
                choice = in.nextInt(); // Read user choice
                in.nextLine(); // Clear buffer
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number.\n");
                in.nextLine(); // Clear invalid input
                continue; // Restart loop
            }

            // Perform action based on user choice
            switch (choice) {
                case 1: // Add student
                    System.out.print("Enter student name: ");
                    String name = in.nextLine();
                    System.out.print("Enter grade: ");
                    try {
                        double grade = in.nextDouble();
                        in.nextLine();
                        manager.addStudent(name, grade);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid grade. Must be a number.\n");
                        in.nextLine();
                    }
                    break;

                case 2: // Show all students
                    manager.displayStudents();
                    break;

                case 3: // Search student
                    System.out.print("Enter student name to search: ");
                    String searchName = in.nextLine();
                    manager.searchStudent(searchName);
                    break;

                case 4: // Delete student
                    System.out.print("Enter student name to delete: ");
                    String deleteName = in.nextLine();
                    manager.deleteStudent(deleteName);
                    break;

                case 5: // Highest grade
                    manager.showHighestGrade();
                    break;

                case 6: // Lowest grade
                    manager.showLowestGrade();
                    break;

                case 7: // Average
                    manager.calculateAverage();
                    break;

                case 8: // Exit
                    System.out.println("Goodbye!");
                    in.close();
                    return;

                default: // Invalid choice
                    System.out.println("Invalid choice, try again.\n");
            }
        }
    }
}

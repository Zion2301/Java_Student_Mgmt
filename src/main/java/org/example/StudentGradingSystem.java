package org.example;
import java.util.Scanner; // Import Scanner for input

public class StudentGradingSystem {

    static Scanner sc = new Scanner(System.in); // Create a single Scanner instance for the whole app

    // Constants for max students and subjects allowed
    static final int MAX_STUDENTS = 200;
    static final int MAX_SUBJECTS = 10;

    // Arrays to store data
    static String[] studentNames = new String[MAX_STUDENTS]; // Student names
    static String[][] subjectNames = new String[MAX_STUDENTS][MAX_SUBJECTS]; // Subject names for each student
    static double[][] scores = new double[MAX_STUDENTS][MAX_SUBJECTS]; // Scores for each subject
    static int[] subjectCounts = new int[MAX_STUDENTS]; // To keep track of how many subjects each student has
    static int studentCount = 0; // Keeps track of total number of students added

    public static void main(String[] args) {
        while (true) { // Infinite loop to keep showing menu until user exits
            printMenu(); // Show menu

            int choice; // User's menu selection

            try {
                choice = Integer.parseInt(sc.nextLine()); // Try converting input to integer
            } catch (Exception e) {
                System.out.println("❌ Invalid input. Try a number.\n"); // If invalid input, prompt again
                continue;
            }

            // Perform action based on user's choice
            switch (choice) {
                case 1 -> addStudent(); // Option 1: Add student and scores
                case 2 -> showReport(); // Option 2: Show report for all students
                case 3 -> {
                    System.out.println("\n👋 Exiting system. Goodbye!"); // Option 3: Exit
                    return;
                }
                default -> System.out.println("❌ Invalid choice. Try again.\n"); // Handle wrong choice
            }
        }
    }

    // Method to print the main menu
    static void printMenu() {
        System.out.println("\n🎓==============================🎓");
        System.out.println("     STUDENT GRADING SYSTEM");
        System.out.println("🎓==============================🎓");
        System.out.println("1️⃣  Add Student and Scores");
        System.out.println("2️⃣  View Student Reports");
        System.out.println("3️⃣  Exit");
        System.out.print("👉 Enter your choice: ");
    }

    // Method to add a student's name, subjects, and scores
    static void addStudent() {
        if (studentCount >= MAX_STUDENTS) { // Check if max student limit is reached
            System.out.println("❗ Max limit reached.");
            return;
        }

        System.out.print("📛 Enter student name: "); // Prompt for student name
        String name = sc.nextLine(); // Read name
        studentNames[studentCount] = name; // Store name

        System.out.print("📚 How many subjects?: "); // Ask for number of subjects
        int subCount;

        try {
            subCount = Integer.parseInt(sc.nextLine()); // Convert to int
        } catch (Exception e) {
            System.out.println("❌ Please enter a number."); // Handle invalid input
            return;
        }

        subjectCounts[studentCount] = subCount; // Store how many subjects this student has

        for (int i = 0; i < subCount; i++) { // Loop to take each subject and score
            System.out.print("  📝 Subject " + (i + 1) + " name: ");
            subjectNames[studentCount][i] = sc.nextLine(); // Read and store subject name

            System.out.print("  🎯 Score for " + subjectNames[studentCount][i] + ": ");
            try {
                scores[studentCount][i] = Double.parseDouble(sc.nextLine()); // Read and store score
            } catch (Exception e) {
                System.out.println("❌ Invalid score. Enter a number.");
                i--; // If input is wrong, repeat this subject again
            }
        }

        System.out.println("✅ Student added successfully!"); // Confirmation
        studentCount++; // Increment student count
    }

    // Method to display all students with their scores and grades
    static void showReport() {
        if (studentCount == 0) { // No students added yet
            System.out.println("😅 No students found.");
            return;
        }

        System.out.println("\n📄=========== STUDENT REPORTS ===========\n");

        for (int i = 0; i < studentCount; i++) { // Loop over each student
            System.out.println("👤 Name: " + studentNames[i]); // Display student name
            double total = 0; // To calculate total scores

            for (int j = 0; j < subjectCounts[i]; j++) { // Loop over subjects
                System.out.printf("   📘 %-12s: %.2f\n", subjectNames[i][j], scores[i][j]); // Show subject and score
                total += scores[i][j]; // Add to total
            }

            double avg = total / subjectCounts[i]; // Calculate average
            String grade = getGrade(avg); // Get grade based on average

            // Display final results
            System.out.printf("   ➕ Total Score : %.2f\n", total);
            System.out.printf("   📈 Average     : %.2f\n", avg);
            System.out.println("   🏆 Grade       : " + grade);
            System.out.println("🔸--------------------------------------\n");
        }
    }

    // Method to determine grade based on average score
    static String getGrade(double avg) {
        if (avg >= 70) return "A"; // 70 and above = A
        else if (avg >= 60) return "B"; // 60–69 = B
        else if (avg >= 50) return "C"; // 50–59 = C
        else if (avg >= 40) return "D"; // 40–49 = D
        else return "F"; // Below 40 = F
    }
}


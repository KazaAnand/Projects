package StuDB;

import java.util.Scanner;

public class StudentApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StudentManagement sm = new StudentManagement();
		int choice;

		do {
			// Menu
			System.out.println("\n=== Student Management Menu ===");
			System.out.println("1. Add Student");
			System.out.println("2. Update Student");
			System.out.println("3. Delete Student");
			System.out.println("4. Add/Update Marks");
			System.out.println("5. Display Student by RollNo");
			System.out.println("6. Display All Students");
			System.out.println("7. Sort Students (RollNo / Name / Percentage)");
			System.out.println("8. Show Topper");
			System.out.println("9. Exit");
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();
			sc.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				sm.addStudent();
				break;
			case 2:
				sm.updateStudent();
				break;
			case 3:
				sm.deleteStudent();
				break;
			case 4:
				sm.addOrUpdateMarks();
				break;
			case 5:
				sm.displayStudentByRollNo();
				break;
			case 6:
				sm.displayAllStudents();
				break;
			case 7:
				System.out.print("Sort by (rollNo/name/percentage): ");
				String criteria = sc.nextLine();
				sm.sortStudentsBy(criteria);
				break;
			case 8:
				sm.getTopper();
				break;
			case 9:
				System.out.println("Exiting program...");
				break;
			default:
				System.out.println("Invalid choice! Try again.");
			}

		} while (choice != 9);

		sc.close();
	}
}

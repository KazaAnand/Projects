//	Manages operations like add, delete, update, sort, search

package StuDB;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StudentManagement {

	// Field
	private Map<String, StudentDetails> studentMap = new HashMap<>();
	Scanner sc = new Scanner(System.in);

	// Add student
	public void addStudent() {
		System.out.print("Enter RollNo: ");
		String rollNo = sc.nextLine();

		if (studentMap.containsKey(rollNo)) {
			System.out.println("Student with Roll No " + rollNo + " already exists!");
			return;
		}

		System.out.print("Enter Name: ");
		String name = sc.nextLine();
		System.out.print("Enter Age: ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter Gender: ");
		String gender = sc.nextLine();
		System.out.print("Enter Course: ");
		String course = sc.nextLine();

		StudentDetails student = new StudentDetails(rollNo, name, age, gender, course, new HashMap<>());
		studentMap.put(rollNo, student);
		System.out.println("Student added successfully!");
	}

	// Update student info
	public void updateStudent() {
		System.out.print("Enter RollNo to update: ");
		String rollNo = sc.nextLine();

		if (!studentMap.containsKey(rollNo)) {
			System.out.println("Student with Roll No " + rollNo + " not found!");
			return;
		}

		StudentDetails student = studentMap.get(rollNo);

		System.out.print("Enter new Name: ");
		student.setName(sc.nextLine());
		System.out.print("Enter new Age: ");
		student.setAge(sc.nextInt());
		sc.nextLine();
		System.out.print("Enter new Gender: ");
		student.setGender(sc.nextLine());
		System.out.print("Enter new Course: ");
		student.setCourse(sc.nextLine());

		System.out.println("Student details updated successfully!");
	}

	// Delete student
	public void deleteStudent() {
		System.out.print("Enter RollNo to delete: ");
		String rollNo = sc.nextLine();

		if (!studentMap.containsKey(rollNo)) {
			System.out.println("Student with Roll No " + rollNo + " not found!");
			return;
		}

		studentMap.remove(rollNo);
		System.out.println("Student deleted successfully!");
	}

	// Add / update marks
	public void addOrUpdateMarks() {
		System.out.print("Enter RollNo: ");
		String rollNo = sc.nextLine();

		if (!studentMap.containsKey(rollNo)) {
			System.out.println("Student with Roll No " + rollNo + " not found!");
			return;
		}

		StudentDetails student = studentMap.get(rollNo);

		System.out.print("Enter number of subjects: ");
		int n = sc.nextInt();
		sc.nextLine();

		for (int i = 0; i < n; i++) {
			System.out.print("Enter subject name: ");
			String subject = sc.nextLine();
			System.out.print("Enter marks: ");
			int marks = sc.nextInt();
			sc.nextLine();

			student.addOrUpdateMark(subject, marks);
		}

		student.calculateTotal();
		student.calculatePercentage();
		student.assignGrade();

		System.out.println("Marks updated successfully for " + student.getName());
	}

	// Display single student
	public void displayStudent(String rollNo) {

		if (studentMap.containsKey(rollNo)) {
			StudentDetails s = studentMap.get(rollNo);
			System.out.println("Roll No: " + s.getRollNo());
			System.out.println("Name: " + s.getName());
			System.out.println("Age: " + s.getAge());
			System.out.println("Gender: " + s.getGender());
			System.out.println("Course: " + s.getCourse());
			System.out.println("Marks: " + s.getMarks());
			System.out.println("Total Marks: " + s.getTotalMarks());
			System.out.println("Percentage: " + String.format("%.2f", s.getPercentage()));
			System.out.println("Grade: " + s.getGrade());
		} else {
			System.out.println("Student with Roll No " + rollNo + " not found!");
		}

	}

	public void displayStudentByRollNo() {
		System.out.print("Enter RollNo: ");
		String rollNo = sc.nextLine();
		displayStudent(rollNo); // calls the above method
	}

	// Display all students
	public void displayAllStudents() {

		if (studentMap.isEmpty()) {
			System.out.println("No students found!");
			return;
		}

		System.out.println("-------- All Students --------");
		for (StudentDetails s : studentMap.values()) {
			System.out.println("Roll No: " + s.getRollNo());
			System.out.println("Name: " + s.getName());
			System.out.println("Age: " + s.getAge());
			System.out.println("Gender: " + s.getGender());
			System.out.println("Course: " + s.getCourse());
			System.out.println("Marks: ");
			for (Map.Entry<String, Integer> entry : s.getMarks().entrySet()) {
				System.out.println("  " + entry.getKey() + " : " + entry.getValue());
			}
			System.out.println("Total Marks: " + s.getTotalMarks());
			System.out.println("Percentage: " + String.format("%.2f", s.getPercentage()));
			System.out.println("Grade: " + s.getGrade());
			System.out.println("----------------------------");
		}

	}

	// Sort Students by criteria
	public void sortStudentsBy(String criteria) {
		List<StudentDetails> list = new ArrayList<>(studentMap.values());

		switch (criteria.toLowerCase()) {
		case "rollno":
			list.sort(Comparator.comparing(StudentDetails::getRollNo));
			break;
		case "name":
			list.sort(Comparator.comparing(StudentDetails::getName));
			break;
		case "percentage":
			list.sort((s1, s2) -> Double.compare(s2.getPercentage(), s1.getPercentage()));
			break;
		default:
			System.out.println("Invalid criteria!");
			return;
		}

		System.out.println("Sorted Students:");
		for (StudentDetails s : list) {
			s.displayInfo();
			System.out.println("-------------------------");
		}
	}

	// Topper
	public void getTopper() {
	    if (studentMap.isEmpty()) {
	        System.out.println("No students found!");
	        return;
	    }

	    ArrayList<StudentDetails> toppers = new ArrayList<>();
	    double maxPercent = -1;

	    // Find max percentage
	    for (StudentDetails s : studentMap.values()) {
	        double percent = s.getPercentage();
	        if (percent > maxPercent) {
	            maxPercent = percent;
	            toppers.clear();      // new topper found, clear old
	            toppers.add(s);
	        } else if (percent == maxPercent) {
	            toppers.add(s);       // same percentage, add to list
	        }
	    }
	    
	    System.out.println("-------- Topper(s) --------");
	    for (StudentDetails t : toppers) {
	        System.out.println("Roll No: " + t.getRollNo());
	        System.out.println("Name: " + t.getName());
	        System.out.println("Course: " + t.getCourse());
	        System.out.println("Total Marks: " + t.getTotalMarks());
	        System.out.println("Percentage: " + String.format("%.2f", t.getPercentage()));
	        System.out.println("Grade: " + t.getGrade());
	        System.out.println("------------------------");
	    }
	}
	
	
	// Search by name
	public List<StudentDetails> searchByName(String name) {
		List<StudentDetails> result = new ArrayList<>();

		for (StudentDetails s : studentMap.values()) {
			if (s.getName().equalsIgnoreCase(name)) { // ignore case
				result.add(s);
			}
		}

		return result;
	}

	// Search by course
	public List<StudentDetails> searchByCourse(String course) {
		List<StudentDetails> result = new ArrayList<>();

		for (StudentDetails s : studentMap.values()) {
			if (s.getCourse().equalsIgnoreCase(course)) { // ignore case
				result.add(s);
			}
		}

		return result;
	}
}

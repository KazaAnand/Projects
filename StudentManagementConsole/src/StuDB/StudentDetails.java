/* 
Add/update marks

Calculate total, percentage, grade

Display student info

*/

package StuDB;

import java.util.Map;

public class StudentDetails {

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Map<String, Integer> getMarks() {
		return marks;
	}

	public void setMarks(Map<String, Integer> marks) {
		this.marks = marks;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public char getGrade() {
		return grade;
	}

	public void setGrade(char grade) {
		this.grade = grade;
	}

	private String rollNo;
	private String name;
	private int age;
	private String gender;
	private String course;

	private Map<String, Integer> marks; // subject, marks

	private int totalMarks;
	private double percentage;
	private char grade;

	// Constructor
	public StudentDetails(String rollNo, String name, int age, String gender, String course,
			Map<String, Integer> marks) {
		super();
		this.rollNo = rollNo;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.course = course;
		this.marks = marks;
	}

	// Add or update marks for a subject
	public void addOrUpdateMark(String subject, int mark) {
		marks.put(subject, mark);
	}

	// Calculate total marks
	public void calculateTotal() {
		totalMarks = 0; // reset before calculation
		for (int mark : marks.values()) {
			totalMarks += mark;
		}
	}

	// Calculate percentage
	public void calculatePercentage() {
		if (marks.size() > 0) {
			percentage = (double) totalMarks / marks.size();
		} else {
			percentage = 0.0;
		}
	}

	// Assign grade based on percentage
	public void assignGrade() {
		if (percentage >= 90)
			grade = 'A';
		else if (percentage >= 75)
			grade = 'B';
		else if (percentage >= 60)
			grade = 'C';
		else
			grade = 'D';
	}

	// Display student info
	public void displayInfo() {
		System.out.println("Roll No: " + rollNo);
		System.out.println("Name: " + name);
		System.out.println("Age: " + age);
		System.out.println("Gender: " + gender);
		System.out.println("Course: " + course);
		System.out.println("Marks: " + marks);
		System.out.println("Total Marks: " + totalMarks);
		System.out.println("Percentage: " + percentage);
		System.out.println("Grade: " + grade);
	}
}

package Mini_projects;

import java.util.*;
import java.sql.*;

public class StudentsManagementSystem  {

	private static Connection loadAndGetconnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "Sai@2003");

	}

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws Exception {

		boolean notExit = true;

		while (notExit) {
			System.out.println("1.Add Student");
			System.out.println("2.View All Students");
			System.out.println("3.Search Student by ID");
			System.out.println("4.Update Student");
			System.out.println("5.Delete Specific Student Record");
			System.out.println("6.Delete All Student Record");
			System.out.println("7.Exit");
			System.out.print("Enter your Choice :");
			int choices = sc.nextInt();

			switch (choices) {

			case 1 -> {
				addStudent();
				System.out.println();
			}

			case 2 -> {
				ViewAllStudents();
				System.out.println();
			}

			case 3 -> {
				searchStudent();
				System.out.println();
			}

			case 4 -> {
				updateStudent();
				System.out.println();
			}

			case 5 -> {
				deleteStudent();
				System.out.println();
			}

			case 6 -> {
				deleteAllStudents();
				System.out.println();
			}
			case 7 -> {
				notExit = false;
				System.out.println("\t\t\t-----------Exist Done!-----------");
			}
			default -> System.out.println("\t\t\t-------invalid choices--------\n");

			}
		}
	}

	public static void addStudent() throws Exception {

		Connection con1 = loadAndGetconnection();
		System.out.print("Enter the id :");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.print("enter the name:");
		String name = sc.nextLine();
		System.out.print("enter the age:");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.print("enter the course:");
		String course = sc.next().toUpperCase();

		PreparedStatement stmt = con1.prepareStatement("insert into students values(?,?,?,?)");
		stmt.setInt(1, id);
		stmt.setString(2, name);
		stmt.setInt(3, age);
		stmt.setString(4, course);
		int rows = stmt.executeUpdate();
		System.out.println("\t\t\tInsert Sucessfully\n");
	}

	public static void ViewAllStudents() throws Exception {
		Connection con = loadAndGetconnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("Select * from students");
		while (rs.next()) {
			System.out.println("\t\t\tID:" + rs.getInt(1) + "\n\t\t\tNAME:" + rs.getString(2) + "\n\t\t\tAGE:"
					+ rs.getInt(3) + "\n\t\t\tCOURSE:" + rs.getString(4) + "\n");
		}
	}

	public static void searchStudent() throws Exception {
		Connection con = loadAndGetconnection();
		System.out.print("which ID you can search\nenter the ID:");
		int id = sc.nextInt();
		PreparedStatement stmt = con.prepareStatement("select * from students where id=?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			System.out.println("\t\t\tID:" + rs.getInt(1) + "\n\t\t\tNAME:" + rs.getString(2) + "\n\t\t\tAGE:"
					+ rs.getInt(3) + "\n\t\t\tCOURSE:" + rs.getString(4) + "\n");
		}
	}

	public static void updateStudent() throws Exception {
		Connection con = loadAndGetconnection();
		System.out.print("Enter your ID:");
		int id = sc.nextInt();
		System.out.print("which one you can update\n1.name\n2.age\n3.course\nEnter your choices:");
		int choices = sc.nextInt();
		sc.nextLine();

		try {
			switch (choices) {
			case 1 -> {
				System.out.print("Enter your name:");
				String name = sc.nextLine();
				PreparedStatement stmt = con.prepareStatement("update students set name=? where id=?");
				stmt.setString(1, name);
				stmt.setInt(2, id);
				int row1 = stmt.executeUpdate();
				System.out.println("\t\t\tName will be updated sucessfully");
			}

			case 2 -> {
				System.out.print("Enter your age:");
				int age = sc.nextInt();
				PreparedStatement stmt = con.prepareStatement("update students set age=? where id=?");
				stmt.setInt(1, age);
				stmt.setInt(2, id);
				int row1 = stmt.executeUpdate();
				System.out.println("\t\t\tAge will be updated sucessfully");
			}

			case 3 -> {
				System.out.print("Enter your course:");
				String course = sc.nextLine();
				PreparedStatement stmt = con.prepareStatement("update students set course=? where id=?");
				stmt.setString(1, course);
				stmt.setInt(2, id);
				int row1 = stmt.executeUpdate();
				System.out.println("\t\t\tCourse will be updated sucessfully");
			}
			default -> {
				System.out.println("\t\t\tInvalid choices");
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteStudent() throws Exception {
		Connection con = loadAndGetconnection();
		System.out.print("Enter your ID:");
		int id = sc.nextInt();
		PreparedStatement stmt = con.prepareStatement("delete from students where id=?");
		stmt.setInt(1, id);
		int rows = stmt.executeUpdate();
		System.out.println("\t\t\tRecord will be deleted successfully");
	}

	public static void deleteAllStudents() throws Exception {
		Connection con = loadAndGetconnection();
		System.out.print("Are sure to Delete All Records(YES/NO):");
		String option = sc.next().toLowerCase();
		System.out.println("please wait...");
		Thread.sleep(5000);
		if (option.equals("yes")) {
			Statement stmt = con.createStatement();
			int rows = stmt.executeUpdate("Truncate table students");
			System.out.println("\t\t\tAll Records will be Deleted successfully");
		} else {
			System.out.println("\t\t\tProcess denied!");
		}
	}
}


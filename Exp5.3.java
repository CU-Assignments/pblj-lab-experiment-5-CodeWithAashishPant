Menu-based Java application that allows you to add employee details, display all employees, and exit. The employee details will be stored in a file, and the program will
read the file to display the stored employee information.

Steps:
1. Create an Employee class with fields like name, id, designation, and salary.
2. Create a menu with three options:
Add an Employee
Display All Employees
Exit
3. Store Employee Data in a File: Serialize the employee objects and store them in a file.
4. Read Employee Data from the File: Deserialize the employee objects from the file and display the details.
5. Handle Exceptions: Handle file I/O exceptions.



---Implementation
  
Employee Class: This class contains details like name, id, designation, and salary. It implements Serializable to allow serialization of Employee objects.
addEmployee(): This method takes input from the user for an employee's details, creates an Employee object, and saves it to a file using ObjectOutputStream.
saveEmployeeToFile(): This method appends employee details to a file. The file is opened in append mode (true parameter in FileOutputStream).
displayAllEmployees(): This method reads all employee objects from the file and prints their details.
readEmployeesFromFile(): This method reads the employee objects from the file using ObjectInputStream and stores them in a list. 
The loop continues until the end of the file is reached (IOFException).

//code
import java.io.*;
import java.util.*;

class Employee implements Serializable {
    private String name;
    private int id;
    private String designation;
    private double salary;

    public Employee(String name, int id, String designation, double salary) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Salary: " + salary;
    }
}

public class EmployeeManagement {

    private static final String FILE_NAME = "employees.ser";

    public static void addEmployee() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Employee Name: ");
        String name = scanner.nextLine();

        System.out.println("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Designation: ");
        String designation = scanner.nextLine();

        System.out.println("Enter Salary: ");
        double salary = scanner.nextDouble();

        Employee employee = new Employee(name, id, designation, salary);
        saveEmployeeToFile(employee);
        System.out.println("Employee added successfully!");
    }

    public static void saveEmployeeToFile(Employee employee) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME, true))) {
            oos.writeObject(employee);
        } catch (IOException e) {
            System.out.println("Error occurred while saving employee: " + e.getMessage());
        }
    }

    public static void displayAllEmployees() {
        List<Employee> employees = readEmployeesFromFile();
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

    public static List<Employee> readEmployeesFromFile() {
        List<Employee> employees = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            while (true) {
                Employee employee = (Employee) ois.readObject();
                employees.add(employee);
            }
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error occurred while reading employees: " + e.getMessage());
        }
        return employees;
    }

    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayAllEmployees();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 3);
    }

    public static void main(String[] args) {
        showMenu();
    }
}


Test Cases:

Test Case 1: Add a new employee and display all employees.
Steps: Select option 1 to add a new employee, then select option 2 to display all employees.
Input:
Employee Name: John Doe
Employee ID: 101
Designation: Software Engineer
Salary: 50000
  
Expected Output:
Employee added successfully!
Employee ID: 101, Name: John Doe, Designation: Software Engineer, Salary: 50000.0

Test Case 2: Try adding multiple employees and display all of them.
Steps: Add multiple employees (using option 1) and then display all employees (using option 2).
Expected Output:
Employee added successfully!
Employee ID: 101, Name: John Doe, Designation: Software Engineer, Salary: 50000.0
Employee added successfully!
Employee ID: 102, Name: Jane Smith, Designation: Manager, Salary: 75000.0

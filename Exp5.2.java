Java program that serializes and deserializes a Student object. It saves the Student object to a file and then reads it back, displaying the student details.
The program handles exceptions like FileNotFoundException, IOException, and ClassNotFoundException.

Steps:
1. Create a Student class with id, name, and GPA.
2. Serialize the Student object: Convert the object to a byte stream and save it to a file.
3. Deserialize the Student object: Read the byte stream from the file and convert it back into an object.
4. Exception handling: Handle possible exceptions such as FileNotFoundException, IOException, and ClassNotFoundException.


----Implementation
---Student Class: The Student class implements the Serializable interface, allowing it to be serialized. It has three fields: id, name, and gpa.
---serializeStudent(): This method serializes a Student object to a file using ObjectOutputStream. The object is written to a file named student.ser.
---deserializeStudent(): This method deserializes the Student object from the file using ObjectInputStream. If successful, it returns the deserialized Student object.
---Exception Handling: The program handles FileNotFoundException, IOException, and ClassNotFoundException during the serialization and deserialization processes.

//code
import java.io.*;

class Student implements Serializable {
    private int id;
    private String name;
    private double gpa;

    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name + ", GPA: " + gpa;
    }
}

public class StudentSerialization {

    // Method to serialize the Student object to a file
    public static void serializeStudent(Student student) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
            oos.writeObject(student);
            System.out.println("Student object has been serialized and saved to file.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        } catch (IOException e) {
            System.out.println("Error: IO Exception occurred during serialization.");
        }
    }

    // Method to deserialize the Student object from a file
    public static Student deserializeStudent() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.ser"))) {
            Student student = (Student) ois.readObject();
            System.out.println("Student object has been deserialized.");
            return student;
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        } catch (IOException e) {
            System.out.println("Error: IO Exception occurred during deserialization.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Class not found.");
        }
        return null;
    }

    public static void main(String[] args) {
        // Test Case 1: Serialize and Deserialize a valid student object
        Student student = new Student(1, "John Doe", 3.75);
        serializeStudent(student);

        Student deserializedStudent = deserializeStudent();
        if (deserializedStudent != null) {
            System.out.println("Deserialized Student Details:");
            System.out.println(deserializedStudent);
        }

        // Test Case 2: Try to deserialize from a non-existent file
        System.out.println("\nTest Case 2: Deserialize from non-existent file");
        File file = new File("student.ser");
        file.delete();  // Simulate the non-existent file by deleting it
        deserializeStudent();

        // Test Case 3: Handle invalid class during deserialization (simulate by changing class name or modifying class)
        // Uncomment the following to simulate ClassNotFoundException scenario
        // System.out.println("\nTest Case 3: Handle invalid class during deserialization");
        // This should throw ClassNotFoundException if class is not found in the classpath.
        // Deserialize after modifying the class
        // deserializeStudent();
    }
}

Test Cases:

Test Case 1: Serialize and Deserialize a valid student object.
  
Input: Student(1, "John Doe", 3.75)
Expected Output:
Student object has been serialized and saved to file.
Student object has been deserialized.
Deserialized Student Details:
Student ID: 1, Name: John Doe, GPA: 3.75
  
Test Case 2: Try to deserialize from a non-existent file.
Expected Output:
Error: File not found.
  
Test Case 3: Handle invalid class during deserialization.
Input: Manually modify the class file to simulate a ClassNotFoundException.
Expected Output:
Error: Class not found.

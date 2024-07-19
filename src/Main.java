import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Collecting student information
        System.out.println("Enter student name: ");
        String name = input.nextLine();
        System.out.println("Enter student age: ");
        int age = input.nextInt();
        input.nextLine(); // Consume newline
        System.out.println("Enter student major: ");
        String major = input.nextLine();
        System.out.println("Enter student GPA: ");
        double gpa = input.nextDouble();

        // Creating a student object
        Student student = new Student(name, age, major, gpa);

        // Serializing the student object
        serializeStudent(student);

        // Deserializing the student object
        Student deserializedStudent = deserializeStudent();

        // Displaying the deserialized student object
        System.out.println(deserializedStudent);
    }

    private static void serializeStudent(Student student) {
        try (FileOutputStream fileOut = new FileOutputStream("student.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(student);
            System.out.println("Serialized data is saved in student.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private static Student deserializeStudent() {
        Student student = null;
        try (FileInputStream fileIn = new FileInputStream("student.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            student = (Student) in.readObject();
            System.out.println("Deserialized data from student.ser");
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Student class not found");
            c.printStackTrace();
        }
        return student;
    }
}
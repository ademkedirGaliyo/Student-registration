import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    protected String name;
    protected String studentID;
    protected String uid;
    protected int age;
    protected String department;
    protected int year;

    public Student(String name, String studentID, String uid, int age, String department, int year) {
        this.name = name;
        this.studentID = studentID;
        this.uid = uid;
        this.age = age;
        this.department = department;
        this.year = year;
    }

    public void display() {
        System.out.println("Name: " + name + ", ID: " + studentID + ", UID: " + uid + ", Age: " + age
                + ", Department: " + department + ", Year: " + year);
    }

    public void saveToFile(String filename, boolean append) {
        try (FileWriter fileWriter = new FileWriter(filename, append);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write(this.name + "," + this.studentID + "," + this.uid + "," + this.age + ","
                    + this.department + "," + this.year + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Student> retrieveFromFile(String filename) {
        ArrayList<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Student student = new Student(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4],
                        Integer.parseInt(data[5]));
                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static Student inputStudentInfoFromUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student name:");
        String name = scanner.nextLine();

        System.out.println("Enter student ID:");
        String studentID = scanner.nextLine();

        System.out.println("Enter student UID:");
        String uid = scanner.nextLine();

        System.out.println("Enter student age:");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character after nextInt()

        System.out.println("Enter student department:");
        String department = scanner.nextLine();

        System.out.println("Enter student year:");
        int year = scanner.nextInt();

        return new Student(name, studentID, uid, age, department, year);
    }
}

class BatchStudent extends Student {
    private ArrayList<Student> students;

    public BatchStudent(ArrayList<Student> students) {
        super("", "", "", 0, "", 0);
        this.students = students;
    }

    @Override
    public void display() {
        for (Student student : students) {
            student.display();
        }
    }

    @Override
    public void saveToFile(String filename, boolean append) {
        try (FileWriter fileWriter = new FileWriter(filename, append);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            for (Student student : this.students) {
                writer.write(student.name + "," + student.studentID + "," + student.uid + "," + student.age + ","
                        + student.department + "," + student.year + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

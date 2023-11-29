import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> studentList = new ArrayList<>();

        // Take information for each student from the user
        for (int i = 0; i < 2; i++) {
            System.out.println("Enter details for student " + (i + 1));
            Student student = Student.inputStudentInfoFromUser();
            studentList.add(student);
        }

        // Create a batch of students
        BatchStudent batch = new BatchStudent(studentList);

        // Display all students in the batch
        batch.display();

        // Save students to a file (appending)
        batch.saveToFile("students.txt", true);

        // Retrieve students from the file
        ArrayList<Student> retrievedStudents = Student.retrieveFromFile("students.txt");

        // Display retrieved students
        for (Student student : retrievedStudents) {
            student.display();
        }
    }
}

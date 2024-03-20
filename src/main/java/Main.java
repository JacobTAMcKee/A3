import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Main {
    static void getAllStudents(){
        String url = "jdbc:postgresql://localhost:5432/A3DB";
        String user = "postgres";
        String password = "poplop123";

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students");
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()){
                String id = resultSet.getString("student_id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                Date date = resultSet.getDate("enrollment_date");
                System.out.println("ID: " + id + "\t" + " Name: " + first_name + " " +
                        last_name + "\t" + " Email: " + email + "\t" + " Enrollment Date: " + date);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    static void addStudent(String first_name, String last_name, String email, Date date){
        String url = "jdbc:postgresql://localhost:5432/A3DB";
        String user = "postgres";
        String password = "poplop123";

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String insertSQL = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES ('" +
                    first_name + "', '" + last_name + "', '" + email + "', '" + date + "')";
            int rowsInserted = statement.executeUpdate(insertSQL);
            if (rowsInserted > 0) {
                System.out.println("A new student was inserted successfully!");
            } else{
                System.out.println("Error inserting new student!");
            }
            statement.close();
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    static void updateStudentEmail(int student_id, String new_email){
        String url = "jdbc:postgresql://localhost:5432/A3DB";
        String user = "postgres";
        String password = "poplop123";

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String insertSQL = "UPDATE students SET email = '" + new_email + "' where student_id = " + student_id;
            statement.executeUpdate(insertSQL);
            statement.close();
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    static void deleteStudent(int student_id){
        String url = "jdbc:postgresql://localhost:5432/A3DB";
        String user = "postgres";
        String password = "poplop123";

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String insertSQL = "DELETE FROM students where student_id = " + student_id;
            statement.executeUpdate(insertSQL);
            statement.close();
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    static void displayMenu(){
        System.out.println("Please select an option:");
        System.out.println("1: Display All Students");
        System.out.println("2: Add Student");
        System.out.println("3: Update Student Email");
        System.out.println("4: Delete Student");
        System.out.println("0: Exit");
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while(true){
            displayMenu();
            int choice = s.nextInt();
            s.nextLine();
            switch(choice){
                case 0:
                    System.exit(0);
                case 1:
                    getAllStudents();
                    break;
                case 2:
                    System.out.println("Input First Name:");
                    String first_name = s.nextLine();
                    System.out.println("Input Last Name:");
                    String last_name = s.nextLine();
                    System.out.println("Input Email:");
                    String email = s.nextLine();
                    System.out.println("Input Enrollment Date (yyyy mm dd):");
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.YEAR, s.nextInt());
                    cal.set(Calendar.MONTH, s.nextInt()-1);
                    cal.set(Calendar.DAY_OF_MONTH, s.nextInt());
                    Date d = new Date(cal.getTimeInMillis());
                    addStudent(first_name, last_name, email, d);
                    break;
                case 3:
                    System.out.println("Input ID Of Student To Update:");
                    int student_id = s.nextInt();
                    System.out.println("Input New Email:");
                    String new_email = s.nextLine();
                    updateStudentEmail(student_id, new_email);
                    break;
                case 4:
                    System.out.println("Input ID Of Student To Delete:");
                    int id = s.nextInt();
                    deleteStudent(id);
                    break;
            }
        }
    }
}

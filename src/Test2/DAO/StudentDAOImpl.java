package Test2.DAO;

import Test2.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO<Student, String>{
    private Connection dbConnection;
    public StudentDAOImpl(){
        try {
            String connectionString = "jdbc:mysql://localhost:3306/users?useUnicode=true&serverTimezone=UTC";
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.dbConnection = DriverManager.getConnection(connectionString, "root", "12345678");
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public void addStudent(Student student){
        try {
            String sql = "insert into users.students (firstName,lastName,patronymic,birthDay,groupName,uniqNumber) values(?,?,?,?,?,?)";
            PreparedStatement prSt = dbConnection.prepareStatement(sql);
            prSt.setString(1, student.getFirstName());
            prSt.setString(2, student.getLastName());
            prSt.setString(3, student.getPatronymic());
            prSt.setString(4, student.getBirthDay());
            prSt.setString(5, student.getGroup());
            prSt.setString(6, student.getUniqNumber().toString());
            prSt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public List<Student> findAllStudents(){
        List<Student> students = new ArrayList<Student>();
        try {
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from users.students");
            while (resultSet.next()) {
                students.add(new Student(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        Integer.parseInt(resultSet.getString(6))
                ));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return students;
    }
    public boolean deleteStudentByUniqNumber(String uniqNumber){
        try {
            return dbConnection.createStatement().executeUpdate("delete from users.students where uniqNumber = '" + uniqNumber + "'") != 0;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean checkUniqness(String uniqNumber) {
        try {
            ResultSet resultSet = dbConnection.createStatement().executeQuery("Select COUNT(*) from users.students where uniqNumber = '" + uniqNumber + "'");
            while (resultSet.next()) return resultSet.getInt("COUNT(*)") == 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void close(){
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

package Test2.controller;

import Test2.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler{
    protected Connection dbConnection;
    public Connection getConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://localhost:3306/users?useUnicode=true&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, "root", "12345678");
        return dbConnection;
    }
    public void addStudent(Student student) throws ClassNotFoundException, SQLException{
        String sql = "insert into users.students (firstName,lastName,patronymic,birthDay,groupName,uniqNumber) values(?,?,?,?,?,?)";
        PreparedStatement prSt = getConnection().prepareStatement(sql);
        prSt.setString(1,student.getFirstName());
        prSt.setString(2,student.getLastName());
        prSt.setString(3,student.getPatronymic());
        prSt.setString(4,student.getBirthDay());
        prSt.setString(5,student.getGroup());
        prSt.setString(6,student.getUniqNumber().toString());
        prSt.executeUpdate();

    }
    public List<Student> findAllStudents() {
        try {
            List<Student> students = new ArrayList<Student>();
            Statement statement = getConnection().createStatement();
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
            return students;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }
    public boolean deleteStudentByUniqNumber(String uniqNumber){
        try {
            return getConnection().createStatement().executeUpdate("delete from users.students where uniqNumber = '" + uniqNumber + "'") != 0;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean checkUniqness(String uniqNumber){
        try {
            ResultSet resultSet = getConnection().createStatement().executeQuery("Select COUNT(*) from users.students where uniqNumber = '" + uniqNumber + "'");
            while(resultSet.next()) return resultSet.getInt("COUNT(*)") == 0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

}

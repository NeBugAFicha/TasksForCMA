package Test2.DAO;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO <Entity, Key> {
    void addStudent (Entity entity);
    List<Entity> findAllStudents();
    boolean deleteStudentByUniqNumber(Key key);
    boolean checkUniqness(Key key);
    void close();
}

package AndroidBasic.DataBase.RoomDataBase;

import androidx.room.*;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM student")
    List<Student> getAll();

    @Query("SELECT * FROM student WHERE id IN (:studentIds)")
    List<Student> loadAllByIds(int[] studentIds);

    @Insert
    void insertAll(Student... students);

    @Delete
    void delete(Student student);

    @Query ("DELETE FROM student WHERE Sname = :SName")
    void deleteByName(String SName);


    @Query("SELECT * FROM student WHERE Sname = :SName")
    List<Student> getDataByName(String SName);

    @Update
    void updateUser(Student student);
}

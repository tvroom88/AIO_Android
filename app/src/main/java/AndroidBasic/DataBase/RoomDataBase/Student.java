package AndroidBasic.DataBase.RoomDataBase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student")
public class Student {
    @PrimaryKey(autoGenerate = true)
    public int id;

//    @ColumnInfo(name = "Sname")
    public String Sname;

//    @ColumnInfo(name = "age")
    public int age;

//    @ColumnInfo(name = "major")
    public String major;

    Student(String Sname, int age, String major){
        this.Sname = Sname;
        this.age = age;
        this.major = major;
    }

    public int getId() {
        return id;
    }
    public String getSName() {
        return Sname;
    }
    public int getAge() {
        return age;
    }
    public String getMajor() {
        return major;
    }

//    public void setSName() {
//        this.Sname = Sname;
//    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setMajor(String major) {
        this.major = major;
    }
}

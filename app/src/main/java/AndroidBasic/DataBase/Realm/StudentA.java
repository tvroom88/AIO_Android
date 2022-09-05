package AndroidBasic.DataBase.Realm;

import androidx.room.PrimaryKey;
import io.realm.RealmObject;

public class StudentA extends RealmObject {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String Sname;
    private int age;
    private String major;

    public StudentA(){

    }

    public StudentA(String Sname, int age, String major){
        this.Sname = Sname;
        this.age = age;
        this.major = major;
    }

    public int getId() { return id; }

    public String getSName() {
        return Sname;
    }

    public int getAge() {
        return age;
    }

    public String getMajor() {
        return major;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setSName(String name) {
        this.Sname = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}

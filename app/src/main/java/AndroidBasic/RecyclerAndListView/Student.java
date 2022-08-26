package AndroidBasic.RecyclerAndListView;


public class Student {

    public int id;
    public String name;
    public int age;


    Student(int id, String Sname, int age) {
        this.id = id;
        this.name = Sname;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
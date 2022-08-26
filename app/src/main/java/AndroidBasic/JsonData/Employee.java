package AndroidBasic.JsonData;

public class Employee {

    private int id;
    private String name;
    private String email;

    Employee(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee 객체 " + '\n' +
                "id: " + id  + '\n' +
                "name: "+ name +  '\n' +
                "email: " + email + "\n\n";
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }
}

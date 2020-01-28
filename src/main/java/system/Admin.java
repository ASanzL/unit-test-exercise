package system;

public class Admin extends Account {
    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRole() {
        return "Adminstrator";
    }

    @Override
    public int getSalary() {
        return 50000;
    }
}

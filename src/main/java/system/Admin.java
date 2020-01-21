package system;

public class Admin extends Account {
    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRole() {
        return "Adminstrator";
    }
}

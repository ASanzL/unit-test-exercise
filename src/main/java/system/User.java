package system;

public class User extends Account {
    private int requestedSalary;
    public User(String username, String password) {
        super(username, password);
        setRole("User");
        requestedSalary = getSalary();
    }

    public int getRequestedSalary() {
        return requestedSalary;
    }

    public boolean setRequestedSalary(int requestedSalary) {
        if (requestedSalary > 0) {
            this.requestedSalary = requestedSalary;
            return true;
        }
        return false;
    }
}

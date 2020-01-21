package systemtest;

import org.junit.Before;
import system.User;

public class UserTest {
    User user = new User("username", "password");

    @Before
    public void before() {
        user = new User("username", "password");
    }
}

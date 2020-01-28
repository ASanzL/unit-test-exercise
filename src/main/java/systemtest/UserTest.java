package systemtest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import system.User;

public class UserTest {
    User user = new User("username", "password");

    @Before
    public void before() {
        user = new User("username", "password");
    }

    @Test
    public void testSetRequstedSalary() {
        Assert.assertEquals("Default requested salary should be 30000",
                30000, user.getRequestedSalary());

        Assert.assertEquals("Salary should not be set to lower than 1",
                false, user.setRequestedSalary(-100));

        user.setRequestedSalary(35000);
        Assert.assertEquals("Requested salary should be  set to 35000",
                35000, user.getRequestedSalary());

    }
}

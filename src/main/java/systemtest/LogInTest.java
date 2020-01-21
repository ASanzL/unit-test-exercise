package systemtest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import system.Admin;
import system.LogIn;

public class LogInTest {
    LogIn logIn = new LogIn();

    @Before
    public void before() {
        logIn = new LogIn();
        logIn.addAccount(new Admin("admin", "admin"));
    }

    @Test
    public void testAddAccount() {
        Assert.assertEquals("Number of accounts should equal 1", 2, logIn.getAccounts().size());
    }
    
    @Test
    public void testLogIn() {
        Assert.assertEquals("Should log in with correct username and password",
                true, logIn.tryToLogIn("admin", "admin"));
    }
}

package systemtest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import system.Admin;
import system.LogIn;
import system.User;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class LogInTest {
    LogIn logIn = new LogIn();

    @Before
    public void before() {
        logIn = new LogIn();
        logIn.addAccount(new Admin("admin", "admin"));
    }

    @Test
    public void testAddAccount() {
        Assert.assertEquals("Number of accounts should equal 1", 1, logIn.getAccounts().size());
    }
    
    @Test
    public void testLogIn() {
        Assert.assertEquals("Should log in with correct username and password",
                true, logIn.tryToLogIn("admin", "admin", false));
    }

    @Test
    public void testCreateNewAccount() {
        Assert.assertEquals("New user should be created",
                true, logIn.createNewAccount(new User("user1", "password1")));
        Assert.assertEquals("New user should not be created",
                false, logIn.createNewAccount(new User("user", "password1")));
    }

    @Test
    public void testValidUsernameAndPassword() {
        Assert.assertEquals("Username should be valid",
                true, LogIn.isUsernameOrPasswordValid("username7"));
        Assert.assertEquals("Password should not be valid",
                false, LogIn.isUsernameOrPasswordValid("pass"));
    }

    @Test
    public void testRemoveUser() {
        Assert.assertEquals("Wrong username and password should not remove a user",
                false, logIn.removeAccount(new User("wronguser", "wrongpass")));

        Assert.assertEquals("New user should be created",
                true, logIn.createNewAccount(new User("user5", "password5")));
        Assert.assertEquals("Correct username and password should remove a user",
                true, logIn.removeAccount(new User("user5", "password5")));
    }
}

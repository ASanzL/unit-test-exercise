package systemtest;

import org.junit.Test;
import system.Admin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdminTest {
Admin admin;
    @Test
    public void roleShouldntBeEmpty() {
        Admin admin = new Admin("admin", "admin");
        assertTrue(admin.getRole() != null && admin.getRole().length() > 0);
    }

}

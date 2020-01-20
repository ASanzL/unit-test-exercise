import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnitTest {

    @Test
    public void roleShouldntBeEmpty() {
        Admin admin = new Admin("admin", "admin");
        assertTrue(admin.getRole() != null && admin.getRole().length() > 0);
    }

}

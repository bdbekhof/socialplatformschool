package nl.bdbekhof.socialplatform.unit;

import nl.bdbekhof.socialplatform.util.ValidationUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationUtilTest {
    @Test
    void usernameTooShort() {
        assertFalse(ValidationUtil.validUsername("ab"));
    }
    @Test
    void validUsername() {
        assertTrue(ValidationUtil.validUsername("User_123"));
    }
}

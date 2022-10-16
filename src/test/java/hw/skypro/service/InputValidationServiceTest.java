package hw.skypro.service;

import hw.skypro.exception.IncorrectFirstNameException;
import hw.skypro.exception.IncorrectLastNameException;
import org.junit.jupiter.api.Test;

import static hw.skypro.constants.TestsConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class InputValidationServiceTest {

    InputValidationService out = new InputValidationService();

    @Test
    void checkFirstNameValid() {
           assertEquals(CORRECT_FIRSTNAME, out.checkFirstNameValid(CORRECT_FIRSTNAME));
    }

    @Test
    void checkLastNameValid() {
        assertEquals(CORRECT_LASTNAME, out.checkLastNameValid(CORRECT_LASTNAME));

    }
    @Test
    void checkFirstNameInValidException() {
        assertThrows(IncorrectFirstNameException.class,
                () -> out.checkFirstNameValid(ILLEGAL_CHARS_NAME));
    }

    @Test
    void checkExceptionLastNameInValidException() {
        assertThrows(IncorrectLastNameException.class,
                () -> out.checkLastNameValid(ILLEGAL_CHARS_NAME));
    }
}

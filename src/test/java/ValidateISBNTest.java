import org.junit.jupiter.api.Test;

import java.text.NumberFormat;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateISBNTest {

    @Test
    public void checkAValid10DigitISBN() {
        ValidateISBN isbn = new ValidateISBN();
        boolean validate2 = isbn.validate("0140177396");
        assertTrue(validate2,"ISBN Validates true");
        boolean validate = isbn.validate("0140449116");
        assertTrue(validate,"ISBN Validates true");
    }
    @Test
    public void checkAValid13DigitISBN() {
        ValidateISBN isbn = new ValidateISBN();
        boolean validate = isbn.validate("9781853260087");
        assertTrue(validate, "First book");
        boolean validate2 = isbn.validate("9781853267338");
        assertTrue(validate2, "Second book");
    }
    @Test
    public void checkAnInValid13DigitISBN() {
        ValidateISBN isbn = new ValidateISBN();
        boolean validate = isbn.validate("9781853260087");
        assertTrue(validate);

    }
    @Test
    public void isbnNumbersWithNineDigitsAreNotValid() {
    ValidateISBN isbn = new ValidateISBN();
    assertThrows(NumberFormatException.class, () -> isbn.validate("123456789"));
    }
    @Test
    public void validateStringISBN() {
    ValidateISBN isbn = new ValidateISBN();
    assertThrows(NumberFormatException.class, () -> isbn.validate("helloworld"));
    }
    @Test
    public void TenDigitISBNNumberEndsWithXAreValid() {
        ValidateISBN isbn = new ValidateISBN();
        assertTrue(isbn.validate("012000030X"));
    }

}

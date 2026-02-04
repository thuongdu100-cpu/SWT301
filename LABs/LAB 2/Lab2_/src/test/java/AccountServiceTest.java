import org.example.Account;
import org.example.AccountService;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
public class AccountServiceTest {
    private AccountService service;

    @BeforeEach
    void setUp() {
        service = new AccountService();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testRegisterFromCSV(String username, String password, String email, boolean expected) {
        Account acc = new Account(username, password, email);
        boolean actual = service.registerAccount(acc);
        assertEquals(expected, actual,
                () -> "Test failed for user: " + username);
    }
    @Test
    void testValidAccountShouldRegisterSuccessfully() {
        Account acc = new Account("john123", "Pass@123", "john@example.com");
        assertTrue(service.registerAccount(acc));
    }
    @Test
    void testEmptyUsernameShouldFail() {
        Account acc = new Account("", "Pass@123", "john@example.com");
        assertFalse(service.registerAccount(acc));
    }
    @Test
    void testShortPasswordShouldFail() {
        Account acc = new Account("alice", "abc", "alice@mail.com");
        assertFalse(service.registerAccount(acc));
    }
    @Test
    void testInvalidEmailShouldFail() {
        Account acc = new Account("bob123", "Password@1", "bobmail.com");
        assertFalse(service.registerAccount(acc));
    }
}

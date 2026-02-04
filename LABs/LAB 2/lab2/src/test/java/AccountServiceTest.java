package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountServiceTest {
    private AccountService service;

    @BeforeEach
    void setUp() {
        service = new AccountService();
    }

    // ✅ Kiểm thử registerAccount bằng dữ liệu từ test-data.csv
    @ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testRegisterFromCSV(String username, String password, String email, String expectedStr) {
        Account acc = new Account(username, password, email);
        boolean expected = Boolean.parseBoolean(expectedStr);
        boolean actual = service.registerAccount(acc);
        assertEquals(expected, actual, "Test failed for user: " + username);
    }

    // ✅ Các test case đơn lẻ tương ứng với test-data.csv
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
        Account acc = new Account("alice", "Short1!", "alice@mail.com");
        assertFalse(service.registerAccount(acc));
    }

    @Test
    void testInvalidEmailShouldFail() {
        Account acc = new Account("bob123", "Password@1", "bobmail.com");
        assertFalse(service.registerAccount(acc));
    }

    @Test
    void testAnotherValidAccountShouldRegisterSuccessfully() {
        Account acc = new Account("carol", "Carol@123", "carol@domain.com");
        assertTrue(service.registerAccount(acc));
    }
}

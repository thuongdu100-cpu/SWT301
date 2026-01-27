package org.example;

public class AccountService {

    public boolean registerAccount(Account acc) {
        if (acc.getUsername() == null || acc.getUsername().isEmpty()) {
            return false;
        }
        if (!isValidPassword(acc.getPassword())) {
            return false;
        }
        if (!isValidEmail(acc.getEmail())) {
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) return false; // phải >= 8 ký tự
        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasSpecial = password.matches(".*[^a-zA-Z0-9].*");
        return hasUpper && hasLower && hasSpecial;
    }

    private boolean isValidEmail(String email) {
        if (email == null) return false;
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
}

package org.example;

import java.util.regex.Pattern;

public class AccountService {
        public boolean isValidEmail(String email) {
            String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            return Pattern.matches(regex, email);
        }

        public boolean isValidPassword(String password) {
             if (password == null || password.length() < 8) return false;
            boolean hasUpper = password.matches(".*[A-Z].*");
            boolean hasLower = password.matches(".*[a-z].*");
             boolean hasSpecial = password.matches(".*[^a-zA-Z0-9].*");
             return hasUpper && hasLower && hasSpecial;
         }


    public boolean isValidUsername(String username) {
            return username != null && username.length() >= 3;
        }

        public boolean registerAccount(Account account) {
            return isValidUsername(account.getUsername()) &&
                    isValidPassword(account.getPassword()) &&
                    isValidEmail(account.getEmail());
        }

}

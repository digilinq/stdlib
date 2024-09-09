package com.eightbits.shared.stdlib.security;



import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PasswordGenerator {

    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()-_+=";

    public static String generatePassword(int length, boolean includeChars, boolean includeNumbers, boolean includeSpecialChars, boolean includeLowercase, boolean includeUppercase) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        List<Character> validChars = new ArrayList<>();

        if (includeChars)
            addChars(validChars, LOWERCASE_CHARS);
        if (includeUppercase)
            addChars(validChars, UPPERCASE_CHARS);
        if (includeNumbers)
            addChars(validChars, NUMBERS);
        if (includeSpecialChars)
            addChars(validChars, SPECIAL_CHARS);

        if (validChars.isEmpty()) {
            throw new IllegalArgumentException("At least one character set must be included");
        }

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(validChars.size());
            password.append(validChars.get(randomIndex));
        }

        return password.toString();
    }

    private static void addChars(List<Character> list, String chars) {
        for (char c : chars.toCharArray()) {
            list.add(c);
        }
    }

    public static void main(String[] args) {
        int length = 12;
        boolean includeChars = true;
        boolean includeNumbers = true;
        boolean includeSpecialChars = true;
        boolean includeLowercase = true;
        boolean includeUppercase = true;

        String password = generatePassword(length, includeChars, includeNumbers, includeSpecialChars, includeLowercase, includeUppercase);
        System.out.println("Generated Password: " + password);
    }
}

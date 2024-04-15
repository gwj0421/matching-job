package kimchisoup.matchingjob.utils;

import java.security.SecureRandom;

public class RandomUtils {
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "!@#$%&*()_+-=[]?";

    private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private static final int CERTIFICATION_NUMBER_SIZE = 8;
    private static final int PASSWORD_LENGTH = 12;

    private RandomUtils() {
    }

    public static String createCertificationNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CERTIFICATION_NUMBER_SIZE; i++) {
            sb.append(SECURE_RANDOM.nextInt(10));
        }
        return sb.toString();
    }

    public static String createInitPassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int randomCharIndex = SECURE_RANDOM.nextInt(PASSWORD_ALLOW_BASE.length());
            password.append(PASSWORD_ALLOW_BASE.charAt(randomCharIndex));
        }
        return password.toString();
    }
}

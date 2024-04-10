package kimchisoup.matchingjob.utils;

import java.security.SecureRandom;

public class RandomUtils {
    private static final int CERTIFICATION_NUMBER_SIZE = 8;
    private static final SecureRandom secureRandom = new SecureRandom();

    private RandomUtils() {
    }

    public static String createCertificationNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CERTIFICATION_NUMBER_SIZE; i++) {
            sb.append(secureRandom.nextInt(10));
        }
        return sb.toString();
    }

}

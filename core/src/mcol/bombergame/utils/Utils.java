package mcol.bombergame.utils;

import java.util.Random;

public class Utils {

    /** Random number generator. */
    private static final Random random = new Random();

    /** Returns a random integer between two values (extremes included). */
    public static int randomInteger(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }
}

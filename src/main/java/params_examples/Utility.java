package params_examples;

import java.util.stream.IntStream;

public class Utility {

    // 1. Check if the number is odd
    public static boolean isOdd(int number) {
        return number % 2 != 0; // 0 - false, !0 - true (1)
    }

    // 2. Rounds a double value to the nearest integer
    public static long roundDouble(double value) {
        return Math.round(value);
    }

    // 3. Check if a string is palindrome
    // 121 - 121
    // ana - ana
    // apa - apa
    public static boolean isPalindrome(String text) {
        // conversie nr -> string

        if (text == null || text.isEmpty())
            return false;

        String reversed = new StringBuilder(text).reverse().toString(); // atentie la spatiile albe
        return text.equalsIgnoreCase(reversed);
        // conversie string -> nr
    }

    // 4. Convert string to uppercase
    public static String toUpperCase(String text) {
        return text.toUpperCase();
    }

    // 5. Check if an object is an instance of a given class
    public static boolean isInstanceOf(Object obj, Class<?> clazz) {
        return clazz.isInstance(obj);
    }

    // 6. Check if an integer is prime
    public static boolean isPrime(int number) {
        if (number < 2)
            return false;

        // for i = [2, sqrt(number)] - contor = 0
        // for i = [2, number / 2] - contor = 0
        // for i = [0, number] - contor = 2
        return IntStream.rangeClosed(2, (int) Math.sqrt(number))
                .allMatch(it -> number % it != 0);
    }
}

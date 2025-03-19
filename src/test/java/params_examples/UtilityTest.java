package params_examples;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class UtilityTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9, 11, 13, 14, -3, -7, -9})
    public void testIsOddHappyFlow(int number) {
        assertThat(Utility.isOdd(number))
                .withFailMessage("isOddHappyFlow should return true if number is odd")
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 1, -10, -2, -4})
    public void testIsOddBadFlow(int number) {
        assertThat(Utility.isOdd(number))
                .isFalse();
    }

    @ParameterizedTest
    @ValueSource(doubles = {2.3, 2.5, 2.9, -2.3, -2.5, -2.9})
    public void testRoundDoubleGoodFlow(double input) {
        int expected = (int) Math.round(input); // CODE REVIEW -> !!!! + SONARQUBE -> CODE SMELLS
        System.out.println(input + " rounded to integer is: " + expected);
        assertThat(Utility.roundDouble(input))
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource // @ValueSource(strings = {null, ""}
    public void testIsPalindromeEdgeCaseEmpty(String input) {
        assertThat(Utility.isPalindrome(input))
                .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"apa", "ana"})
    public void testIsPalindromeHappyFlow(String input) {
        assertThat(Utility.isPalindrome(input))
                .isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"test;TEST", "tEsT;TEST", "Java;JAVA"}, delimiter = ';')
    public void testToUpperCaseHappyFlow(String input, String expected) {
        assertThat(Utility.toUpperCase(input))
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(classes = {String.class, Integer.class, Double.class})
    public void testIsInstanceOfHappyFlow(Class<?> clazz) {
        Object testObject;

        if(clazz.equals(String.class)) {
            testObject = "test";
        } else if(clazz.equals(Integer.class)) {
            testObject = 1;
        } else {
            testObject = 2.22;
        }

        assertThat(testObject).isNotNull();
        assertThat(Utility.isInstanceOf(testObject, clazz)).isTrue();
    }
}

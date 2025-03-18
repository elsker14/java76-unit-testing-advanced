package params_examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

public class CalculatorTest {

    private Calculator calc = new Calculator();

    /*
    1,2,3
    4,5,9
    -9,-9,-18
     */
    @ParameterizedTest
    @DisplayName("Should add 2 numbers")
    @CsvSource({
            "1, 2, 3",
            "4, 5, 9",
            "-9, -9, -18"
    })
    public void testAdd(double a, double b, double expected) {
        assertThat(this.calc.add(a, b))
                .isEqualTo(expected)
                .isBetween(-100.0, 100.0);
    }

    @ParameterizedTest
    @DisplayName("Should subtract 2 numbers")
    @CsvSource({
            "8, 3, 5",
            "3, 8, -5",
            "10, 10, 0",
            "-5, 6, -11",
            "-5, -5, 0"
    })
    public void testSubtract(double a, double b, double expected) {
        assertThat(this.calc.subtract(a, b)).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Should multiply 2 numbers")
    @CsvSource({
            "10, 10, 100",
            "-10, -10, 100",
            "-10, 10, -100"
    })
    public void testMultiple(double a, double b, double expected) {
        assertThat(this.calc.multiply(a, b)).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Should divide 2 numbers")
    @CsvSource({
            "6, 2, 3",
            "7, 2, 3.5",
            "-1, 4, -0.25"
    })
    public void testDivide(double a, double b, double expected) {
        assertThat(this.calc.divide(a, b)).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Should divide by zero throw Arithmetic Exception")
    @CsvSource("10, 0")
    public void testDivideByZero(double a, double b) {
        assertThatException().isThrownBy(
                () -> this.calc.divide(a, b)
        );

        assertThatExceptionOfType(ArithmeticException.class)
                .isThrownBy(() -> this.calc.divide(a, b))
                .withMessage("Divide by zero is impossible");
    }

    /*
    No ParameterResolver registered for parameter [double arg2] in method [public void params_examples.CalculatorTest.testDivideByZero(double,double,double)].

    argumentele oricarei metode sunt indexate incepand cu 0:
        - double a - arg0
        - double b - arg1
        - double expected - arg2
     */
}

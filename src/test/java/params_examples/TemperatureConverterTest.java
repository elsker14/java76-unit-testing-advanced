package params_examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class TemperatureConverterTest {

    @ParameterizedTest
    @DisplayName("Should convert temperature successfully")
    @EnumSource(TemperatureConverter.class)
    public void shouldConvertTemperature(TemperatureConverter converter) {
        double result = converter.convertTemp(10);
        System.out.println(converter.name() + " -> " + result + " " + converter.name);
        assertThat(result)
                .isBetween(-1000.0, 1000.0)
                .isNotNull()
                .isNotInstanceOf(Function.class)    // Function<Double, Double>
                .isNotZero();
    }

    @ParameterizedTest
    @DisplayName("Should convert temperature successfully with inclusion rule")
    @EnumSource(
            value = TemperatureConverter.class,
            names = {"CELSIUS_TO_KELVIN", "CELSIUS_TO_FAHRENHEIT"}
    )
    public void shouldConvertTemperatureWithInclusionRule(TemperatureConverter converter) {
        assertThat(converter.convertTemp(10))
                .isLessThan(Integer.MAX_VALUE);
    }

    @ParameterizedTest
    @DisplayName("Should convert temperature successfully with exclusion rule")
    @EnumSource(
            value = TemperatureConverter.class,
            names = {"KELVIN_TO_CELSIUS"},
            mode = EnumSource.Mode.EXCLUDE
    )
    public void shouldConvertTemperatureWithExclusionRule(TemperatureConverter converter) {
        assertThat(converter.convertTemp(10))
                .isGreaterThan(Integer.MIN_VALUE);
    }
}

/*
    Echipa de business -> PO, PM, SEM, BA, ...
    business -> dev
    User Stories -> Criterii de acceptanta -> Unit tests -> Source code
    A user, must be able to convert temperature, depending on the given value.
 */

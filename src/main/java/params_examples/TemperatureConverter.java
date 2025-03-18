package params_examples;

import java.util.function.Function;

public enum TemperatureConverter {
    CELSIUS_TO_KELVIN(cTemp -> cTemp + 273.15, "C2K"),
    KELVIN_TO_CELSIUS(kTemp -> kTemp - 273.15, "K2C"),
    CELSIUS_TO_FAHRENHEIT(cTemp -> cTemp * 9 / 5 + 32, "C2F");

    // Enum se comporta ca o clasa de obiect CU CONSTANTE
    // CONSTANTE care sunt de tipul Enumului -> prin urmare se subordoneaza atributelor/constructorilor definiti
    private Function<Double, Double> converter;
    public String name;

    TemperatureConverter(Function<Double, Double> converter, String name) {
        this.converter = converter;
        this.name = name;
    }

    public double convertTemp(double temp) {
        return converter.apply(temp);
    }
}

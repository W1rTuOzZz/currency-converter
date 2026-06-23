package org.example;

public record ConversionResponse(
        String from,
        String to,
        double amount,
        double rate,
        double result
) {
}

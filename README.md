# Currency Converter 💱

A console application for converting currencies with real-time exchange rates.

## Technologies
- Java 21
- Maven
- Gson 2.10.1
- [ExchangeRate API](https://www.exchangerate-api.com/)

## Features
- Conversion between any world currencies
- Up-to-date rates via API
- Repeat conversions without restarting

## How to Run
1. Clone the repository
2. Register at exchangerate-api.com and get a free API key
3. Insert the key into `Main.java` in the `API_KEY` line
4. Run via IntelliJ IDEA

## Example Usage
```
=== Currency Converter ===
Enter source currency (for example, USD): GBP
Enter target currency (for example, EUR): EUR
Enter amount: 14
14,00 GBP = 16,04 EUR
Exchange rate: 1 GBP = 1,1456 EUR
```
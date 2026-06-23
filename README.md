# Currency Converter 💱

[![Java Version](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-blue.svg)](https://maven.apache.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A robust, production-ready console application built with Java 21 for real-time currency conversion utilizing external REST APIs.

---

## 📺 Live Demo / Preview

> 💡 **Tip for Reviewers:** Since this is a console application, you can see exactly how it runs and processes user input in the demonstration below without needing to clone and build it locally.

*(Сюда вставь свою GIF-анимацию работы программы! Записать её можно за 1 минуту через бесплатную утилиту ScreenToGif или ShareX)*
![App Demo](https://твоя_ссылка_на_гифку_или_удалить_эту_строку)

---

## 🚀 Key Features

* **Real-Time Data:** Integrates with third-party `ExchangeRate-API` via secure HTTP requests.
* **Modern Java Architecture:** Utilizes native `HttpClient` introduced in modern Java versions for efficient, asynchronous-ready network operations.
* **JSON Parsing:** Robust responses handling using Google's `Gson` library.
* **Security First:** Sensitive credentials (API Keys) are securely externalized into a `config.properties` file and ignored by Git to prevent leaks.
* **Interactive Loop:** Allows multiple consecutive operations without application restart.

---

## 🛠️ Tech Stack & Dependencies

* **Core:** Java 21
* **Build Automation:** Maven
* **Libraries:**
    * `com.google.code.gson:gson:2.10.1` (JSON processing)
* **API Provider:** [ExchangeRate-API](https://www.exchangerate-api.com/)

---

## 🔧 Architecture & Project Structure

The project strictly follows standard Maven architecture conventions:
* `src/main/java/org/example/Main.java` — Core logic handles HTTP communication, parsing, and user interactive loops.
* `config.properties` — Configuration management for environments variables.

---

## 📋 How to Run & Configure

### Prerequisites
* JDK 21 installed.
* Maven configured.

### Installation
1. Clone the repository:
   git clone [https://github.com/W1rTuOzZz/currency-converter.git](https://github.com/W1rTuOzZz/currency-converter.git)
2. Register at ExchangeRate-API to obtain your free API Key. 
3. In the project root folder, create a file named config.properties. 
4. Add your personal key into the file:
api.key=YOUR_ACTUAL_API_KEY


Build and run via terminal or your favorite IDE (e.g., IntelliJ IDEA):

mvn clean compile exec:java -Dexec.mainClass="org.example.Main"

💬 Example Usage

=== Currency Converter ===
Enter source currency (for example, GBP): USD

Enter target currency (for example, EUR): KGS

Enter amount: 100

100.00 USD = 8750.50 KGS

Exchange rate: 1 USD = 87.5050 KGS

Do you want to continue? (yes/no)

no
package src.main.serverConfigs;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private final Properties properties = new Properties();

    public ConfigReader(String fileName) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {

            if (input == null) {
                throw new RuntimeException("Cannot find config file: " + fileName);
            }

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    private String trimBrackets(String value) {
        if (value == null) return null;
        while (true) {
            // 1. Обрізаємо пробіли на кожній ітерації
            value = value.trim();

            if (value.length() < 2) {
                break; // Немає чого обрізати
            }

            char first = value.charAt(0);
            char last = value.charAt(value.length() - 1);

            // 2. Визначаємо, чи символи є парними та допустимими для обрізки
            boolean isPair = (first == '(' && last == ')') ||
                    (first == '[' && last == ']') ||
                    (first == '{' && last == '}') ||
                    (first == '"' && last == '"') ||
                    (first == '\'' && last == '\'');

            if (isPair) {
                // Якщо парні, обрізаємо перший і останній символ
                value = value.substring(1, value.length() - 1);
                // Продовжуємо цикл, щоб перевірити, чи немає наступного обгорткового символу
            } else {
                // Якщо немає парних символів, завершуємо роботу
                break;
            }
        }

        // Повертаємо остаточно обрізане значення
        return value;
    }

    public String getString(String key) {
        return trimBrackets(properties.getProperty(key));
    }

    public int getInt(String key) {
        String value = trimBrackets(properties.getProperty(key));

        if (value == null || value.isEmpty()) return 0;

        return Integer.parseInt(value);
    }
}


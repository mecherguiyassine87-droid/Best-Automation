
package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    // Charger le fichier config.properties
    static {
        try (InputStream input =
                ConfigReader.class.getClassLoader()
                    .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException(
                    "Fichier config.properties introuvable"
                );
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException(
                "Erreur lors du chargement de la configuration", e
            );
        }
    }

    // Lire une propriété par son nom
    public static String get(String key) {

        String value = System.getProperty(key);

        if (value == null) {
            value = properties.getProperty(key);
        }

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                "Propriété introuvable : " + key
            );
        }

        return value.trim();
    }
}

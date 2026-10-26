package hooks;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    private static WebDriver driver;

    // Ouvrir Chrome
    
public static void launchDriver() {

    if (driver == null) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);

        // Attente implicite : maximum 10 secondes
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        // Attente maximale du chargement de page
        driver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(60));

        System.out.println("Chrome ouvert avec succès");
    }
}



    // Récupérer le navigateur actuel
    public static WebDriver getDriver() {
        return driver;
    }

    // Fermer Chrome après les tests
    public static void closeDriver() {

        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
            }

            System.out.println("Chrome fermé");
        }
    }
}

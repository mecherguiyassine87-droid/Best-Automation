
package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Setup {

    // Exécuté avant chaque scénario
    @Before
    public void beforeScenario(Scenario scenario) {

        System.out.println("Début du test : " + scenario.getName());

        DriverManager.launchDriver();
    }

    // Capture d'écran après chaque étape Cucumber
    
@AfterStep
public void afterStep(Scenario scenario) {

    WebDriver driver = DriverManager.getDriver();

    if (driver != null) {
        try {

            // Attendre que le document HTML soit chargé
            WebDriverWait wait = new WebDriverWait(
                    driver,
                    Duration.ofSeconds(30)
            );

            wait.until(d ->
                    ((JavascriptExecutor) d)
                            .executeScript("return document.readyState")
                            .equals("complete")
            );

            // Prendre une capture d'écran
            byte[] screenshot =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.BYTES);

            // Ajouter la capture au rapport Cucumber
            scenario.attach(
                    screenshot,
                    "image/png",
                    "Screenshot"
            );

        } catch (Exception e) {
            System.out.println(
                    "Screenshot impossible : " + e.getMessage()
            );
        }
    }
}

    // Exécuté après chaque scénario
    @After
    public void afterScenario(Scenario scenario) {

        System.out.println("Résultat : " + scenario.getStatus());

        DriverManager.closeDriver();
    }
}


package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Keys;

import utils.ConfigReader;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Localisateur de la page HTML
    private final By body = By.tagName("body");

    // Localisateur du lien/logo BEST
    private final By bestLogo =
            By.xpath("//a[@aria-label='Best Al Yousufi']");

    // Constructeur
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(30)
        );
    }

    // Ouvrir la homepage staging
    public void ouvrir() {

        String url = ConfigReader.get("base.url");

        driver.get(url);

        System.out.println("Site ouvert : " + url);
    }

    // Vérifier que la page est chargée
    public boolean estChargee() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(body)
        ).isDisplayed();
    }

    // Vérifier que le lien/logo BEST est visible
    public boolean logoBestEstVisible() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(bestLogo)
        ).isDisplayed();
    }

    // Récupérer l'URL actuelle
    public String obtenirURL() {
        return driver.getCurrentUrl();
    }

    
    // À remplacer par le localisateur réel du champ Search
    private final By searchInput =
            By.cssSelector("input[placeholder='Search here...']");

    // Rechercher un produit par SKU
    public void rechercherProduit(String sku) {

        // Attendre que le champ soit utilisable
        var search = wait.until(
                ExpectedConditions.elementToBeClickable(searchInput)
        );

        // Effacer une éventuelle recherche précédente
        search.clear();

        // Saisir le SKU
        search.sendKeys(sku);

        // Lancer la recherche
        search.sendKeys(Keys.ENTER);

        System.out.println("Recherche du SKU : " + sku);
    }

}

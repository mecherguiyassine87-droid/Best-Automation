
package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;

public class SearchResultsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Titre H1 de la page de résultats BEST
    private final By searchResultTitle = By.cssSelector(
        "cx-page-slot[position='SearchResultsListSlot'] best-product-list div div div h1"
    );

    public SearchResultsPage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(30)
        );
    }

    // Vérifier que le titre de la PLP contient le SKU recherché
    public boolean produitEstVisible(String sku) {

        WebElement title = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                searchResultTitle
            )
        );

        // Récupérer le texte affiché dans le H1
        String actualTitle = title.getText();

        System.out.println(
            "Titre de la PLP : " + actualTitle
        );

        System.out.println(
            "SKU recherché : " + sku
        );

        // Vérifier que le titre contient le SKU
        return actualTitle.toLowerCase()
                .contains(sku.toLowerCase());
    }
    
    // Localisateur de l'image produit dans la PLP
    private final By productImage = By.xpath(
        "//img[@title='Air Volume: 280 M3/H, Wet Curtain Thickness : 28mm, 3 Speed Levels, 3 Wind Modes']"
    );

    // Ouvrir la fiche produit PDP
    public void ouvrirProduit() {

        // Attendre que l'image soit cliquable
        WebElement image = wait.until(
            ExpectedConditions.elementToBeClickable(productImage)
        );

        // Cliquer sur le produit
        image.click();

        System.out.println("Clic sur le produit effectué");
    }

}

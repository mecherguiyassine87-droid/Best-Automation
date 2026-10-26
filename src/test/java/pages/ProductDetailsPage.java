
package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Bouton Add to Cart à l'intérieur du composant
    private final By addToCartButton =
        By.cssSelector("button.add-to-cart-btn");

    public ProductDetailsPage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(30)
        );
    }

    // Vérifier que le bouton Add to Cart est visible
    public boolean boutonAddToCartVisible() {

        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                addToCartButton
            )
        ).isDisplayed();
    }

   

public void ajouterAuPanier() {

    // 1. Attendre que le bouton soit cliquable
    WebElement button = wait.until(
        ExpectedConditions.elementToBeClickable(addToCartButton)
    );

    // 2. Faire défiler la page jusqu'au bouton
    ((JavascriptExecutor) driver).executeScript(
        "arguments[0].scrollIntoView({block: 'center'});",
        button
    );

    // 3. Vérifier les propriétés du bouton
    System.out.println("Texte : " + button.getText());
    System.out.println("Visible : " + button.isDisplayed());
    System.out.println("Activé : " + button.isEnabled());
    System.out.println(
        "HTML : " + button.getAttribute("outerHTML")
    );

    // 4. Attendre à nouveau que le bouton soit cliquable
    button = wait.until(
        ExpectedConditions.elementToBeClickable(addToCartButton)
    );

    // 5. Cliquer sur le bouton
    button.click();

    System.out.println("Clic Add to Cart exécuté");
}

}

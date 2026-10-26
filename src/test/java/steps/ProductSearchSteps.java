
package steps;

import hooks.DriverManager;
import pages.HomePage;
import pages.SearchResultsPage;
import utils.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import pages.ProductDetailsPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class ProductSearchSteps {

    private HomePage homePage;
    private SearchResultsPage resultsPage;

    @Given("the customer is on BEST staging homepage")
    public void customerOnHomepage() {

        homePage = new HomePage(
                DriverManager.getDriver()
        );

        homePage.ouvrir();
    }

    @When("the customer searches for the configured SKU")
    public void searchConfiguredSku() {

        String sku = ConfigReader.get("test.sku");

        homePage.rechercherProduit(sku);
    }

    @Then("the search results heading should contain the configured SKU")
    public void verifyProductResults() {

        String sku = ConfigReader.get("test.sku");

        resultsPage = new SearchResultsPage(
                DriverManager.getDriver()
        );

        assertTrue(
                resultsPage.produitEstVisible(sku),
                "Produit introuvable dans les résultats : " + sku
        );
    }
    
    private ProductDetailsPage productDetailsPage;
    private String searchResultsUrl;

    // Cliquer sur le produit depuis la PLP
    @When("the customer opens the product details page")
    public void ouvrirPDP() {

        searchResultsUrl =
            DriverManager.getDriver().getCurrentUrl();

        resultsPage.ouvrirProduit();

        // Attendre que l'URL change après le clic
        WebDriverWait wait = new WebDriverWait(
            DriverManager.getDriver(),
            Duration.ofSeconds(30)
        );

        wait.until(
            org.openqa.selenium.support.ui.ExpectedConditions
                .not(
                    org.openqa.selenium.support.ui.ExpectedConditions
                        .urlToBe(searchResultsUrl)
                )
        );

        productDetailsPage = new ProductDetailsPage(
            DriverManager.getDriver()
        );

        System.out.println(
            "URL après ouverture PDP : " +
            DriverManager.getDriver().getCurrentUrl()
        );
    }

    // Vérifier le bouton Add to Cart
    @Then("the Add to Cart button should be visible")
    public void verifierAddToCart() {

        assertTrue(
            productDetailsPage.boutonAddToCartVisible(),
            "Le bouton Add to Cart est invisible"
        );
    }

    // Cliquer sur Add to Cart
    @When("the customer adds the product to the cart")
    public void ajouterProduitAuPanier() {

        productDetailsPage.ajouterAuPanier();
    }

}

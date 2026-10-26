
package steps;

import hooks.DriverManager;
import pages.HomePage;
import utils.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.*;

public class HomeSteps {

    private HomePage homePage;

    @Given("the customer opens BEST staging homepage")
    public void ouvrirHomepage() {

        homePage = new HomePage(
                DriverManager.getDriver()
        );

        homePage.ouvrir();
    }

    @Then("the homepage should be displayed")
    public void verifierHomepage() {

        assertTrue(
                homePage.estChargee(),
                "La homepage ne s'affiche pas"
        );
    }

    @Then("the current URL should match staging")
    public void verifierURL() {

        String expected = ConfigReader.get("base.url");
        String actual = homePage.obtenirURL();

        assertTrue(
                actual.startsWith(expected),
                "URL incorrecte : " + actual
        );
    }

    // Vérifier la visibilité du lien/logo BEST
    @Then("the BEST Al Yousufi logo should be visible")
    public void verifierLogoBest() {

        assertTrue(
                homePage.logoBestEstVisible(),
                "Le logo BEST Al Yousufi est absent ou invisible"
        );

        System.out.println(
                "Logo BEST Al Yousufi visible avec succès"
        );
    }
}

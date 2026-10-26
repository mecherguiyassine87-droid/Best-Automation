
Feature: BEST Product Details and Add to Cart

  @e2e @qa @postUpgrade
  Scenario: TC_E2E_001 - Search product and click Add to Cart

    Given the customer is on BEST staging homepage
    When the customer searches for the configured SKU
    Then the search results heading should contain the configured SKU
    When the customer opens the product details page
    Then the Add to Cart button should be visible
    When the customer adds the product to the cart

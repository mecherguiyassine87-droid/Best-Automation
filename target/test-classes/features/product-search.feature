
Feature: BEST Product Search

  @search @qa @postUpgrade
  Scenario: TC_SEARCH_001 - Search product by SKU

    Given the customer is on BEST staging homepage
    When the customer searches for the configured SKU
    Then the search results heading should contain the configured SKU


Feature: BEST Staging Homepage

  @smoke @qa @postUpgrade
  Scenario: TC_SMOKE_001 - Verify BEST homepage

    Given the customer opens BEST staging homepage
    Then the homepage should be displayed
    And the current URL should match staging
    And the BEST Al Yousufi logo should be visible

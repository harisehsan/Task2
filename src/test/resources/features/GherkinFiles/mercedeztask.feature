@mercedesAutomation
Feature: A Class Model price Validation

  @validate_a_class_model_price_in_Range
  Scenario: Validate A Class models price are between £15,000 and £60,000
    Given I navigate to the mercedes-benz UK site
    Then I should be on the mercedes-benz site
    When I select HatchBack under our model section on main page
    Then I verify that A class Hatchback should exists in the lists
    Then I Hover the mouse on the A class models to get "Build your car" option and select it
    Then I should be navigated to the build you car page
    Then I check that fuel type "Diesel" should be available and select it
    Then I verify that one or more item(s) should be present in the list
    Then I should see that all the available items fuel type should be "Diesel"
    Then I verify that the catalog should be sorted according the default sorting method
    When I save the screenshot of the resultant screen
    Then The screenshot should be present in the directory
    And I save Highest and lowest price in the text file
    Then I verify that the text file should exist and should not be empty
    Then FINALLY I verify that the price range should be between "£15,000" and "£60,000"

# NOTE: All the statements starting with "Then" contains test Validations. There are total 11 validation checks in that
# above scenario.







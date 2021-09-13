Feature: Enter login details


    @e2e
    @smoke
    Scenario Outline: Sucessful login

    Given I start the application

    When I click email field

    And I enter valid email <email>

    And I click password field

    And I enter valid password <password>

    And I enter valid password <password>

    Examples:
      | email | password |
Feature: LoginAndCreateBrand

  Scenario Outline: Successful Login to the page create and save new brand and logout after
    Given I open web browser
    When I navigate to login page
    And I provide username as "<username>" and password as "<password>"
    And I click on login button
    Then name should be "<name>"
    When I click on brands link
    Then table brands should be "<table_name>"
    When I click on create brand button
    Then label brand should be "<label_name>"
    And I provide brand name as "<brand>"
    And I click on submit button
    Then table brands should be "<table_name>"
    When Open dropdown menu
    And click logout button
    Then user logged out

    Examples:
      | username | password | name  | table_name | label_name  | brand|
      | admin    | 123      | admin |Brands list| Brand name | AdminBrand|




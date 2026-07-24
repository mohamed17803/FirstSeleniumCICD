Feature: Register page

  As a new user
  I want to register an account
  So that I can access registered user features

  Scenario Outline: Register with valid data
    Given the registration user is on the home page
    When the registration user navigates to the register page
    And the registration user fills the form with first name "<firstName>" and last name "<lastName>" and email "<email>" and telephone "<telephone>" and password "<password>" and confirm password "<confirmPassword>"
    And the registration user marks the registration choices
    Then the registration user submits the registration form

    Examples:
      | firstName | lastName | email                | telephone  | password  | confirmPassword |
      | Hamo      | Tester   | Hamosayed1@gmail.com | 0123456789 | 123456789 | 123456789       |

Feature: Login page

  As a registered user
  I want to log in to my account
  So that I can access my account information

  Scenario: Login with valid credentials
    Given the user is on the home page
    When the user navigates to the login page
    And the user logs in with email "Hamosayed01@gmail.com" and password "123456789"
    Then the user should be logged in successfully

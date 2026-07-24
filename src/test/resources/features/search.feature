Feature: Search

  As a customer
  I want to search for products
  So that I can find matching items

  Scenario Outline: Search for a product
    Given the search user is on the home page
    When the search user searches for product "<productName>"
    Then the search user should see the desired product

    Examples:
      | productName |
      | Macbook     |

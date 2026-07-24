Feature: Add to cart

  As a customer
  I want to add a product to the shopping cart
  So that I can review it before checkout

  Scenario: Add iPhone to shopping cart
    Given the cart user is on the home page
    When the cart user opens the iPhone product
    And the cart user adds the product to the cart
    Then the cart user should see the add to cart success message
    And the cart user should see the product in the shopping cart

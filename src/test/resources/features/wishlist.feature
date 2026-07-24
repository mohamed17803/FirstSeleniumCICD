Feature: Wishlist

  As a registered user
  I want to add products to my wishlist
  So that I can find them again later

  Scenario: Add products to wishlist
    Given the wishlist user is on the home page
    When the wishlist user navigates to the login page
    And the wishlist user logs in with email "Hamosayed1@gmail.com" and password "123456789"
    And the wishlist user returns to the home page
    And the wishlist user adds products to the wishlist
    And the wishlist user opens the wishlist page
    Then the wishlist user should see the added products

Feature: Contact Us

  As a customer
  I want to send an inquiry to the store
  So that I can receive support

  Scenario Outline: Submit a contact us inquiry
    Given the contact user is on the home page
    When the contact user navigates to the login page
    And the contact user logs in with email "<email>" and password "<password>"
    And the contact user returns to the home page
    And the contact user navigates directly to the contact us page
    And the contact user submits enquiry "<writtenMail>"
    Then the contact user should see confirmation message "<confirmationMessage>"

    Examples:
      | email                | password  | writtenMail                                             | confirmationMessage                                         |
      | Hamosayed1@gmail.com | 123456789 | I need more info about your store working hours, please. | Your enquiry has been successfully sent to the store owner! |

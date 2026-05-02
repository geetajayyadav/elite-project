Feature: Login Feature

  Scenario: Valid Login
    Given user is on login page
    When user enters username "aypl" and password "AD03EF9F"
    Then user should login successfully
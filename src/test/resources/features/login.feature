Feature: Login Feature

  Scenario Outline: Login with multiple data
    Given user is on login page
    When user enters username "<username>" and password "<password>"
    Then user should see "<result>"

  Examples:
    | username | password  | result               |
    | aypl     | AD03EF9F  | success              |
    | wrong    | wrong123  | error                |
    |          |           | validation message   |
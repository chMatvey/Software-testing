Feature: Find compositions by author

  Scenario Outline: find compositions by author
    Given we have author name "<name>"
    When we find compositions by author name
    Then we get compositions list is not empty "<result>"

    Examples:
      | name     | result |
      | author   | true   |
      | author1  | false  |

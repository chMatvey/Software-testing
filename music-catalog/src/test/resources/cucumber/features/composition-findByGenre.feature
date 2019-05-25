Feature: Find compositions by genre

  Scenario Outline: find compositions by author
    Given we have genre name "<name>"
    When we find composition by genre name
    Then we get compositions list group by genre is not empty "<result>"

    Examples:
      | name    | result |
      | pop     | true   |
      | name    | false  |

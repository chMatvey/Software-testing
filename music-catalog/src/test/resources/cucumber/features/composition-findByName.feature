Feature: Find composition by name

  Scenario Outline: find composition by name
    Given we have composition name "<compositionName>", "<authorName>"
    When we find composition by name
    Then we get composition "<result>"

    Examples:
      | compositionName  | authorName  | result |
      | name             | author      | true   |
      | name             | author1     | false  |

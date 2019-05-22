Feature: Add composition

  Scenario Outline: add composition
    Given we have new composition "<compositionName>", "<authorName>" and "<genreName>"
    When we try add this composition
    Then new composition equal null "<result>"

    Examples:
      | compositionName  | authorName  | genreName | result |
      | name             | author      | pop       | false  |
      | name             | author      | pop       | true   |
      | name             | author1     | pop       | false  |
      | name1            | author      | pop       | false  |
      | name             | author      | rap       | true   |

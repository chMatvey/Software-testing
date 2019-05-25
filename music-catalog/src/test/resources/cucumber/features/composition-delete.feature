Feature: Delete composition

  @compositionDelete
  Scenario Outline: delete composition
    Given we have composition "<compositionName>", "<authorName>"
    When we try delete composition
    And we get all compositions
    Then compositions does not contains composition

    Examples:
      | compositionName | authorName |
      | name            | author     |

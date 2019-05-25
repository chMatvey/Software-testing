Feature: Delete genre

  @genreDelete
  Scenario Outline: delete genre
    Given we have genre "<name>"
    When we try delete genre
    And we get all genres
    Then genres does not contains genre

    Examples:
      | name |
      | name |

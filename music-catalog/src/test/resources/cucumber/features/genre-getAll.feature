Feature: Get all genres

  Scenario: get all genres
    When we try get all genres
    Then genres list is not empty

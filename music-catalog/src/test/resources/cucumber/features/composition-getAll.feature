Feature: Get all compositions

  Scenario: get all compositions
    When we try get all composition
    Then compositions list is not empty

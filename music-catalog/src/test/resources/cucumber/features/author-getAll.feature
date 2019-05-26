Feature: Get all authors

  Scenario: get all authors
    When we try get all authors
    Then authors list is not empty

Feature: Get all playlist names

  @GetAllPlaylistNames
  Scenario: get all playlist names
    When we try get all playlist names
    Then playlist names list is not empty

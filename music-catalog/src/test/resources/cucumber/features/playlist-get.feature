Feature: Get playlist compositions

  @GetPlaylist
  Scenario Outline: get playlist compositions
    Given we have playlist "<name>"
    When we try get playlist compositions
    Then we get playlist with compositions

    Examples:
      | name  |
      | name  |

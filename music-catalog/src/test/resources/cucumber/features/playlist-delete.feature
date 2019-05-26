Feature: Delete playlist

  @deletePlaylist
  Scenario Outline: delete playlist
    Given we have unnecessary playlist "<name>"
    When we try delete playlist
    And we get all playlists
    Then playlists does not contain playlist

    Examples:
      | name |
      | name |

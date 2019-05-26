Feature: Delete playlist

  @deletePlaylist
  Scenario: delete playlist
    Given we have unnecessary playlist
    When we try delete playlist
    And we get all playlists
    Then playlists does not contain playlist

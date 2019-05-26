Feature: Add composition to playlist

  @AddCompositionToPlaylist
  Scenario: playlist add composition
    Given we have playlist and composition
    When we try add composition to playlist
    Then playlist contains composition

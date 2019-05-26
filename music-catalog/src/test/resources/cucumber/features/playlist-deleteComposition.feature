Feature: Delete composition from playlist

  @DeleteCompositionFromPlaylist
  Scenario: delete composition from playlist
    Given we have playlist and composition names
    When we try delete composition from playlist
    Then playlist does not contains composition

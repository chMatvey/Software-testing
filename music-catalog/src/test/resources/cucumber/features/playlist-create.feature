Feature: Create playlist

  @CreatePlaylist
  Scenario Outline: create playlist
    Given we have new playlist "<name>"
    When we try create playlist
    Then we get new playlist? "<result>"

    Examples:
      | name   | result |
      | name   | true   |
      | name1  | false  |

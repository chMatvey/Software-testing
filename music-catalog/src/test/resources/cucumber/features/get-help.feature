Feature: Get help

  @getHelp
  Scenario: get help
    When we try get help
    Then we get get information about using app

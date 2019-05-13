Feature: Composition

  Scenario: add composition
    Given composition "<name>", "<author>" and "<genre>"
    When we try add composition
    Then we can find composition in db

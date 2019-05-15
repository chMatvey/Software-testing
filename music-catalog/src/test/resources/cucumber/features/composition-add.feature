Feature: Add composition

  Background:
    Given we have composition set

  @Success
  Scenario Outline: add composition successfully
    Given we have new composition "<name>", "<author>" and "<genre>"
    When we try add this composition
    Then we can find composition in set
    Examples:
      | name  | author  | pop |
      | name1 | author  | pop |
      | name  | author1 | pop |

  @Fail
  Scenario Outline: add composition - composition already exist
    Given we have new composition "<name>", "<author>" and "<genre>"
    When we try add this composition
    Then we get error with message "This composition already exist"
    And  we can't find composition in set
    Examples:
      | name | author | pop |
      | name | author | rap |

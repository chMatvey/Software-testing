Feature: Add composition

  Background:
    Given We have composition list with one composition "name", "author" and "genre"
    And We have new composition "<name>", "<author>" and "<genre>"
    When I try add new composition

  @Success
  Scenario Outline: add composition successfully
    Then we can find composition in db
    Examples:
      | name  | author  | genre  |
      | name1 | author1 | genre1 |

  @Fail
  Scenario Outline: add composition - composition already exist
    Then we get error with message "This composition already exist"
    Examples:
    Examples:
      | name | author | genre |
      | name | author | genre |

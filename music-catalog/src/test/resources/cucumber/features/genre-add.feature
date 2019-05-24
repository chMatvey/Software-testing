#Feature: Add genre
#
#  Scenario Outline: add genre
#    Given we have new genre "<name>"
#    When we try add this genre
#    Then new genre equal null "<result>"
#
#    Examples:
#      | name  | result |
#      | name  | false  |
#      | name1 | true   |

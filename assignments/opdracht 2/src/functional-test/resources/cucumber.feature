Feature: the greeting can be retrieved
  Scenario: client makes call to GET /greet
    When the client calls /greet
    Then the client receives status code of 200
    And the client receives greeting saying "Greetings, Giel Beelen"
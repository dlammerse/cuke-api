Feature: Api test messages application

  Background:
    Given the API is running
    And make sure 10 standard message is available

  Scenario: Get messages
    When I call get messages
    Then the response shows 10 messages
    And every messages has an id, subject and body

  Scenario: Post messages
    When I call post messages
    Then the posted message is returned when I call a get messages


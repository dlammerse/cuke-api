Feature: Api test messages application

  Background:
    Given the API is running
    And make sure 10 standard message is available

  Scenario: Get messages
    When I call get messages
    Then the response shows multiple messages

Feature: Api test messages application

  Background:
    Given I created 1 standard messages

  Scenario: Get messages
    When I call get messages
    Then the response shows multiple messages

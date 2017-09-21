Feature: Testing listener service
         AS a listener service that can monitor a given slack channel
         I WANT to be able to extract events from that channel
         SO THAT messages from those events can be analysed


  Scenario Outline: Get valid response when a message is sent to a slack channel
    Given a message is sent to a slack channel
    Then a valid response is received with <status>

    Examples:
      | status |
      | 200    |

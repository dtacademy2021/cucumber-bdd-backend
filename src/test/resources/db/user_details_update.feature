Feature:  User details update
  @duotify
  Scenario: Update user details in DB and verify the change on the UI

    When I update firstName with "Barack", lastName with "Ozturk" and email with "furkan@gmail.com" for a user with username "duotech"
    Then The same info on the UI should be correct
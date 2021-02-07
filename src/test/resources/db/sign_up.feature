Feature:  Verify data mapping of the user details in the database

  @duotify
    Scenario: Verify sign-up details are correctly mapped with the DB
      Given I am in Sign Up page
      When I enter the following User details and sign up
        |username | first | last  | email             | password |
        | j.biden | Joe   | Biden | jbiden79@gmail.com| jbiden79 |
      Then I should land on the welcome page
      And The user details should be correctly mapped in the users table of database

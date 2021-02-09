Feature:  Column names
#  @duotify_db_only
  Scenario: Verify column names per business requirement document

    When I retrieve column names for the "songs" table
    Then The names of the columns should be the following
      | id         |
      | title      |
      | artist     |
      | album      |
      | genre      |
      | duration   |
      | path       |
      | albumOrder |
      | plays      |



  @duotify_db_only
  Scenario: Verify genres business requirement document

    When I retrieve genre names for the genres table
    Then The names of the genres should be the following
      | rap        |
      | pop        |
      | techno     |
      | rnb        |
      | house      |
      | classical  |
      | jazz       |
      | electronic |
      | dance      |
      | reggae     |
      | reggaeton  |



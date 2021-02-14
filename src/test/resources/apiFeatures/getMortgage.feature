Feature:  Get mortgage
  @api
  Scenario: Verify getmortgage endpoint

    Given I am logged an as a user and I have generated a token
    When I add the authorization token to the header
    And Send a GET request to the endpoint "/getmortagage.php"
    Then The response code should be 200 and the mortgage count should be correct


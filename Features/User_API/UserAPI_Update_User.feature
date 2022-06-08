Feature: To verify API automation with rest assured

  Scenario Outline: To verify Rest Service for update user endpoint - Put Method
    Given User want to execute PUT operation for "/Users"
    When User submit the PUT request for update "<name>","<phonenumber>","<location>","<timezone>","<visastatus>" for "<userid>"
    Then User should get 201 success Status code along with response body
    Then User updated successfully schema validation

    Examples: 
      | userid | name            | phonenumber | location | timezone | visastatus |
      | U18    | Rama,Rama       |  3456723456 | Houston  | CST      | H4         |
      | U69    | krishna,krishna |  3425678234 | Houston  | EST      | H4         |

  Scenario Outline: To verify Rest Service for update user endpoint as invalid user - Put Method
    Given User want to execute PUT operation for "/Users"
    When User submit the PUT request "<name>","<phonenumber>","<location>","<timezone>","<visastatus>" for "<userid>" as invalid user
    Then User should get 401 Unauthorized Status code along with response body

    Examples: 
      | userid | name            | phonenumber | location | timezone | visastatus |
      | U18    | Rama,Rama       |  3456723456 | Houston  | CST      | H4         |
      | U69    | krishna,krishna |  3425678234 | Houston  | EST      | H4         |

  Scenario Outline: To verify Rest Service for update user not found - Put Method
    Given User want to execute PUT operation for "/Users"
    When User submit the PUT request for update "<name>","<phonenumber>","<location>","<timezone>","<visastatus>" for "<userid>"
    Then User should get 404 update user not found Status code along with response body
    Then User not found schema validation

    Examples: 
      | userid | name            | phonenumber | location | timezone | visastatus |
      | Z19    | Rama,Rama       |  3456723456 | Houston  | CST      | H4         |
      | Y17    | krishna,krishna |  3425678234 | Houston  | EST      | H4         |

  Scenario Outline: To verify Rest Service for update user missing required fields - Put Method
    Given User want to execute PUT operation for "/Users"
    When User submit the PUT request for update "<name>","<phonenumber>","<location>","<timezone>","<visastatus>" for "<userid>"
    Then User get 400 bad request Status code along with response body
    Then User missing required fields for update response schema validation

    Examples: 
      | userid | name            | phonenumber | location | timezone | visastatus |
      | U18    | Rama,Rama       |    12348797 |          |          |            |
      | U69    | krishna,krishna |    34278234 |          |          |            |

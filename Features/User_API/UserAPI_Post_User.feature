Feature: To verify API automation with rest assured

  Scenario Outline: To verify Rest Service for update user endpoint - Put Method
    Given User want to execute POST operation for "/Users"
    When User pass the data from given sheetname "<SheetName>" and rownumber <RowNumber>
    Then User should get 201 success code
    Then User validate the success message with json schema

    Examples: 
      | SheetName | RowNumber |
      | User      |         0 |
      | User      |         1 |

  Scenario Outline: To verify Rest Service for invalid endpoint - Get Method
    Given User want to execute POST invalid endpoint for "/Users1/"
    When User pass the data from given sheetname "<SheetName>" and rownumber <RowNumber>
    Then User should 404 not found code
    Then User validate invalid url by response schema

    Examples: 
      | SheetName | RowNumber |
      | User      |         0 |
      | User      |         2 |

  Scenario Outline: To verify Rest Service for invalid endpoint - Get Method
    Given User want to execute POST invalid endpoint for "/Users/"
    When User pass the data from given sheetname "<SheetName>" and rownumber <RowNumber>
    Then User should 400 visa status required
    Then User get bad request 400 for phone number already exist
    Then User get bad request 400 for timezone required
    Then User validate visa status required by response schema

    Examples: 
      | SheetName | RowNumber |
      | User      |         2 |
      | User      |         3 |
      | User      |         4 |

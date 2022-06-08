Feature: To verify API automation with rest assured

  Scenario Outline: To verify Rest Service - Post Method
    Given Skill User perform POST operation for "/Skills"
    When Skill User pass the data from given sheetname "Skill"
    Then Skill User validate response schema
    Then Skill User should get 201 success code
 
 
  Scenario Outline: To verify unauthorized Rest Service - Get Method
    Given Skill User perform POST operation for "/Skills"
    When Skill User pass the data from given sheetname "Skill" as invalid user
    Then Skill User should get 401 unauthorized code

  Scenario Outline: To verify Rest Service for invalid endpoint - Get Method
    Given Skill User want to execute POST invalid endpoint for "/Skills1/"
    When Skill User pass the data from given sheetname "Skill"
    Then Skill User should 404 not found code
    Then Skill User validate invalid url response schema

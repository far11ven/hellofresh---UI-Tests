Feature: SuccessfulUserSignUp [Positive Senario]
 
    
  @m3u1 @all @positive
  Scenario Outline: TC4- Successful registration for new user
    Given User Navigates to 'AutomationPractice' Website URL
    Then User clicks on 'SignIn' link from header
    When User provides valid emailId for account creation
    Then User clicks on 'Create An Account' button
    And Verify user is present on "Registartion" page
    When User fills Registration form with 'RegistrationTestData' details 
    Then Verify user was created successfully

    Examples: 
      | Username                        | Password  | FULLNAME  |
      | hf_challenge_123456@hf12345.com | 12345678  | Joe Black |

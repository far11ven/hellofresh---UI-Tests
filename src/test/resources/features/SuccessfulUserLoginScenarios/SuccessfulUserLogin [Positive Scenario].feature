Feature: SuccessfulUserLogin [Positive Senario]
 
    
  @m3u2 @all @positive
  Scenario Outline: TC1 - Successful Login with Single user
    Given User Navigates to 'AutomationPractice' Website URL
    Then User clicks on 'SignIn' link from header
    When User provides valid credentials for "<Username>" and "<Password>"
    Then User clicks on 'SignIn' button
    And Verify user is loggedIn as "<FULLNAME>"

    Examples: 
      | Username                        | Password  | FULLNAME  |
      | hf_challenge_123456@hf12345.com | 12345678  | Joe Black |

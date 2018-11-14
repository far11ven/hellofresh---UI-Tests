Feature: SuccessfulUserLogin [Negitive Scenario]

  Background: 
    Given User Navigates to 'AutomationPractice' Website URL
    
  @m2u1 @all @negative
  Scenario Outline: TC2 - Successful Login with Single user
    Given User clicks on 'SignIn' link from header
    When User provides valid credentials for "<Username>" and "<Password>"
    Then User clicks on 'SignIn' button
    And Verify user is not loggedIn as "<FULLNAME>"

    Examples: 
      | Username                        | Password  | FULLNAME  |
      | hf_challenge_123456@hf12345.com | 12345678  | Max Black |
      | hf_challenge_123456@hf12345.com | 12345678  | Joe White |

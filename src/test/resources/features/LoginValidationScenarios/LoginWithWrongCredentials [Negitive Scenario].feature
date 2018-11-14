Feature: LoginWithWrongCredentials [Negitive Scenario]

  Background: 
    Given User Navigates to 'AutomationPractice' Website URL
    
  @m2u1 @all @negative
  Scenario Outline: TC2 - Login with multiple users
    Given User clicks on 'SignIn' link from header
    When User provides valid credentials for "<Username>" and "<Password>"
    Then User clicks on 'SignIn' button
    And Verify user is shown error message

    Examples: 
      | Username                        | Password        |
      | wrong_username                  | 12345678        |
      | hf_challenge_123456@hf12345.com | wrong_password  |

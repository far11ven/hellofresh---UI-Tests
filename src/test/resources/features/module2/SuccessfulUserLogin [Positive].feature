Feature: Module 1 User Story 1

  Background: Environment set up module 1
    Given User Navigates to 'AutomationPractice' Website URL
    
  @m1u1 @all
  Scenario Outline: TC2 - Login with multiple users
    Given User clicks on 'SignIn' link from header
    When User provides valid credentials for "<Username>" and "<Password>"
    Then User clicks on 'SignIn' button
    And Verify user is loggedIn

    Examples: 
      | Username                        | Password  |
      | hf_challenge_123456@hf12345.com | 12345678  |
      | hf_challenge_123456@hf12345.com | 12345678  |

Feature: SuccessfulCheckoutFlow [Positive Senario]
 
    
  @m1u1 @all @positive
  Scenario Outline: TC5 - Successful checkout with valid user
    Given User Navigates to 'AutomationPractice' Website URL
    Then User clicks on 'SignIn' link from header
    When User provides valid credentials for "<Username>" and "<Password>"
    Then User clicks on 'SignIn' button
    And Verify user is loggedIn as "<FULLNAME>"
    When User clicks on 'Women' section link
    And User selects a product and adds it to cart
    Then Verify product has been added to cart
    When User clicks on 'Proced To Checkout'
    Then Verify cart product details and proceed
    And Verify address and proceed
    And Verify shipping and proceed
    When User selects 'Bankwire' payment method
    And User confirms the order
    Then Verify order completion details

    Examples: 
      | Username                        | Password  | FULLNAME  |
      | hf_challenge_123456@hf12345.com | 12345678  | Joe Black |

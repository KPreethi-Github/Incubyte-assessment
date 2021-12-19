Feature: Incubute assessment

  Scenario: Compose a mail
    Given I launch the Chrome browser
    When I navigate to Gmail login screen
    When I login to my Gmail with username as "itispreetee22@gmail.com"
    When I click the "Next" button
    When I login to my Gmail with password as "testingNow"
    When I click the "Next" button
    When I click the "Compose" button
    When I compose the mail with all necessary details
      | Fields       | Values                          |
      | Mail-body    | Automation QA test for Incubyte |
      | Mail-subject | Incubyte                        |
      | Mail-To      | preeteekannan@gmail.com         |
    When I click the "Send" button
    Then I validate the success message
    When I close the browser

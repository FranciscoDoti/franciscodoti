Feature: Performance tests

Background: set Performance page
    Given set Performance page



Scenario: List of Pending requests on Season Closing Workflow
    Given PFT_An ADMINISTRATOR
    When PFT_SC_List approved and rejected Season Closing reports
    Then PFT_Verify that time response is less than "40000"


Scenario: List of Pending requests on Season Closing Workflow
    Given PFT_An ADMINISTRATOR
    When PFT_List Season Closing pending requests
    Then PFT_Verify that time response is less than "40000"

Scenario: Load a Season closing report
    Given PFT_An ADMINISTRATOR
    When PFT_Load a Season closing
    Then PFT_Verify that time response is less than "40000"

Scenario: List rols approved and rejected
    Given PFT_An ADMINISTRATOR
    When PFT_List rols approved and rejected
    Then PFT_Verify that time response is less than "40000"

Scenario: List Pending Requests for Rol Workflow
    Given PFT_An ADMINISTRATOR
    When PFT_List rol pending requests
    Then PFT_Verify that time response is less than "40000"


Scenario: List rol models
    Given PFT_An ADMINISTRATOR
    When PFT_List Rol Models
    Then PFT_Verify that time response is less than "40000"

Scenario: Export rol history report
    Given PFT_An ADMINISTRATOR
    When PFT_Export Rol History report
    Then PFT_Verify that time response is less than "40000"

Scenario: List Rol history
    Given PFT_An ADMINISTRATOR
    When PFT_List Rol History
    Then PFT_Verify that time response is less than "40000"

Scenario: Load a Rol
    Given PFT_An ADMINISTRATOR
    When PFT_Load a Rol
    Then PFT_Verify that time response is less than "40000"

Scenario: Post Participant delivery
    Given PFT_An ADMINISTRATOR
    When PFT_Post Participant delivery
    Then PFT_Verify that time response is less than "40000"

Scenario: Export Participant deliveries
    Given PFT_An ADMINISTRATOR
    When PFT_Export Participant deliveries
    Then PFT_Verify that time response is less than "40000"

Scenario: List Participant deliveries
    Given PFT_An ADMINISTRATOR
    When PFT_List Participant deliveries
    Then PFT_Verify that time response is less than "30000"

Scenario: List Parameters
    Given PFT_An ADMINISTRATOR
    When PFT_List parameters
    Then PFT_Verify that time response is less than "30000"

Scenario: List fixed volumes
    Given PFT_An ADMINISTRATOR
    When PFT_List fixed volumes
    Then PFT_Verify that time response is less than "30000"

Scenario: Get Token
    Given PFT_An ADMINISTRATOR
    When PFT_Get token
    Then PFT_Verify that time response is less than "3000"

Scenario: Post a fixed volume
    Given PFT_An ADMINISTRATOR
    When PFT_Post a fixed volume
    Then PFT_Verify that time response is less than "30000"


# new feature
# Tags: optional
    
Feature: Bulk's tests

    Background: to set bulk feature env
        Given set Enviroment "dev"
            And Set new UUID
            And A Bulk file with Participant Delivers


    Scenario: Bulk with all the Participant Deliver OK with an ADMINISTRATOR
        Given PD_Bulk_An ADMINISTRATOR
        When I post the Bulk
        Then Verify that each participant deliver was posted ok

    Scenario: Bulk with one participant deliver with wrong Document Number
       Given PD_Bulk_An ADMINISTRATOR
       Given I replace a PD with wrong Document Number
       When I post the Bulk
       Then Verify that the Bulk has an error.

    Scenario: Bulk with one participant deliver with negative volume with an ADMINISTRATOR
       Given PD_Bulk_An ADMINISTRATOR
       Given I replace a PD with with negative volume
       When I post the Bulk
       Then Verify that the Bulk has an error.

    Scenario: Bulk with one participant deliver with empty Document Type with an ADMINISTRATOR
       Given PD_Bulk_An ADMINISTRATOR
       Given I replace a PD with empty Document Type
       When I post the Bulk
       Then Verify that the Bulk has an error.

    Scenario: Bulk with one participant deliver with empty Document Number with an ADMINISTRATOR
       Given PD_Bulk_An ADMINISTRATOR
       Given I replace a PD with empty Document Number
       When I post the Bulk
       Then Verify that the Bulk has an error.

    Scenario: Bulk with one participant deliver with null quantity with an ADMINISTRATOR
       Given PD_Bulk_An ADMINISTRATOR
       Given I replace a PD with with null quantity
       When I post the Bulk
       Then Verify that the Bulk has an error.

    Scenario: Bulk with one participant deliver with wrong document type with an ADMINISTRATOR
       Given PD_Bulk_An ADMINISTRATOR
       Given I replace a PD with wrong Document Type
       When I post the Bulk
       Then Verify that the Bulk has an error.

    Scenario: Bulk with one participant deliver with quantity with letters with an ADMINISTRATOR
       Given PD_Bulk_An ADMINISTRATOR
       Given I replace a PD with quantity with letters
       When I post the Bulk
       Then Verify that the Bulk has an error.

    Scenario: Bulk with one participant deliver with quantity with simbols with an ADMINISTRATOR
       Given PD_Bulk_An ADMINISTRATOR
       Given I replace a PD with quantity with simbols
       When I post the Bulk
       Then Verify that the Bulk has an error.






    Scenario: Bulk with all the Participant Deliver OK with a PARTICIPANT
        Given PD_Bulk_A PARTICIPANT
        When I post the Bulk
        Then Verify that each participant deliver was posted ok

    Scenario: Bulk with one participant deliver with wrong Document Number with a PARTICIPANT
       Given PD_Bulk_A PARTICIPANT
       Given I replace a PD with wrong Document Number
       When I post the Bulk
       Then Verify that the Bulk has an error.

    Scenario: Bulk with one participant deliver with negative volume with a PARTICIPANT
       Given PD_Bulk_A PARTICIPANT
       Given I replace a PD with with negative volume
       When I post the Bulk
       Then Verify that the Bulk has an error.

    Scenario: Bulk with one participant deliver with empty Document Type with a PARTICIPANT
       Given PD_Bulk_A PARTICIPANT
       Given I replace a PD with empty Document Type
       When I post the Bulk
       Then Verify that the Bulk has an error.

    Scenario: Bulk with one participant deliver with empty Document Number with a PARTICIPANT
       Given PD_Bulk_A PARTICIPANT
       Given I replace a PD with empty Document Number
       When I post the Bulk
       Then Verify that the Bulk has an error.

    Scenario: Bulk with one participant deliver with null quantity with a PARTICIPANT
       Given PD_Bulk_A PARTICIPANT
       Given I replace a PD with with null quantity
       When I post the Bulk
       Then Verify that the Bulk has an error.

    Scenario: Bulk with one participant deliver with wrong document type with a PARTICIPANT
       Given PD_Bulk_A PARTICIPANT
       Given I replace a PD with wrong Document Type
       When I post the Bulk
       Then Verify that the Bulk has an error.

    Scenario: Bulk with one participant deliver with quantity with letters with a PARTICIPANT
       Given PD_Bulk_A PARTICIPANT
       Given I replace a PD with quantity with letters
       When I post the Bulk
       Then Verify that the Bulk has an error.

    Scenario: Bulk with one participant deliver with quantity with simbols with a PARTICIPANT
       Given PD_Bulk_A PARTICIPANT
       Given I replace a PD with quantity with simbols
       When I post the Bulk
       Then Verify that the Bulk has an error.
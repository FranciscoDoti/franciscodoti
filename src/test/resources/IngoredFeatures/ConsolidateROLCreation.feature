# new feature
# Tags: optional
    
Feature: Scenarios for Consolidate ROL creation

Background: This is a background to setup the ConsolidateROLCreationPage and Enviroment
    Given Setup ROL Creation Page
    Given Setup ROL Models Page


Scenario: Verify the sum of Participant Deliveries in a ROL coming the next month with an ADMINISTRATOR
    Given RC_An ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "08707604000184"
    When Create "CONSOLIDATED" ROL Model for "08707604000184"
    Given AffiliateDocument "10953876000106"
        And Current Period
    When I load the ROL
        And I save the current amount
        And I post a participant deliver from "77856995004109" to "10953876000106" with volume "10"
        And I load the ROL
    Then Verify that the sum of PD is correct

Scenario: Create a ROL with fixed price and fixed Volume for Intacta with an ADMINISTRATOR
    Given RC_An ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "08707604000184"
    When Create "CONSOLIDATED" ROL Model for "08707604000184"
    Given AffiliateDocument "08707604000184"
        And Year "2018"
        And Month "06"
    When I load the ROL
        And I add fixed volume "100" and fixed Price "100" for "Intacta"
        And I create the ROL
    Then Verify that the ROL was created



Scenario: Load a ROL with ROL Model but unauthorized by ITS
    Given RC_An ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Given AffiliateDocument "80768153001003"
        And Year "2018"
        And Month "09"
    When I load the ROL
    Then Verify that the ROL couldnt be loaded

Scenario: Load a ROL with ROL Model authorized by ITS but out of the date range
    Given RC_An ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Given AffiliateDocument "08707604000184"
        And Year "2089"
        And Month "09"
    When I load the ROL
    Then Verify that the ROL couldnt be loaded








Scenario: Create a ROL with fixed volume for Intacta with an ADMINISTRATOR
    Given RC_An ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "08707604000184"
    When Create "CONSOLIDATED" ROL Model for "08707604000184"
    Given AffiliateDocument "08707604000184"
        And Year "2018"
        And Month "03"
    When I load the ROL
        And I add fixed volume "100" and fixed Price "0" for "Intacta"
        And I create the ROL
    Then Verify that the ROL was created




Scenario: Create a ROL with fixed price for Intacta with an ADMINISTRATOR
    Given RC_An ADMINISTRATOR
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "08707604000184"
    When Create "CONSOLIDATED" ROL Model for "08707604000184"
    Given AffiliateDocument "08707604000184"
        And Year "2018"
        And Month "03"
    When I load the ROL
        And I add fixed volume "0" and fixed Price "100" for "Intacta"
        And I create the ROL
    Then Verify that the ROL was created




####################################################################################################################################
  ####################################################################################################################################


Scenario: Verify that Participant can load a ROL for a Headoffice with contract
    Given RC_A PARTICIPANT
    Given RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "77863223000107"
    When Create "CONSOLIDATED" ROL Model for "77863223000107"
    Given AffiliateDocument "77863223000107"
        And Year "2018"
        And Month "03"
    When I load the ROL
    Then Verify that the ROL could be loaded



#Scenario: Verify that Participant can't load a ROL for a Headoffice without contract
#    Given RC_A PARTICIPANT
#    Given RMod_An ADMINISTRATOR
#    Then Delete ROL Model with id "08707604000184"
#    When Create "CONSOLIDATED" ROL Model for "08707604000184"
#    Given AffiliateDocument "08707604000184"
#        And Year "2018"
#        And Month "03"
#    When I load the ROL
#    Then Verify that the ROL couldnt be loaded

